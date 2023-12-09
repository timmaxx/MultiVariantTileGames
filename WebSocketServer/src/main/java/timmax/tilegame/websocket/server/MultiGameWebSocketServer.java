package timmax.tilegame.websocket.server;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Map;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server.ModelOfServer;
import timmax.tilegame.basemodel.protocol.server.RemoteView;
import timmax.tilegame.transport.TransportOfModel;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public class MultiGameWebSocketServer extends WebSocketServer implements TransportOfModel<WebSocket> {
    ModelOfServer<WebSocket> modelOfServer;

    private ObjectOutput objectOutput;


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
        TransportPackageOfServer transportPackageOfServer = new TransportPackageOfServer(
                GAME_EVENT,
                Map.of("viewId", remoteView.getViewId(),
                        "gameEvent", gameEvent)
        );
        send(remoteView.getClientId(), transportPackageOfServer);
    }

    public void encodeExternalizable(Externalizable externalizable) throws IOException {
        objectOutput.writeObject(externalizable);
    }

    void send(WebSocket webSocket, TransportPackageOfServer transportPackageOfServer) {
        System.out.println("private void send(WebSocket webSocket, TransportPackageOfServer transportPackageOfServer)");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            objectOutput = new ObjectOutputStream(byteArrayOutputStream); // new ObjectOutputStream(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            encodeExternalizable(transportPackageOfServer);
            webSocket.send(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            System.err.println("catch (IOException e)");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
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

        new IncomingMessageHandler(this, webSocket, byteBuffer);
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        System.err.println("onMessage(WebSocket webSocket, String message)");
        System.err.println(webSocket);
        System.err.println("This type of message (String) should not be!");
        System.exit(1);
    }
}