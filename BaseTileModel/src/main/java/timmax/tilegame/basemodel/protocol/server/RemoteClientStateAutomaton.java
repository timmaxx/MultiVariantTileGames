package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.IFabricOfClientStateAutomaton;
import timmax.tilegame.transport.TransportOfServer;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RemoteClientStateAutomaton<ClientId> extends ClientStateAutomaton<IModelOfServer> {
    private final TransportOfServer<ClientId> multiGameWebSocketServer;

    // ToDo: Удалить, т.к. ClientId есть в мапе в MultiGameWebSocketServer
    private final ClientId webSocket;
    private final Set<String> setOfViewName;

    private Map<String, Integer> mapOfParamsOfModelValue;

    public RemoteClientStateAutomaton(
            IFabricOfRemoteClientStates<ClientId> fabricOfClientStatesForServer,
            IFabricOfClientStateAutomaton iFabricOfClientStateAutomaton,
            TransportOfServer<ClientId> multiGameWebSocketServer,
            // ToDo: Удалить, т.к. ClientId есть в мапе в MultiGameWebSocketServer
            ClientId webSocket) {
        super(fabricOfClientStatesForServer, iFabricOfClientStateAutomaton);
        this.multiGameWebSocketServer = multiGameWebSocketServer;
        this.setOfViewName = new HashSet<>();
        this.webSocket = webSocket;
    }

    // For remote clientState:
    // ToDo: Удалить, т.к. ClientId есть в мапе в MultiGameWebSocketServer
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
