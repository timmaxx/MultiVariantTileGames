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
        logger.info("onStart()");
        logger.info("  MultiGameWebSocketServer started on port: {}.", getPort());
        logger.info("---------- End of onStart");
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        logger.info("onClose(WebSocket, int, String, boolean)");
        logger.info("  {}", webSocket);
        logger.info("  Connect was closed.");
        logger.info("  Code = {}. Reason = {}. Remote = {}.", code, reason, remote);
        mapOfRemoteClientState.remove(webSocket);
        logger.info("---------- End of onClose");
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        logger.info("onOpen(WebSocket, ClientHandshake)");
        logger.info("  {}", webSocket);
        mapOfRemoteClientState.put(webSocket, new RemoteClientState<>(this, webSocket));
        logger.info("---------- End of onOpen");
    }

    @Override
    public void onError(WebSocket webSocket, Exception ex) {
        logger.info("onError(WebSocket, Exception)");
        logger.info("  {}", webSocket);
        ex.printStackTrace();
        logger.info("---------- End of onError");
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        logger.info("onMessage(WebSocket, ByteBuffer)");
        logger.info("  {}", webSocket);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        EventOfClient eventOfClient = mapper.readValue(byteArrayInputStream, EventOfClient.class);

        logger.info("  eventOfClient = {}", eventOfClient);
        logger.info("---------- End of onMessage(WebSocket, ByteBuffer)");

        Thread thread = new Thread(() -> eventOfClient.executeOnServer(mapOfRemoteClientState.get(webSocket)));
        thread.start();
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        // Входящее сообщение как строка не предполагается. Поэтому логировать будем как предупреждение.
        // Т.е. сервер должен залогировать, игнорировать такое входящее сообщение и продолжить работу.
        logger.warn("onMessage(WebSocket, String)");
        logger.warn("  {}", webSocket);
        logger.warn("  This type of message (String) should not be!");
        // ToDo: Но поскольку от какого-то клиента поступило такое сообщение, то его желательно отключить.
    }

    // Overriden methods from interface TransportOfServer:
    @Override
    public void sendEventOfServer(WebSocket clientId, EventOfServer eventOfServer) {
        logger.info("  sendEventOfServer(WebSocket, EventOfServer)");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        logger.info("    eventOfServer = {}", eventOfServer);
        mapper.writeValue(byteArrayOutputStream, eventOfServer);
        clientId.send(byteArrayOutputStream.toByteArray());
        logger.info("---------- End of send(WebSocket, EventOfServer<WebSocket>)");
    }
}
