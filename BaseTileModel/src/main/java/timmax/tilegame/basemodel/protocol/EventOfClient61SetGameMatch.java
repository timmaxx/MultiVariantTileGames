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
    //       -- используется с рефлексией,
    //       -- явно вызывается sendEventOfServer, в которую передаётся clientId.
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        // ToDo: Исправить Warning:(33, 9) Raw use of parameterized class 'IGameMatch'
        IGameMatch iGameMatch = null;
        if (gameMatchId.isNullOrEmpty()) {
            remoteClientStateAutomaton.getTransportOfServer().sendEventOfServer(
                    clientId,
                    new EventOfServer60ForgetGameMatch()
            );
            return;
        }
        if (gameMatchId.equalsNewGame()) {
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
                iGameMatch = constructorOfGameMatch.newInstance(remoteClientStateAutomaton, clientId);
            } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
                logger.error("Server cannot create object of model for {} with constructorOfGameMatch with specific parameters.", gameType, e);
                System.exit(1);
            }
            remoteClientStateAutomaton
                    .getGameMatchXSet()
                    // ToDo: Исправить Warning:(54, 26) Unchecked assignment: 'timmax.tilegame.basemodel.protocol.server.IGameMatch' to 'timmax.tilegame.basemodel.protocol.server.IGameMatch<ClientId>'
                    .add(iGameMatch);
        }
        /*
          else {
            iGameMatch = remoteClientState
                    .getGameMatchSet()
                    .stream()
                    .filter(x -> x.toString().equals(gameMatchId.getId()))
                    .findAny()
                    .orElse(null)
            ;

            if (iGameMatch == null) {
                logger.error("There is not model '" + gameMatchId.getId() + "'");
                remoteClientState.forgetServerBaseModel();
                return;
            }
        }
        */
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
