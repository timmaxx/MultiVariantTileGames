package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import timmax.tilegame.basemodel.protocol.server.IGameMatch;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

public class EventOfClient61SetGameMatch<ClientId> extends EventOfClient<ClientId> {
    private GameMatchId gameMatchId;

    public EventOfClient61SetGameMatch() {
        super();
    }

    public EventOfClient61SetGameMatch(GameMatchId gameMatchId) {
        this();
        this.gameMatchId = gameMatchId;
    }

    // ToDo: Вероятно нужно переработать код executeOnServer(...) для классов (см. ниже) и вероятно перестать
    //       использовать в них clientId.
    //       Комментарий относится:
    //       - в первую очередь к классу:
    //       -- EventOfClient61SetGameMatch,
    //       - во вторых:
    //       -- к EventOfClient,
    //       - в третьих:
    //       -- ко всем остальным EventOfClientХХ...
    //       clientId используется в executeOnServer(...) в классах-наследниках EventOfClient только в:
    //       - EventOfClient61SetGameMatch (используется с рефлексией)
    //       -- используется с рефлексией.
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        IGameMatch iGameMatch = null;
        if (gameMatchId.isNullOrEmpty()) {
            remoteClientStateAutomaton.forgetGameMatchX();
            return;
        }
        // ToDo: То, что внутри if - унести в RemoteClientState05GameTypeSelected.
        //       И удалить if.
        //       Также см. else.
        if (gameMatchId.equalsNewGame()) {
            // ToDo: Лучше-бы чтобы:
            //       1.1. При создании перечня матчей на сервере (не в этом, а в более раннем классе),
            //            если список не содержит ни одного матча в состоянии "Не начат",
            //            то сервер сам создаёт такой матч.
            //            В этом случае на клиент всегда будет отправляться перечень как минимум с одним матчем.
            //            Т.е. и логика в этом методе уйдёт большей частью в более ранний класс.
            //       1.2. Клиент должен работать только с таким списком, который поступил от сервера
            //            (т.е. не создавать новую запись, а только выбирать).
            //            И только для не игранного матча должна быть доступна возможность редактировать параметры матча.
            //            А для игранного (матч на паузе):
            //            - на клиенте опционально: не давать возможность редактировать в принципе.
            //            - на сервере обязательно: проверять попытку изменить параметры матча и возвращать актуальные
            //                значения на клиент.
            //       1.2.1. Однако потом нужно будет вернуться к возможности удалять или как-то скидывать в архив
            //              - начатые, но не оконченные (на паузе) партии.
            //              - начатые и оконченные партии - для возможности ознакомления с ними.
            // В текущей реализации новая игра создаётся на клиенте раньше, чем на сервере,
            // поэтому в этом методе, определяется, что клиент указал на новую игру определяется через
            // id == "New game".

            // Определяем ранее выбранный тип
            GameType gameType = remoteClientStateAutomaton.getGameType();
            Constructor<? extends IGameMatch> constructorOfGameMatch = gameType.getConstructorOfGameMatch();

            try {
                // Создаём экземпляр модели, ранее выбранного типа.
                // ToDo: Нужно минимизировать количество согласований между классами.
                //       Параметры, которые передаются в newInstance():
                //       1. Перечень параметров согласовывается с перечнем в
                //          GameType :: GameType(...)
                //          в строке
                //          constructorOfGameMatch = gameMatchClass.getConstructor(...);
                //          и там-же ниже в строке
                //          iGameMatch = constructorOfGameMatch.newInstance(...);
                //       2. Ну в т.ч. это, те-же параметры, которые поступили в executeOnServer().
                // ToDo: Здесь создаётся экземпляр матча и, как видно, ему передаётся идентификатор клиента,
                //       Но это единственный класс в котором этот идентификатор используется.
                //       Нужно сделать так, что-бы идентификатор клиента не использовался-бы, и тогда
                //       для всей иерархии классов EventOfClientХХ... можно будет удалить параметр ClientId для метода
                //       executeOnServer(...). А может и для классов.
                iGameMatch = constructorOfGameMatch.newInstance(remoteClientStateAutomaton, clientId);
            } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
                logger.error("Server cannot create object of model for {} with constructorOfGameMatch with specific parameters.", gameType, e);
                System.exit(1);
            }
            remoteClientStateAutomaton
                    // ToDo: Странно. У одного remoteClientStateAutomaton может быть несколько GameMatch?
                    //       Проверить. Вероятно неправильно.
                    .getGameMatchXSet()
                    // ToDo: Исправить Warning:(54, 26) Unchecked assignment: 'timmax.tilegame.basemodel.protocol.server.IGameMatch' to 'timmax.tilegame.basemodel.protocol.server.IGameMatch<ClientId>'
                    .add(iGameMatch);
        }
        // ToDo: То, что внутри else, вытащить из него.
          else {

            iGameMatch = remoteClientStateAutomaton
                    .getGameMatchXSet()
                    .stream()
                    .filter(x -> x.toString().equals(gameMatchId.getId()))
                    .findAny()
                    .orElse(null)
            ;

            if (iGameMatch == null) {
                logger.error("There is not match '" + gameMatchId.getId() + "'");
                remoteClientStateAutomaton.forgetGameMatchX();
                return;
            }
        }

        // ToDo: Исправить Warning:(72, 87) Unchecked assignment: 'timmax.tilegame.basemodel.protocol.server.IGameMatch' to 'timmax.tilegame.basemodel.protocol.server.IGameMatch<ClientId>'
        remoteClientStateAutomaton.setGameMatchX(iGameMatch);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "gameMatchId=" + gameMatchId +
                '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameMatchId);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameMatchId = (GameMatchId) in.readObject();
    }
}
