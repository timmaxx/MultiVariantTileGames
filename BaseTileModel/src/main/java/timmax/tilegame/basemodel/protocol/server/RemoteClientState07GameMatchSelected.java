package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

import java.util.Set;

public class RemoteClientState07GameMatchSelected<ClientId> extends ClientState07GameMatchSelected<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState07GameMatchSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    // ToDo: Устранить дублирование кода.
    //       Этот класс является наследником ClientState07GameMatchSelected,
    //       но код который хотелось-бы иметь как void setUser(),
    //       находится в RemoteClientState02ConnectNonIdent.
    //       Поэтому пришлось сделать здесь точную копию.
    //       - Копия метода из RemoteClientState02ConnectNonIdent:
    @Override
    public void identifyAuthenticateAuthorizeUser(String userName, Set<GameType> gameTypeSet) {
        super.identifyAuthenticateAuthorizeUser(userName, gameTypeSet);
        getClientStateAutomaton().sendEventOfServer(clientId, new EventOfServer21IdentifyAuthenticateAuthorizeUser(userName, gameTypeSet));
    }

    // ToDo: Устранить дублирование кода.
    //       Этот класс является наследником ClientState07GameMatchSelected,
    //       но код который хотелось-бы иметь как void openConnectWithoutUserIdentify(),
    //       находится в RemoteClientState04GameTypeSetSelected.
    //       Поэтому пришлось сделать здесь точную копию.
    //       - Копия метода из RemoteClientState04GameTypeSetSelected:
    @Override
    public void openConnectWithoutUserIdentify() {
        super.openConnectWithoutUserIdentify();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer40ForgetUser()
        );
    }

    // ToDo: Устранить дублирование кода.
    //       Этот класс является наследником ClientState07GameMatchSelected,
    //       но код который хотелось-бы иметь как void resetUser(),
    //       находится в RemoteClientState06GameMatchSetSelected.
    //       Поэтому пришлось сделать здесь точную копию.
    //       - Копия метода из RemoteClientState06GameMatchSetSelected:
    @Override
    public void reauthorizeUser() {
        String userName = getClientStateAutomaton().getUserName();
        Set<GameType> gameTypeSet = getClientStateAutomaton().getGameTypeSet();
        super.identifyAuthenticateAuthorizeUser(userName, gameTypeSet);
        getClientStateAutomaton().sendEventOfServer(clientId, new EventOfServer21IdentifyAuthenticateAuthorizeUser(userName, gameTypeSet));
    }

    // class ClientState07GameMatchSelected
    // ---- 6 Конкретная партия игры
    @Override
    public void forgetGameMatchX() {
        super.forgetGameMatchX();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer70ForgetGameMatch()
        );
    }

    // ---- 7
    @Override
    public void setGameMatchPlaying(Boolean gameIsPlaying) {
        super.setGameMatchPlaying(gameIsPlaying);
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer71SetGameMatchPlaying()
        );

        // ToDo: Вызов этого метода может быть как для модели:
        //       - для которой ранее ещё не было вызвано createNewGame()
        //       - так и для той, у которой был вызов createNewGame(), но потом она была поставлена на паузу.
        getClientStateAutomaton().getGameMatchX().createNewGame();
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton getClientStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getClientStateAutomaton());
    }
}
