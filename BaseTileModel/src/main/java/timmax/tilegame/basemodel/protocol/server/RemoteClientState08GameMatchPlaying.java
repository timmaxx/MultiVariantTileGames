package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class RemoteClientState08GameMatchPlaying<ClientId> extends ClientState08GameMatchPlaying<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState08GameMatchPlaying(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    // ToDo: Устранить дублирование кода.
    //       Этот класс является наследником ClientState06GameMatchSetSelected,
    //       но код который хотелось-бы иметь как void forgetUser(),
    //       находится в RemoteClientState04GameTypeSetSelected.
    //       Поэтому пришлось сделать здесь точную копию.
    //       - Копия метода из RemoteClientState04GameTypeSetSelected:
    @Override
    public void forgetUser() {
        super.forgetUser();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer20ForgetUser()
        );
    }

    // ToDo: Устранить дублирование кода.
    //       Этот класс является наследником ClientState07GameMatchSelected,
    //       но код который хотелось-бы иметь как void forgetGameType(),
    //       находится в RemoteClientState06GameMatchSetSelected.
    //       Поэтому пришлось сделать здесь точную копию.
    //       - Копия метода из RemoteClientState06GameMatchSetSelected:
    @Override
    public void forgetGameType() {
        super.forgetGameType();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer40ForgetGameType()
        );
    }

    // ToDo: Устранить дублирование кода.
    //       Этот класс является наследником ClientState07GameMatchSelected,
    //       но код который хотелось-бы иметь как void forgetGameMatchX(),
    //       находится в RemoteClientState07GameMatchSelected.
    //       Поэтому пришлось сделать здесь точную копию.
    //       - Копия метода из RemoteClientState07GameMatchSelected:
    @Override
    public void forgetGameMatchX() {
        super.forgetGameMatchX();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer60ForgetGameMatch()
        );
    }

    // class ClientState08GameMatchPlaying
    // ---- 7
    @Override
    public void forgetGameMatchPlaying() {
        super.forgetGameMatchPlaying();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer70ForgetGameMatchPlaying()
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton getClientStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getClientStateAutomaton());
    }
}
