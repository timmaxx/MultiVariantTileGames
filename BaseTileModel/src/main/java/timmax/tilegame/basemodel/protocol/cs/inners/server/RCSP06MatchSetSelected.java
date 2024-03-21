package timmax.tilegame.basemodel.protocol.cs.inners.server;

import timmax.tilegame.basemodel.protocol.EventOfServer50ForgetGameMatchSet;
import timmax.tilegame.basemodel.protocol.EventOfServer61SetGameMatch;
import timmax.tilegame.basemodel.protocol.cs.inners.CSP06MatchSetSelected;
import timmax.tilegame.basemodel.protocol.cs.inners.ClientState;
import timmax.tilegame.basemodel.protocol.server.IModelOfServer;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.transport.TransportOfServer;

public class RCSP06MatchSetSelected<ClientId> extends CSP06MatchSetSelected<IModelOfServer> {
    private final ClientId clientId;
    private final TransportOfServer<ClientId> transportOfServer;

    public RCSP06MatchSetSelected(ClientState<IModelOfServer> clientState, ClientId clientId, TransportOfServer<ClientId> transportOfServer) {
        super(clientState);
        this.clientId = clientId;
        this.transportOfServer = transportOfServer;
    }

    @Override
    public void forgetGameMatchSet() {
        super.setGameMatchSet(null);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer50ForgetGameMatchSet());
    }

    @Override
    public void setServerBaseModel(IModelOfServer iModelOfServer) {
        super.setServerBaseModel(iModelOfServer);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer61SetGameMatch(
                new InstanceIdOfModel(iModelOfServer.toString())
        ));
    }
}
