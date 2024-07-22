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
    //   Но ещё лучше может сделать resetUser()?
    //       находится в RemoteClientState02ConnectNonIdent.
    //       Поэтому пришлось сделать здесь точную копию.
    //       - Копия метода из RemoteClientState02ConnectNonIdent:
    @Override
    public void setUser(String userName, Set<GameType> gameTypeSet) {
        super.setUser(userName, gameTypeSet);
        getClientStateAutomaton().sendEventOfServer(clientId, new EventOfServer21SetUser(userName, gameTypeSet));
    }

    // ToDo: Устранить дублирование кода.
    //       Этот класс является наследником ClientState06GameMatchSetSelected,
    //       но код который хотелось-бы иметь как void changeStateTo02ConnectNonIdent(),
    //       находится в RemoteClientState04GameTypeSetSelected.
    //       Поэтому пришлось сделать здесь точную копию.
    //       - Копия метода из RemoteClientState04GameTypeSetSelected:
    @Override
    public void changeStateTo02ConnectNonIdent() {
        super.changeStateTo02ConnectNonIdent();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer20ForgetUser()
        );
    }

    // class ClientState07GameMatchSelected
    // ---- 6 Конкретная партия игры
    @Override
    public void forgetGameMatchX() {
        super.forgetGameMatchX();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer60ForgetGameMatch()
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
