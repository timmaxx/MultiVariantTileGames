package timmax.tilegame.client.websocket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.basemodel.protocol.client.LocalClientState;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.transport.TransportOfClient;

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

    public MainGameClientStatus getMainGameClientStatus() {
        if (!isOpen() || isClosed()) {
            return MainGameClientStatus.NO_CONNECT;
        }
        if (isOpen()) {
            return localClientState.getMainGameClientStatus();
        }
        throw new RuntimeException("Unknown state.");
    }

    // Overriden methods from class WebSocketClient:
    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("onClose");

        localClientState.forgetUserName();
        System.out.println("  getMainGameClientStatus() = " + getMainGameClientStatus());
        hashSetOfObserverOnAbstractEvent.updateOnClose();

        System.out.println("  Connect was closed.");
        System.out.println("  Code = " + code + ". Reason = " + reason + ". Remote = " + remote + ".");
        System.out.println("---------- End of onClose");
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("onOpen(ServerHandshake)");

        localClientState.forgetUserName();
        System.out.println("  getMainGameClientStatus() = " + getMainGameClientStatus());
        hashSetOfObserverOnAbstractEvent.updateOnOpen();

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

    // Overriden methods from interface TransportOfClient:
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
    public LocalClientState getLocalClientState() {
        return localClientState;
    }

    @Override
    public HashSetOfObserverOnAbstractEvent getHashSetOfObserverOnAbstractEvent() {
        return hashSetOfObserverOnAbstractEvent;
    }

    // Overriden methods from interface IModelOfClient:
    // 2
    @Override
    public void logout() {
        System.out.println("logout()");
        sendEventOfClient(new EventOfClient20Logout());
    }

    @Override
    public void login(String userName, String password) {
        System.out.println("login(String, String)");
        sendEventOfClient(new EventOfClient21Login(userName, password));
    }

    // 3
    @Override
    public void forgetGameTypeSet() {
        System.out.println("forgetGameTypeSet()");
        sendEventOfClient(new EventOfClient30ForgetGameTypeSet());
    }

    @Override
    public void getGameTypeSet() {
        System.out.println("getGameTypeSet()");
        sendEventOfClient(new EventOfClient31GiveGameTypeSet());
    }

    // 4
    @Override
    public void forgetGameType() {
        System.out.println("forgetGameType()");
        sendEventOfClient(new EventOfClient40ForgetGameType());
    }

    @Override
    public void gameTypeSelect(ModelOfServerDescriptor modelOfServerDescriptor) {
        System.out.println("gameTypeSelect(String)");
        sendEventOfClient(new EventOfClient41SetGameType(modelOfServerDescriptor));
    }

    // 5
    @Override
    public void forgetGameMatchSet() {
        System.out.println("forgetGameMatchSet()");
        sendEventOfClient(new EventOfClient50ForgetGameMatchSet());
    }

    @Override
    public void getGameMatchSet() {
        System.out.println("getGameMatchSet()");
        sendEventOfClient(new EventOfClient51GiveGameMatchSet());
    }

    // 6
    @Override
    public void forgetGameMatch() {
        System.out.println("forgetGameMatch()");
        sendEventOfClient(new EventOfClient60ForgetGameMatch());
    }

    @Override
    public void gameMatchSelect(InstanceIdOfModel model) {
        System.out.println("gameMatchSelect(InstanceIdOfModel model)");
        sendEventOfClient(new EventOfClient61SetGameMatch(model));
    }

    // 7
    @Override
    public void stopGameMatchPlaying() {
        System.out.println("stopPlaying()");
        sendEventOfClient(new EventOfClient70StopGameMatchPlaying());
    }

    @Override
    public void startGameMatchPlaying() {
        System.out.println("startPlaying()");
        sendEventOfClient(new EventOfClient71StartGameMatchPlaying());
    }
}
