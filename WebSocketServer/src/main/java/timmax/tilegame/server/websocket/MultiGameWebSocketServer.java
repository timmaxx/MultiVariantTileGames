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
import timmax.tilegame.basemodel.protocol.server.RemoteClientState;
import timmax.tilegame.transport.TransportOfServer;

public class MultiGameWebSocketServer extends WebSocketServer implements TransportOfServer<WebSocket> {
    private static final Logger logger = LoggerFactory.getLogger(MultiGameWebSocketServer.class);

    private final ObjectMapperOfMvtg mapper;
    private final Map<WebSocket, RemoteClientState<WebSocket>> mapOfRemoteClientState;

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
        logger.debug("  ClientHandshake: {}", clientHandshake);
        mapOfRemoteClientState.put(webSocket, new RemoteClientState<>(this, webSocket));
    }

    @Override
    public void onError(WebSocket webSocket, Exception ex) {
        logger.error("WebSocket: {}. There is error.", webSocket, ex);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        // ToDo: А вдруг здесь от клиента прилетит что-то не EventOfClient?
        //       Тогда нужно обрабатывать исключение и выводить в лог.
        EventOfClient eventOfClient = mapper.readValue(byteArrayInputStream, EventOfClient.class);
        logger.info("WebSocket: {}. A message was received. EventOfServer: {}", webSocket, eventOfClient);
        Thread thread = new Thread(() -> eventOfClient.executeOnServer(mapOfRemoteClientState.get(webSocket)));
        thread.start();
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        // Входящее сообщение как строка не предполагается. Поэтому логировать будем как предупреждение.
        // Т.е. сервер должен залогировать, игнорировать такое входящее сообщение и продолжить работу.
        logger.warn("WebSocket: {}. A message was received. This type of message (String) should not be!", webSocket);
        // ToDo: Но поскольку от какого-то клиента поступило такое сообщение, то его желательно отключить.
    }

    // Overriden methods from interface TransportOfServer:
    @Override
    public void sendEventOfServer(WebSocket webSocket, EventOfServer eventOfServer) {
        logger.info("WebSocket: {}. Outcoming message. EventOfServer: {}", webSocket, eventOfServer);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mapper.writeValue(byteArrayOutputStream, eventOfServer);
        webSocket.send(byteArrayOutputStream.toByteArray());
    }
}
