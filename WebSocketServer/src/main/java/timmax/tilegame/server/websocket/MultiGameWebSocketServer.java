package timmax.tilegame.server.websocket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.protocol.ObjectMapperOfMvtg;
import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;
import timmax.tilegame.transport.TransportOfServer;

public class MultiGameWebSocketServer extends WebSocketServer implements TransportOfServer<WebSocket> {
    private static final Logger logger = LoggerFactory.getLogger(MultiGameWebSocketServer.class);

    private final ObjectMapperOfMvtg mapper;

    // ToDo: Удалить комментарий после решения проблеммы, указанной в нём.
    //       Здесь используется только
    //       RemoteClientStateAutomaton<WebSocket>> mapOfRemoteClientState
    //       и это хорошо!
    //       Но в
    //       MultiGameClient :: void start(Stage primaryStage)
    //       есть одновременно
    //       LocalClientStateAutomaton localClientStateJfx
    //       и
    //       IModelOfClient iModelOfClient
    //       что не есть хорошо, т.к. не единообразно!
    private final Map<WebSocket, RemoteClientStateAutomaton<WebSocket>> mapOfRemoteClientState;

    public MultiGameWebSocketServer(int port) {
        super(new InetSocketAddress(port));
        mapper = new ObjectMapperOfMvtg();
        mapOfRemoteClientState = new HashMap<>();
    }

    // Overriden methods from class WebSocketServer:
    @Override
    public void onStart() {
        logger.info("The server was started on port: {}.", getPort());
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        logger.info("WebSocket: {}. Connection was closed.", webSocket);
        logger.debug("  Code: {}. Reason: {}. Remote: {}.", code, reason, remote);
        mapOfRemoteClientState.remove(webSocket);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        logger.info("WebSocket: {}. Connection was opened.", webSocket);
        logger.debug("  ClientHandshake: {}.", clientHandshake);
        mapOfRemoteClientState.put(
                webSocket,
                new RemoteClientStateAutomaton<>(
                        new FabricOfRemoteClientStates<>(),
                        new FabricOfRemoteClientStateAutomaton(),
                        this,
                        // ToDo: Удалить этот параметр, т.к. в мапу и так он попадает (см. несколько строк выше).
                        webSocket
                )
        );
    }

    @Override
    public void onError(WebSocket webSocket, Exception ex) {
        logger.error("WebSocket: {}. Error occurred.", webSocket, ex);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        // ToDo: А вдруг здесь от клиента прилетит что-то не EventOfClient?
        //       Тогда нужно обрабатывать исключение и выводить в лог.
        // ToDo: Обработать Warning:
        //       Warning:(74, 50) Unchecked assignment: 'timmax.tilegame.basemodel.protocol.EventOfClient' to 'timmax.tilegame.basemodel.protocol.EventOfClient<org.java_websocket.WebSocket>'
        EventOfClient<WebSocket> eventOfClient = mapper.readValue(byteArrayInputStream, EventOfClient.class);
        logger.info("WebSocket: {}. Incoming message. EventOfClient: {}.", webSocket, eventOfClient);
        Thread thread = new Thread(() -> eventOfClient.executeOnServer(mapOfRemoteClientState.get(webSocket)));
        thread.start();
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        // Входящее сообщение, как строка, не предполагается. Поэтому логировать будем как предупреждение.
        // Т.е. сервер должен залогировать, игнорировать такое входящее сообщение и продолжить работу.
        logger.warn("WebSocket: {}. Incoming message. This type of message (String) should not be!", webSocket);
        // ToDo: Но поскольку, от какого-то клиента поступило такое сообщение, то этого клиента желательно отключить.
    }

    // Overriden methods from interface TransportOfServer:
    @Override
    public void sendEventOfServer(WebSocket webSocket, EventOfServer eventOfServer) {
        logger.info("WebSocket: {}. Outcoming message. EventOfServer: {}.", webSocket, eventOfServer);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mapper.writeValue(byteArrayOutputStream, eventOfServer);
        webSocket.send(byteArrayOutputStream.toByteArray());
    }
}
