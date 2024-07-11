package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.transport.TransportOfServer;

public class RemoteClientStateAutomaton<ClientId> extends ClientStateAutomaton<IGameMatch> {
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

    public RemoteClientStateAutomaton(
            IFabricOfRemoteClientStates<ClientId> fabricOfClientStatesForServer,
            TransportOfServer<ClientId> multiGameWebSocketServer) {
        super(fabricOfClientStatesForServer);
        this.multiGameWebSocketServer = multiGameWebSocketServer;
    }

    void sendEventOfServer(ClientId clientId, EventOfServer transportPackageOfServer) {
        multiGameWebSocketServer.sendEventOfServer(clientId, transportPackageOfServer);
    }
}
