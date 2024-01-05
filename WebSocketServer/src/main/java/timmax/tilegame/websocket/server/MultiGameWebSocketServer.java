package timmax.tilegame.websocket.server;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.EventOfServer92GameEvent;
import timmax.tilegame.basemodel.protocol.ObjectMapperOfMvtg;
import timmax.tilegame.basemodel.protocol.server.ModelOfServer;
import timmax.tilegame.basemodel.protocol.server.RemoteView;
import timmax.tilegame.transport.TransportOfServer;

import timmax.tilegame.game.minesweeper.model.ModelOfServerOfMinesweeper;

public class MultiGameWebSocketServer extends WebSocketServer implements TransportOfServer<WebSocket> {
    private final ObjectMapperOfMvtg mapper = new ObjectMapperOfMvtg();

    private ModelOfServer<WebSocket> modelOfServer;

    public MultiGameWebSocketServer(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void sendGameEvent(GameEvent gameEvent) {
        throw new RuntimeException("Не использовать этот метод. Потом удалить его из классов и интерфейсов!");
    }

    @Override
    public void sendGameEvent(RemoteView<WebSocket> remoteView, GameEvent gameEvent) {
        EventOfServer eventOfServer = new EventOfServer92GameEvent(remoteView.getViewId(), gameEvent);
        send(remoteView.getClientId(), eventOfServer);
    }

    @Override
    public void send(WebSocket clientId, EventOfServer eventOfServer) {
        System.out.println("  send(WebSocket, EventOfServer<WebSocket>)");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.out.println("    eventOfServer = " + eventOfServer);
        mapper.writeValue(byteArrayOutputStream, eventOfServer);
        clientId.send(byteArrayOutputStream.toByteArray());
        System.out.println("---------- End of send(WebSocket, EventOfServer<WebSocket>)");
    }

    @Override
    public ModelOfServer<WebSocket> getModelOfServer() {
        return modelOfServer;
    }

    @Override
    public void setModelOfServerTmp() {
        // Здесь нужно динамически выбирать модель.
        // this.modelOfServer = new ModelOfServerOfSokoban<>(this);
        this.modelOfServer = new ModelOfServerOfMinesweeper<>(this);
    }

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
        System.out.println("---------- End of onClose");
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        System.out.println("onOpen(WebSocket, ClientHandshake)");
        System.out.println("  " + webSocket);
        // ToDo: Для каждого соединения можно создавать отдельный поток-нить.
        //       Соответственно, нужна карта, в которой будет храниться:
        //       webSocket, нить, модель игры.
        //       Но, вполне возможно, что это и так уже делается ядром WinSocket...
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
}
