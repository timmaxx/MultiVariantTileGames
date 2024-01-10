package timmax.tilegame.client.websocket;

import java.io.*;
import java.net.URI;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.gamecommand.GameCommandNewGame;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.basemodel.protocol.client.LocalClientState;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.basemodel.protocol.TypeOfEvent.*;

public class MultiGameWebSocketClient extends WebSocketClient implements TransportOfClient, IModelOfClient {
    private final ObjectMapperOfMvtg mapper = new ObjectMapperOfMvtg();
    private final LocalClientState localClientState;
    private final HashSetOfObserverOnAbstractEvent hashSetOfObserverOnAbstractEvent;

    public MultiGameWebSocketClient(URI serverUri, LocalClientState localClientState, HashSetOfObserverOnAbstractEvent hashSetOfObserverOnAbstractEvent) {
        super(serverUri);
        this.localClientState = localClientState;
        this.hashSetOfObserverOnAbstractEvent = hashSetOfObserverOnAbstractEvent;
        System.out.println(serverUri);
    }

    // Удалить
    @Override
    public LocalClientState getLocalClientState() {
        return localClientState;
    }

    public MainGameClientStatus getMainGameClientStatus() {
        if (!isOpen() || isClosed()) {
            return MainGameClientStatus.NO_CONNECT;
        }
        if (isOpen()) {
            return localClientState.getMainGameClientStatus();
        }
        throw new RuntimeException("Unknown state.");
    }

    // Overiden methods from class WebSocketClient:
    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("onClose");

        localClientState.setUserName("");
        System.out.println("  getMainGameClientStatus() = " + getMainGameClientStatus());
        hashSetOfObserverOnAbstractEvent.updateConnectStatePane(CLOSE);

        System.out.println("  Connect was closed.");
        System.out.println("  Code = " + code + ". Reason = " + reason + ". Remote = " + remote + ".");
        System.out.println("---------- End of onClose");
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("onOpen(ServerHandshake)");

        localClientState.setUserName("");
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
        EventOfServer eventOfServer = mapper.readValue(byteArrayInputStream, EventOfServer.class);
        System.out.println("  eventOfServer = " + eventOfServer);

        eventOfServer.executeOnClient(this);
        System.out.println("  getMainGameClientStatus() = " + getMainGameClientStatus());
        System.out.println("---------- End of onMessage(ByteBuffer)");
    }

    @Override
    public void onMessage(String message) {
        System.err.println("onMessage(String)");
        System.err.println("This type of message (String) should not be!");
        System.exit(1);
    }

    // Overiden methods from interface TransportOfClient:
    @Override
    public void sendEventOfClient(EventOfClient eventOfClient) {
        System.out.println("  send(EventOfClient<WebSocket>)");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mapper.writeValue(byteArrayOutputStream, eventOfClient);
        System.out.println("    eventOfClient = " + eventOfClient);
        send(byteArrayOutputStream.toByteArray());
        System.out.println("---------- End of public void send(EventOfClient<WebSocket> eventOfClient)");
    }

    @Override
    public void updateConnectStatePane(TypeOfEvent getGameTypeSet) {
        hashSetOfObserverOnAbstractEvent.updateConnectStatePane(getGameTypeSet);
    }

    // Overiden methods from interface IModelOfClient:
    // 2
    @Override
    public void logout() {
        System.out.println("logout()");
        sendEventOfClient(new EventOfClient10Logout());
    }

    @Override
    public void login(String userName, String password) {
        System.out.println("login(String, String)");
        sendEventOfClient(new EventOfClient11Login(userName, password));
    }

    // 3
    @Override
    public void forgetGameTypeSet() {
        System.out.println("forgetGameTypeSet()");
        sendEventOfClient(new EventOfClient20ForgetGameTypeSet());
    }

    @Override
    public void getGameTypeSet() {
        System.out.println("getGameTypeSet()");
        sendEventOfClient(new EventOfClient21GetGameTypeSet());
    }

    // 4
    @Override
    public void forgetGameType() {
        System.out.println("forgetGameType()");
        sendEventOfClient(new EventOfClient30ForgetGameType());
    }

    @Override
    public void gameTypeSelect(String serverBaseModelClass) {
        System.out.println("gameTypeSelect(String)");
        sendEventOfClient(new EventOfClient31GameTypeSelect(serverBaseModelClass));
    }

    // 9
    @Override
    public void addView(View view) {
        System.out.println("addView(View)");
        localClientState.addView(view);
        sendEventOfClient(new EventOfClient91AddView(view.toString()));
    }

    @Override
    public void createNewGame() {
        System.out.println("createNewGame()");
        sendEventOfClient(new EventOfClient92GameCommand(new GameCommandNewGame()));
    }
}
