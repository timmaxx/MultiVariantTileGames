package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState06GameMatchSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;

public class RemoteClientState06GameMatchSetSelected<ClientId> extends ClientState06GameMatchSetSelected<IModelOfServer> {
    private final ClientId clientId;

    public RemoteClientState06GameMatchSetSelected(ClientStateAutomaton<IModelOfServer> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    // class ClientState06GameMatchSetSelected
    // ---- 5 Перечень партий
    @Override
    public void forgetGameMatchSet() {
        super.forgetGameMatchSet();
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(
                clientId,
                new EventOfServer50ForgetGameMatchSet()
        );
    }

    // ---- 6 Конкретная партия игры
    @Override
    public void setServerBaseModel(IModelOfServer iModelOfServer) {
        super.setServerBaseModel(iModelOfServer);
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(
                clientId,
                new EventOfServer61SetGameMatch(
                        new InstanceIdOfModel(iModelOfServer.toString())
                )
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}
