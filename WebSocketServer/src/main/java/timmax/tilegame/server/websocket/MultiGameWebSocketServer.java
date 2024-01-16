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

import timmax.tilegame.basemodel.protocol.ObjectMapperOfMvtg;
import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.server.RemoteClientState;
import timmax.tilegame.transport.TransportOfServer;

public class MultiGameWebSocketServer extends WebSocketServer implements TransportOfServer<WebSocket> {
    private final ObjectMapperOfMvtg mapper;
    private final Map<WebSocket, RemoteClientState<WebSocket>> mapOfRemoteClientState;

    public MultiGameWebSocketServer(int port) {
        super(new InetSocketAddress(port));
        mapper = new ObjectMapperOfMvtg();
        mapOfRemoteClientState = new HashMap<>();
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
}
