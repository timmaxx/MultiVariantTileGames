package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState06GameMatchSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

public class RemoteClientState06GameMatchSetSelected<ClientId> extends ClientState06GameMatchSetSelected<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState06GameMatchSetSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
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

    // class ClientState06GameMatchSetSelected
    // ---- 5 Перечень партий
    @Override
    public void forgetGameType() {
        super.forgetGameType();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer40ForgetGameType()
        );
    }

    // ---- 6 Конкретная партия игры
    @Override
    public void setGameMatchX(IGameMatch gameMatchX) {
        super.setGameMatchX(gameMatchX);
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer61SetGameMatch(
                        new GameMatchId(gameMatchX.toString())
                )
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton getClientStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getClientStateAutomaton());
    }
}
