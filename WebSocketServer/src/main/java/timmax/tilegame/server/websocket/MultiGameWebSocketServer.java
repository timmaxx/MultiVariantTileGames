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
import timmax.tilegame.basemodel.credential.User;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.EventOfServer92GameEvent;
import timmax.tilegame.basemodel.protocol.server.MatchPlayerList;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfServer;

//  ToDo:   Разделить на два класса:
//          1. MultiGameWebSocketServer
//          2. SenderOfEventOfServer
public class MultiGameWebSocketServer extends WebSocketServer implements TransportOfServer {
    private static final Logger logger = LoggerFactory.getLogger(MultiGameWebSocketServer.class);

    private final ObjectMapperOfMvtg mapper;
    private final Map<WebSocket, RemoteClientStateAutomaton> webSocket_RemoteClientStateAutomaton_Map;

    public MultiGameWebSocketServer(int port) {
        super(new InetSocketAddress(port));
        mapper = new ObjectMapperOfMvtg();
        webSocket_RemoteClientStateAutomaton_Map = new HashMap<>();
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
        webSocket_RemoteClientStateAutomaton_Map.remove(webSocket);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        logger.info("WebSocket: {}. Connection was opened.", webSocket);
        logger.debug("  ClientHandshake: {}.", clientHandshake);
        // ToDo: Устранить взаимозависимость интерфейса IFabricOfClientStates и класса ClientStateAutomaton.
        //       См. коммент к IFabricOfClientStates
        webSocket_RemoteClientStateAutomaton_Map.put(
                webSocket,
                // ToDo: Внедрить двустороннюю мапу (https://coderlessons.com/articles/java/google-guava-bimaps)
                // ToDo: Сейчас в конструктор RemoteClientStateAutomaton передаются несколько параметров, некоторые из
                //       них не являются необходимыми (см. комментарий перед каждым параметром):
                new RemoteClientStateAutomaton(
                        webSocket,
                        new FabricOfRemoteClientStates(),
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
        Thread thread = new Thread(
                () -> eventOfClient.executeOnServer(webSocket_RemoteClientStateAutomaton_Map.get(webSocket))
        );
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
    //  ToDo:   Все методы ниже (send...()) лучше вынести из этого класса, т.к. они:
    //          1. не используются в методах выше (это методы, которые вызываются при возникновении событий,
    //             связанных с WebSocketServer).
    //          2.1. методы send...() вызываются тогда, когда приложение собирается что-то отправить корреспондентам
    //               (т.е. каким-то WebSocket),
    //          2.2. и даже WebSocket не является ни предком ни наследником WebSocketServer.
    @Override
    public void sendEventOfServer(WebSocket webSocket, EventOfServer eventOfServer) {
        logger.info("WebSocket: {}. Outcoming message. EventOfServer: {}.", webSocket, eventOfServer);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mapper.writeValue(byteArrayOutputStream, eventOfServer);
        webSocket.send(byteArrayOutputStream.toByteArray());
    }

    //  Не стал определять этот метод в interface TransportOfServer, поэтому не @Override.
    private void sendEventOfServer(User user, EventOfServer eventOfServer) {
        if (user == null) {
            logger.error("user is null.");
            throw new RuntimeException("user is null.");
        }
        for (var webSocket_RemoteClientStateAutomaton : webSocket_RemoteClientStateAutomaton_Map.entrySet()) {
            if (webSocket_RemoteClientStateAutomaton.getValue().getUser().equals(user)) {
                sendEventOfServer(webSocket_RemoteClientStateAutomaton.getKey(), eventOfServer);
            }
        }
    }

    @Override
    public void sendEventOfServer(MatchPlayerList matchPlayerList, EventOfServer eventOfServer) {
        if (matchPlayerList == null || matchPlayerList.size() == 0) {
            logger.error("matchPlayerList is null or empty.");
            throw new RuntimeException("matchPlayerList is null or empty.");
        }
        for (int i = 0; i < matchPlayerList.size(); i++) {
            sendEventOfServer(matchPlayerList.get(i), eventOfServer);
        }
    }

    @Override
    public void sendGameEventToAllViews(
            MatchPlayerList matchPlayerList,
            GameEvent gameEvent,
            Map<String, Class<? extends View>> viewName_ViewClassMap) {
        for (String viewName : viewName_ViewClassMap.keySet()) {
            sendEventOfServer(matchPlayerList, new EventOfServer92GameEvent(viewName, gameEvent));
        }
    }
}
