package timmax.tilegame.basemodel.protocol.cs.inners.server;

import java.util.Set;

import timmax.tilegame.basemodel.protocol.cs.inners.AClientStateProtected;
import timmax.tilegame.basemodel.protocol.server.IModelOfServer;
import timmax.tilegame.transport.TransportOfServer;

public class RemoteClientState2<ClientId> extends AClientStateProtected<IModelOfServer> {
    private final ClientId clientId;
    private final TransportOfServer<ClientId> transportOfServer;

    private Set<String> setOfViewName;

    public RemoteClientState2(TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        super(null); // !!!!!
        this.transportOfServer = transportOfServer;
        this.clientId = clientId;
    }

    public Set<String> getSetOfViewName() {
        return setOfViewName;
    }

    public void setSetOfViewName(Set<String> setOfViewName) {
        this.setOfViewName = setOfViewName;
    }

    public TransportOfServer<ClientId> getTransportOfServer() {
        return transportOfServer;
    }

    public ClientId getClientId() {
        return clientId;
    }
}
