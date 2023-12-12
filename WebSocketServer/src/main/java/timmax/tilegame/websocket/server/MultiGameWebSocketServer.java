package timmax.tilegame.websocket.server;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server.ModelOfServer;
import timmax.tilegame.basemodel.protocol.server.RemoteView;
import timmax.tilegame.game.sokoban.model.ModelOfServerOfSokoban;
import timmax.tilegame.transport.TransportOfModel;

public class MultiGameWebSocketServer extends WebSocketServer implements TransportOfModel<WebSocket> {
    final ObjectMapperOfMvtg mapper = new ObjectMapperOfMvtg();

    ModelOfServer<WebSocket> modelOfServer;


    public MultiGameWebSocketServer(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void sendGameEvent(GameEvent gameEvent) {
        throw new RuntimeException("Не использовать этот метод. Потом удалить его из классов и интерфейсов!");
    }

    @Override
    public void sendGameEvent(RemoteView<WebSocket> remoteView, GameEvent gameEvent) {
/*
        System.out.println("MultiGameWebSocketServer");
        System.out.println("public void sendGameEvent(RemoteView<WebSocket> remoteView, GameEvent gameEvent)");
        System.out.println("remoteView = " + remoteView);
*/

/*
        TransportPackageOfServer transportPackageOfServer = new TransportPackageOfServer(
                GAME_EVENT,
                Map.of("viewId", remoteView.getViewId(),
                        "gameEvent", gameEvent)
        );
        send(remoteView.getClientId(), transportPackageOfServer);
*/
    }

    @Override
    public void send(WebSocket clientId, TransportPackageOfServer<WebSocket> transportPackageOfServer) {
        // System.out.println("class MultiGameWebSocketServer");
        // System.out.println("public void send(WebSocket clientId, TransportPackageOfServer transportPackageOfServer)");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mapper.writeValue(byteArrayOutputStream, transportPackageOfServer);
        System.out.println("transportPackageOfServer = " + transportPackageOfServer);
        clientId.send(byteArrayOutputStream.toByteArray());
    }

    @Override
    public ModelOfServer<WebSocket> getModelOfServer() {
        return modelOfServer;
    }

    @Override
    public void setModelOfServer(ModelOfServer<WebSocket> modelOfServer) {
        this.modelOfServer = modelOfServer;
    }

    @Override
    public void setModelOfServerTmp() {
        this.modelOfServer = new ModelOfServerOfSokoban<>(this);
    }

    @Override
    public void onStart() {
        System.out.println("MultiGameWebSocketServer started on port: " + getPort() + ".");
        System.err.println("---------- End of onStart");
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        System.out.println("onClose");
        System.out.println(webSocket + ".");
        System.out.println("Connect was closed.");
        System.out.println("Code = " + code + ". Reason = " + reason + ". Remote = " + remote + ".");
        System.out.println("---------- End of onClose");
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        System.out.println("onOpen");
        // System.out.println("Connect from '" + webSocket + "' are opened.");
        System.out.println(webSocket + ".");
        // System.out.println(webSocket + ". Connect are opened.");
        // ToDo: Для каждого соединения можно создавать отдельный поток-нить.
        //       Соответственно, нужна карта, в которой будет храниться:
        //       webSocket, нить, модель игры.
        //       Но, вполне возможно, что это и так уже делается ядром WinSocket...
        System.out.println("---------- End of onOpen");
    }

    @Override
    public void onError(WebSocket webSocket, Exception ex) {
        System.err.println("onError");
        System.err.println(webSocket + ".");
        ex.printStackTrace();
        System.err.println("---------- End of onError");
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        System.out.println("onMessage(WebSocket webSocket, ByteBuffer byteBuffer)");
        System.out.println(webSocket);
        System.out.println("---------- End of onMessage(WebSocket webSocket, ByteBuffer byteBuffer)");

        new ServerIncomingMessageHandler(this, webSocket, byteBuffer);
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        System.err.println("onMessage(WebSocket webSocket, String message)");
        System.err.println(webSocket);
        System.err.println("This type of message (String) should not be!");
        System.exit(1);
    }
}