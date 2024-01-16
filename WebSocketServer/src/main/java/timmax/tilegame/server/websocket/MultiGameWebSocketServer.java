package timmax.tilegame.server.websocket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server.*;
import timmax.tilegame.transport.TransportOfServer;

public class MultiGameWebSocketServer extends WebSocketServer implements TransportOfServer<WebSocket> {
    private final ObjectMapperOfMvtg mapper;
    // ToDo: Здесь содержится и переменная и её инициализация, т.к. предполагается, что при старте сервера нужно
    //       один раз прочитать файл с перечнем классов с моделями.
    //       Но будет даже лучше убрать отсюда и переменную и вызов инициализации и перенести её в логику
    //       EventOfServer21GetGameTypeSet, и пусть там, при запросе от клиента, каждый раз считывается файл.
    //       Тогда можно будет без остановки сервера внести изменения в файл, и сервер узнает о других моделях (или
    //       "забудет" неиспользующуеся более).
    private final Set<ModelOfServerDescriptor> collectionOfModelOfServerDescriptor;
    private final Map<WebSocket, RemoteClientState<WebSocket>> mapOfRemoteClientState;

    public MultiGameWebSocketServer(int port) {
        super(new InetSocketAddress(port));
        mapper = new ObjectMapperOfMvtg();
        mapOfRemoteClientState = new HashMap<>();

        ModelOfServerLoader modelLoader = null;
        try {
            modelLoader = new ModelOfServerLoader(
                    Paths.get(
                            Objects.requireNonNull(ModelOfServerLoader.class.getResource("models.txt")).toURI()
                    )
            );
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.exit(1);
        }
        collectionOfModelOfServerDescriptor = modelLoader.getCollectionOfModelOfServerDescriptor();
    }

    // Overiden methods from class WebSocketServer:
    @Override
    public void onStart() {
        System.out.println("onStart()");
        System.out.println("  MultiGameWebSocketServer started on port: " + getPort() + ".");
        System.out.println("---------- End of onStart");
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        System.out.println("onClose(WebSocket, int, String, boolean)");
        System.out.println("  " + webSocket);
        System.out.println("  Connect was closed.");
        System.out.println("  Code = " + code + ". Reason = " + reason + ". Remote = " + remote + ".");
        mapOfRemoteClientState.remove(webSocket);
        System.out.println("---------- End of onClose");
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        System.out.println("onOpen(WebSocket, ClientHandshake)");
        System.out.println("  " + webSocket);
        mapOfRemoteClientState.put(webSocket, new RemoteClientState<>(this, webSocket));
        System.out.println("---------- End of onOpen");
    }

    @Override
    public void onError(WebSocket webSocket, Exception ex) {
        System.err.println("onError(WebSocket, Exception)");
        System.err.println("  " + webSocket);
        ex.printStackTrace();
        System.err.println("---------- End of onError");
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        System.out.println("onMessage(WebSocket, ByteBuffer)");
        System.out.println("  " + webSocket);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        EventOfClient eventOfClient = mapper.readValue(byteArrayInputStream, EventOfClient.class);

        System.out.println("  eventOfClient = " + eventOfClient);
        System.out.println("---------- End of onMessage(WebSocket, ByteBuffer)");

        Thread thread = new Thread(() -> eventOfClient.executeOnServer(this, webSocket));
        thread.start();
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        System.err.println("onMessage(WebSocket, String)");
        System.err.println("  " + webSocket);
        System.err.println("  This type of message (String) should not be!");
        System.exit(1);
    }

    // Overiden methods from interface TransportOfServer:
    @Override
    public void sendGameEventToRemoteView(RemoteView<WebSocket> remoteView, GameEvent gameEvent) {
        EventOfServer eventOfServer = new EventOfServer92GameEvent(remoteView.getViewId(), gameEvent);
        sendEventOfServer(remoteView.getClientId(), eventOfServer);
    }

    @Override
    public void sendEventOfServer(WebSocket clientId, EventOfServer eventOfServer) {
        System.out.println("  send(WebSocket, EventOfServer<WebSocket>)");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.out.println("    eventOfServer = " + eventOfServer);
        mapper.writeValue(byteArrayOutputStream, eventOfServer);
        clientId.send(byteArrayOutputStream.toByteArray());
        System.out.println("---------- End of send(WebSocket, EventOfServer<WebSocket>)");
    }

    @Override
    public RemoteClientState<WebSocket> getRemoteClientStateByClientId(WebSocket clientId) {
        return mapOfRemoteClientState.get(clientId);
    }

    @Override
    public Set<ModelOfServerDescriptor> getCollectionOfModelOfServerDescriptor() {
        return collectionOfModelOfServerDescriptor;
    }
}
