package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.transport.TransportOfServer;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RemoteClientStateAutomaton<ClientId> extends ClientStateAutomaton<IModelOfServer, ClientId> {
    private final TransportOfServer<ClientId> multiGameWebSocketServer;
    private final ClientId webSocket;
    private final Set<String> setOfViewName;

    private Map<String, Integer> mapOfParamsOfModelValue;

    public RemoteClientStateAutomaton(IFabricOfRemoteClientStates<ClientId> fabricOfClientStatesForServer, TransportOfServer<ClientId> multiGameWebSocketServer, ClientId webSocket) {
        super(fabricOfClientStatesForServer);
        this.multiGameWebSocketServer = multiGameWebSocketServer;
        this.setOfViewName = new HashSet<>();
        this.webSocket = webSocket;
    }

    // For remote clientState:
    public ClientId getClientId() {
        return webSocket;
    }

    public TransportOfServer<ClientId> getTransportOfServer() {
        return multiGameWebSocketServer;
    }

    protected Set<String> getSetOfViewName() {
        return setOfViewName;
    }

    public void setMapOfParamsOfModelValue(Map<String, Integer> mapOfParamsOfModelValue) {
        this.mapOfParamsOfModelValue = mapOfParamsOfModelValue;
    }

    public Map<String, Integer> getMapOfParamsOfModelValue() {
        return mapOfParamsOfModelValue;
    }
}