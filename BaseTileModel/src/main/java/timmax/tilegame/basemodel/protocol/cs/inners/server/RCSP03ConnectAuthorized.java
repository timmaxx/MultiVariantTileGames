package timmax.tilegame.basemodel.protocol.cs.inners.server;

import java.util.Set;

import timmax.tilegame.basemodel.protocol.EventOfServer20Logout;
import timmax.tilegame.basemodel.protocol.EventOfServer31GetGameTypeSet;
import timmax.tilegame.basemodel.protocol.cs.inners.CSP03ConnectAuthorized;
import timmax.tilegame.basemodel.protocol.cs.inners.ClientState;
import timmax.tilegame.basemodel.protocol.server.IModelOfServer;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.transport.TransportOfServer;

public class RCSP03ConnectAuthorized<ClientId> extends CSP03ConnectAuthorized<IModelOfServer> {
    private final ClientId clientId;
    private final TransportOfServer<ClientId> transportOfServer;

    public RCSP03ConnectAuthorized(ClientState<IModelOfServer> clientState, ClientId clientId, TransportOfServer<ClientId> transportOfServer) {
        super(clientState);
        this.clientId = clientId;
        this.transportOfServer = transportOfServer;
    }

    @Override
    public void forgetUserName() {
        super.forgetUserName();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer20Logout());
    }

    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        super.setGameTypeSet(setOfModelOfServerDescriptor);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer31GetGameTypeSet(setOfModelOfServerDescriptor));
    }
}
