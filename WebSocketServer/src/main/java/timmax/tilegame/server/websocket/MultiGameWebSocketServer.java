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

import timmax.common.ObjectMapperOfMvtg;
import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;
import timmax.tilegame.transport.TransportOfServer;

public class MultiGameWebSocketServer extends WebSocketServer implements TransportOfServer {
    private static final Logger logger = LoggerFactory.getLogger(MultiGameWebSocketServer.class);

    private final ObjectMapperOfMvtg mapper;
    private final Map<WebSocket, RemoteClientStateAutomaton<WebSocket>> webSocketAndRemoteClientStateAutomatonMap;

    public MultiGameWebSocketServer(int port) {
        super(new InetSocketAddress(port));
        mapper = new ObjectMapperOfMvtg();
        webSocketAndRemoteClientStateAutomatonMap = new HashMap<>();
    }

    // class WebSocketServer:
    @Override
    public void onStart() {
        logger.info("The server was started on port: {}.", getPort());
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        logger.info("WebSocket: {}. Connection was closed.", webSocket);
        logger.debug("  Code: {}. Reason: {}. Remote: {}.", code, reason, remote);
        webSocketAndRemoteClientStateAutomatonMap.remove(webSocket);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        logger.info("WebSocket: {}. Connection was opened.", webSocket);
        logger.debug("  ClientHandshake: {}.", clientHandshake);
        // ToDo: Устранить взаимозависимость интерфейса IFabricOfClientStates и класса ClientStateAutomaton.
        //       См. коммент к IFabricOfClientStates
        webSocketAndRemoteClientStateAutomatonMap.put(
                webSocket,
                // ToDo: Внедрить двустороннюю мапу (https://coderlessons.com/articles/java/google-guava-bimaps)
                // ToDo: Сейчас в конструктор RemoteClientStateAutomaton передаются несколько параметров, некоторые из
                //       них не являются необходимыми (см. комментарий перед каждым параметром):
                new RemoteClientStateAutomaton<>(
                        webSocket,
                        new FabricOfRemoteClientStates<>(),
                        this
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
        EventOfClient eventOfClient = mapper.readValue(byteArrayInputStream);
        logger.info("WebSocket: {}. Incoming message. EventOfClient: {}.", webSocket, eventOfClient);
        // ToDo: События, поступающие от разных клиентов (webSocket) нужно складывать в очередь и обрабатывать после
        //       того, как будут обработаны предыдущие.
        //       Также см. комментарий в EventOfServer92GameEvent :: void executeOnClient(...).
        Thread thread = new Thread(() -> eventOfClient.executeOnServer(webSocketAndRemoteClientStateAutomatonMap.get(webSocket)));
        thread.start();
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        // Входящее сообщение, как строка, не предполагается. Поэтому логировать будем как предупреждение.
        // Т.е. сервер должен залогировать, игнорировать такое входящее сообщение и продолжить работу.
        logger.warn("WebSocket: {}. Incoming message. This type of message (String) should not be!", webSocket);
        // ToDo: Но поскольку, от какого-то клиента поступило такое сообщение, то этого клиента желательно отключить.
    }

    // interface TransportOfServer:
    @Override
    // public void sendEventOfServer(WebSocket webSocket, EventOfServer eventOfServer)
    // ToDo: Было-бы красивее, если-бы вместо
    //       <ClientId>
    //       можно было-бы написать
    //       <ClientId extends WebSocket>
    //       Тогда не пришлось-бы делать
    //       if (webSocket instanceof WebSocket ws)
    // public <ClientId extends WebSocket> void sendEventOfServer(ClientId webSocket, EventOfServer eventOfServer) {
    public <ClientId> void sendEventOfServer(ClientId webSocket, EventOfServer eventOfServer) {
        logger.info("WebSocket: {}. Outcoming message. EventOfServer: {}.", webSocket, eventOfServer);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mapper.writeValue(byteArrayOutputStream, eventOfServer);

        if (webSocket instanceof WebSocket ws) {
            ws.send(byteArrayOutputStream.toByteArray());
        } else {
            logger.error("Variable webSocket is not of type WebSocket!");
            throw new RuntimeException("Variable webSocket is not of type WebSocket!");
        }
        // ToDo: См. выше про <ClientId extends WebSocket>
        // webSocket.send(byteArrayOutputStream.toByteArray());
    }
}
