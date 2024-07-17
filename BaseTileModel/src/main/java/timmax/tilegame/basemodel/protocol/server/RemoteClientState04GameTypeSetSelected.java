package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState04GameTypeSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

public class RemoteClientState04GameTypeSetSelected<ClientId> extends ClientState04GameTypeSetSelected<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState04GameTypeSetSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    // class ClientState04GameTypeSetSelected
    // ---- 3 (Список типов игр)
    @Override
    public void forgetUser() {
        super.forgetUser();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer20ForgetUser()
        );
    }

    // ---- 4 (Конкретный тип игры)
    @Override
    public void setGameType(GameType gameType, Set<IGameMatch> gameMatchXSet) {
        if (gameType == null) {
            getClientStateAutomaton().sendEventOfServer(
                    clientId,
                    new EventOfServer20ForgetUser()
            );
            return;
        }

        // Done:
        //       1.1. При создании перечня матчей на сервере,
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
        // ToDo:
        //       1.2.1. Однако потом нужно будет вернуться к возможности удалять или как-то скидывать в архив
        //              - начатые, но не оконченные (на паузе) партии.
        //              - начатые и оконченные партии - для возможности ознакомления с ними.

        IGameMatch iGameMatch = null;
        Constructor<? extends IGameMatch> GameMatchConstructor = gameType.getGameMatchConstructor();

        try {
            // Создаём экземпляр модели, ранее выбранного типа.
            // ToDo: Нужно минимизировать количество согласований между классами.
            //       Параметры, которые передаются в newInstance():
            //       1. Перечень параметров согласовывается с перечнем в
            //          GameType :: GameType(...)
            //          в строке
            //          GameMatchConstructor = gameMatchClass.getConstructor(...);
            //          и там-же ниже в строке
            //          iGameMatch = GameMatchConstructor.newInstance(...);
            //       2. Ну в т.ч. это, те-же параметры, которые поступили в executeOnServer().
            // ToDo: Здесь создаётся экземпляр матча и, как видно, ему передаётся идентификатор клиента,
            //       Но это единственный класс в котором этот идентификатор используется.
            //       Нужно сделать так, что-бы идентификатор клиента не использовался-бы, и тогда
            //       для всей иерархии классов EventOfClientХХ... можно будет удалить параметр ClientId для метода
            //       executeOnServer(...). А может и для классов.
            iGameMatch = GameMatchConstructor.newInstance(getClientStateAutomaton(), clientId);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            logger.error("Server cannot create object of model for {} with GameMatchConstructor with specific parameters.", gameType, e);
            System.exit(1);
        }
        gameMatchXSet.add(iGameMatch);

        super.setGameType(gameType, gameMatchXSet);

        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer41SetGameType(
                        gameType.getGameTypeName(),
                        gameMatchXSet
                                .stream()
                                .map(x -> new GameMatchId(x.toString()))
                                .collect(Collectors.toSet())
                )
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton getClientStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getClientStateAutomaton());
    }
}
