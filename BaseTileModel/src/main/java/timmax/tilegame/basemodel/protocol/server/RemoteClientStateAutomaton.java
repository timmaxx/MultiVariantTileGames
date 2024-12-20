package timmax.tilegame.basemodel.protocol.server;

import org.java_websocket.WebSocket;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.transport.ISenderOfEventOfServer;

//  Автомат состояний клиента, работающий на сервере и учитывающий состояния удалённых клиентов.
public class RemoteClientStateAutomaton extends ClientStateAutomaton<IGameMatch> {
    private final WebSocket clientId;

    // ToDo: Удалить ISenderOfEventOfServer senderOfEventOfServer.
    //       Вместо того, чтобы хранить переменную типа ISenderOfEventOfServer здесь, нужно рассмотреть вариант по передаче
    //       её как параметра в те методы, где она нужна.
    // ToDo: Удалить ISenderOfEventOfServer senderOfEventOfServer.
    //       1. ISenderOfEventOfClient - LocalClientStateAutomaton:
    //         - В SenderOfEventOfClient есть переменная LocalClientStateAutomaton.
    //         - В LocalClientStateAutomaton нет переменной ISenderOfEventOfClient (или SenderOfEventOfClient).
    //           И как-то без этого обходится!
    //       2. ISenderOfEventOfServer - Map<WebSocket, RemoteClientStateAutomaton<WebSocket>>
    //         - В MultiGameWebSocketServer есть мапа WebSocket -> RemoteClientStateAutomaton
    //         - В RemoteClientStateAutomaton есть переменная ISenderOfEventOfServer.
    //           Почему-же здесь нужна эта переменная?
    // ToDo: Привести к единому виду взаимоиспользование:
    //       - на клиенте переменных типов ISenderOfEventOfClient и LocalClientStateAutomaton,
    //       - на сервере переменных типов ISenderOfEventOfServer и RemoteClientStateAutomaton.
    //       Переменные типов ISenderOfEventOfClient и LocalClientStateAutomaton на клиенте на одном уровне.
    //       А для сервера переменная типа ISenderOfEventOfServer входит в состав RemoteClientStateAutomaton.
    private final ISenderOfEventOfServer senderOfEventOfServer;

    public RemoteClientStateAutomaton(
            WebSocket clientId,
            IFabricOfRemoteClientStates fabricOfClientStatesForServer,
            //  ToDo:   Удалить.
            ISenderOfEventOfServer senderOfEventOfServer) {
        super(fabricOfClientStatesForServer);
        this.clientId = clientId;
        //  ToDo:   Удалить.
        this.senderOfEventOfServer = senderOfEventOfServer;
        changeStateFrom01To02_();
    }

    public WebSocket getClientId() {
        return clientId;
    }

    //  ToDo:   Удалить.
    public ISenderOfEventOfServer getTransportOfServer() {
        return senderOfEventOfServer;
    }

    @Override
    //  Warning:(56, 12) Raw use of parameterized class 'GameMatch'
    public GameMatch getGameMatchX() {
        //  Warning:(58, 17) Raw use of parameterized class 'GameMatch'
        return (GameMatch) super.getGameMatchX();
    }
}
