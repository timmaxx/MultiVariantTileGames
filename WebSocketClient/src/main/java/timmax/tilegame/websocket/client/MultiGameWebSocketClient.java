package timmax.tilegame.websocket.client;

import java.io.*;
import java.net.URI;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfController;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public class MultiGameWebSocketClient extends WebSocketClient implements TransportOfController<WebSocket> {
    final ObjectMapperOfMvtg mapper = new ObjectMapperOfMvtg();
    final ClientState<Object> clientState;
    final HashSetOfObserverOnAbstractEvent hashSetOfObserverOnAbstractEvent;


    public MultiGameWebSocketClient(URI serverUri, ClientState<Object> clientState, HashSetOfObserverOnAbstractEvent hashSetOfObserverOnAbstractEvent) {
        super(serverUri);
        this.clientState = clientState;
        this.hashSetOfObserverOnAbstractEvent = hashSetOfObserverOnAbstractEvent;
        System.out.println(serverUri);
    }

    public MainGameClientStatus getMainGameClientStatus() {
        if (!isOpen() || isClosed()) {
            return MainGameClientStatus.NO_CONNECT;
        }
        if (isOpen()) {
            return clientState.getMainGameClientStatus();
        }
        throw new RuntimeException("Unknown state.");
    }

    // 2
    public void logout() {
        System.out.println("logout()");
        send(new TransportPackageOfClient010Logout<>());
    }

    public void login(String userName, String password) {
        System.out.println("login(String, String)");
        send(new TransportPackageOfClient011Login<>(userName, password));
    }

    // 3
    public void forgetGameTypeSet() {
        System.out.println("forgetGameTypeSet()");
        send(new TransportPackageOfClient020ForgetGameTypeSet<>());
    }

    public void getGameTypeSet() {
        System.out.println("getGameTypeSet()");
        send(new TransportPackageOfClient021GetGameTypeSet<>());
    }

    // 4
    public void forgetGameType() {
        send(new TransportPackageOfClient30ForgetGameType<>());
    }

    public void gameTypeSelect(String serverBaseModelClass) {
        send(new TransportPackageOfClient31GameTypeSelect<>(serverBaseModelClass));
    }

    // 9
    public void addView(View view) {
        System.out.println("addView(View view)");
        System.out.println("viewId = " + view.toString());
        clientState.addView(view.toString());
        send(new TransportPackageOfClient91AddView<>(view.toString()));
    }
/*
    public void createNewGame() {
        System.out.println("createNewGame");
        send(new TransportPackageOfClient(CREATE_NEW_GAME));
    }
*/
    @Override
    public void send(TransportPackageOfClient<WebSocket> transportPackageOfClient) {
        System.out.println("  send(TransportPackageOfClient<WebSocket>)");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mapper.writeValue(byteArrayOutputStream, transportPackageOfClient);
        System.out.println("    transportPackageOfClient = " + transportPackageOfClient);
        send(byteArrayOutputStream.toByteArray());
        System.out.println("---------- End of public void send(TransportPackageOfClient<WebSocket> transportPackageOfClient)");
    }

    @Override
    public void sendCommand(GameCommand gameCommand) {
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("onClose");

        clientState.setUserName("");
        System.out.println("  getMainGameClientStatus() = " + getMainGameClientStatus());
        hashSetOfObserverOnAbstractEvent.updateConnectStatePane(CLOSE);

        System.out.println("  Connect was closed.");
        System.out.println("  Code = " + code + ". Reason = " + reason + ". Remote = " + remote + ".");
        System.out.println("---------- End of onClose");
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("onOpen(ServerHandshake)");

        clientState.setUserName("");
        System.out.println("  getMainGameClientStatus() = " + getMainGameClientStatus());
        hashSetOfObserverOnAbstractEvent.updateConnectStatePane(OPEN);

        System.out.println("---------- End of onOpen");
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("onError(Exception)");

        ex.printStackTrace();
        System.err.println("---------- End of onError");
    }

    @Override
    public void onMessage(ByteBuffer byteBuffer) {
        System.out.println("onMessage(ByteBuffer)");

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());

        TransportPackageOfServer<WebSocket> transportPackageOfServer = mapper.readValue(byteArrayInputStream, TransportPackageOfServer.class);

        System.out.println("  transportPackageOfServer = " + transportPackageOfServer);

        Thread thread = new Thread(() -> {
            transportPackageOfServer.execute(this);
            System.out.println("  getMainGameClientStatus() = " + getMainGameClientStatus());
            System.out.println("---------- End of onMessage(ByteBuffer)");
        });
        thread.start();
    }
/*
    private void onGameEvent(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onGameEvent");

        String viewId = (String) (transportPackageOfServer.get("viewId"));
        System.out.println("viewId = " + viewId);

        GameEvent gameEvent = (GameEvent) (transportPackageOfServer.get("gameEvent"));
        System.out.println("gameEvent = " + gameEvent);
    }
*/

    @Override
    public void onMessage(String message) {
        System.err.println("onMessage(String)");
        System.err.println("This type of message (String) should not be!");
        System.exit(1);
    }

    @Override
    public ClientState<Object> getClientState() {
        return clientState;
    }

    @Override
    public HashSetOfObserverOnAbstractEvent getHashSetOfObserverOnAbstractEvent() {
        return hashSetOfObserverOnAbstractEvent;
    }
}