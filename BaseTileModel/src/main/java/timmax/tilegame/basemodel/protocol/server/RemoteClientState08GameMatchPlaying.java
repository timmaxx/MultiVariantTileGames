package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

import java.util.Set;

public class RemoteClientState08GameMatchPlaying<ClientId> extends ClientState08GameMatchPlaying<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState08GameMatchPlaying(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    // ToDo: Устранить дублирование кода.
    //       Этот класс является наследником ClientState08GameMatchPlaying,
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
    //       Этот класс является наследником ClientState08GameMatchPlaying,
    //       но код который хотелось-бы иметь как void changeStateTo02ConnectNonIdent(),
    //       находится в RemoteClientState04GameTypeSetSelected.
    //       Поэтому пришлось сделать здесь точную копию.
    //       - Копия метода из RemoteClientState04GameTypeSetSelected:
    @Override
    public void changeStateTo02ConnectNonIdent() {
        super.changeStateTo02ConnectNonIdent();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer40ForgetUser()
        );
    }

    // ToDo: Устранить дублирование кода.
    //       Этот класс является наследником ClientState08GameMatchPlaying,
    //       но код который хотелось-бы иметь как void resetUser(),
    //       находится в RemoteClientState06GameMatchSetSelected.
    //       Поэтому пришлось сделать здесь точную копию.
    //       - Копия метода из RemoteClientState06GameMatchSetSelected:
    @Override
    public void reauthorizeUser() {
        String userName = getClientStateAutomaton().getUserName();
        Set<GameType> gameTypeSet = getClientStateAutomaton().getGameTypeSet();
        super.setUser(userName, gameTypeSet);
        getClientStateAutomaton().sendEventOfServer(clientId, new EventOfServer21SetUser(userName, gameTypeSet));
    }

    // ToDo: Устранить дублирование кода.
    //       Этот класс является наследником ClientState08GameMatchPlaying,
    //       но код который хотелось-бы иметь как void forgetGameMatchX(),
    //       находится в RemoteClientState07GameMatchSelected.
    //       Поэтому пришлось сделать здесь точную копию.
    //       - Копия метода из RemoteClientState07GameMatchSelected:
    @Override
    public void forgetGameMatchX() {
        super.forgetGameMatchX();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer70ForgetGameMatch()
        );
    }

    // class ClientState08GameMatchPlaying
    // ---- 7
    @Override
    public void forgetGameMatchPlaying() {
        super.forgetGameMatchPlaying();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer80ForgetGameMatchPlaying()
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton getClientStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getClientStateAutomaton());
    }
}
