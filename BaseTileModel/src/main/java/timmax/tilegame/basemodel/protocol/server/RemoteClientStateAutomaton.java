package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.transport.TransportOfServer;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RemoteClientStateAutomaton<ClientId> extends ClientStateAutomaton<IModelOfServer> {
    // ToDo: Удалить TransportOfServer<ClientId> multiGameWebSocketServer.
    //       Вместо того, чтобы хранить переменную типа TransportOfServer здесь, нужно рассмотреть вариант по передаче
    //       её как параметра в те методы, где она нужна.
    // ToDo: Удалить TransportOfServer<ClientId> multiGameWebSocketServer.
    //       1. TransportOfClient - LocalClientStateAutomaton:
    //         - В MultiGameWebSocketClientManyTimesUse есть переменная LocalClientStateAutomaton.
    //         - В LocalClientStateAutomaton нет переменной TransportOfClient (или MultiGameWebSocketClientManyTimesUse).
    //           И как-то без этого обходится.
    //       2. TransportOfServer - Map<WebSocket, RemoteClientStateAutomaton<WebSocket>>
    //         - В MultiGameWebSocketServer есть мапа WebSocket -> RemoteClientStateAutomaton
    //         - В RemoteClientStateAutomaton есть переменная TransportOfServer.
    //           Почему-же здесь нужна эта переменная?
    private final TransportOfServer<ClientId> multiGameWebSocketServer;

    private final Set<String> setOfViewName;

    private Map<String, Integer> mapOfParamsOfModelValue;

    public RemoteClientStateAutomaton(
            IFabricOfRemoteClientStates<ClientId> fabricOfClientStatesForServer,
            TransportOfServer<ClientId> multiGameWebSocketServer) {
        super(fabricOfClientStatesForServer);
        this.multiGameWebSocketServer = multiGameWebSocketServer;
        this.setOfViewName = new HashSet<>();
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
