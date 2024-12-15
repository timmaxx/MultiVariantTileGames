package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.transport.TransportOfServer;

//  Автомат состояний клиента, работающий на сервере и учитывающий состояния удалённых клиентов.
public class RemoteClientStateAutomaton<ClientId> extends ClientStateAutomaton<IGameMatch> {
    private final ClientId clientId;

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
    // ToDo: Привести к единому виду взаимоиспользование:
    //       - на клиенте переменных типов TransportOfClient и LocalClientStateAutomaton,
    //       - на сервере переменных типов TransportOfServer и RemoteClientStateAutomaton.
    //       Переменные типов TransportOfClient и LocalClientStateAutomaton на клиенте на одном уровне.
    //       А для сервера переменная типа TransportOfServer входит в состав RemoteClientStateAutomaton.
    private final TransportOfServer multiGameWebSocketServer;

    public RemoteClientStateAutomaton(
            ClientId clientId,
            IFabricOfRemoteClientStates<ClientId> fabricOfClientStatesForServer,
            //  ToDo:   Удалить.
            TransportOfServer multiGameWebSocketServer) {
        super(fabricOfClientStatesForServer);
        this.clientId = clientId;
        this.multiGameWebSocketServer = multiGameWebSocketServer;
        changeStateFrom01To02_();
    }

    public ClientId getClientId() {
        return clientId;
    }

    //  ToDo:   Удалить.
    public TransportOfServer getTransportOfServer() {
        return multiGameWebSocketServer;
    }

    @Override
    public GameMatch getGameMatchX() {
        return (GameMatch) super.getGameMatchX();
    }
}
