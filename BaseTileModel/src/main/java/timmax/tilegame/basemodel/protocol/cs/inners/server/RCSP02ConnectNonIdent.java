package timmax.tilegame.basemodel.protocol.cs.inners.server;

import timmax.tilegame.basemodel.protocol.EventOfServer21Login;
import timmax.tilegame.basemodel.protocol.cs.inners.CSP02ConnectNonIdent;
import timmax.tilegame.basemodel.protocol.cs.inners.ClientState;
import timmax.tilegame.basemodel.protocol.server.IModelOfServer;
import timmax.tilegame.transport.TransportOfServer;

public class RCSP02ConnectNonIdent<ClientId> extends CSP02ConnectNonIdent<IModelOfServer> {
    private final ClientId clientId;
    private final TransportOfServer<ClientId> transportOfServer;

    public RCSP02ConnectNonIdent(ClientState<IModelOfServer> clientState, ClientId clientId, TransportOfServer<ClientId> transportOfServer) {
        super(clientState);
        this.clientId = clientId;
        this.transportOfServer = transportOfServer;
    }

    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer21Login(userName));
    }
}
