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

// ToDo: Делать ли этот класс реализующим TransportOfClient?
public class MultiGameWebSocketClient extends WebSocketClient /*implements TransportOfClient*/ {
    private final ObjectMapperOfMvtg mapper = new ObjectMapperOfMvtg();
    // ToDo: Модель пришлось инициализировать через сеттер. А луше-бы через коструктор.
    private /*final*/ IModelOfClient iModelOfClient;

    public MultiGameWebSocketClient(URI serverUri) {
        super(serverUri);
        System.out.println(serverUri);
    }

    public void setModelOfClient(IModelOfClient iModelOfClient) {
        this.iModelOfClient = iModelOfClient;
    }

    public MainGameClientStatus getMainGameClientStatus() {
        return iModelOfClient.getMainGameClientStatus();
    }

    // Overriden methods from class WebSocketClient:
    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("onClose");

        iModelOfClient.getLocalClientState().forgetUserName();
        System.out.println("  getMainGameClientStatus() = " + getMainGameClientStatus());
        iModelOfClient.getLocalClientState().getHashSetOfObserverOnAbstractEvent().updateOnClose();

        System.out.println("  Connect was closed.");
        System.out.println("  Code = " + code + ". Reason = " + reason + ". Remote = " + remote + ".");
        System.out.println("---------- End of onClose");
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("onOpen(ServerHandshake)");

        iModelOfClient.getLocalClientState().forgetUserName();
        System.out.println("  getMainGameClientStatus() = " + getMainGameClientStatus());
        iModelOfClient.getLocalClientState().getHashSetOfObserverOnAbstractEvent().updateOnOpen();

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

        eventOfServer.executeOnClient(iModelOfClient);
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
    // @Override
    public void sendEventOfClient(EventOfClient eventOfClient) {
        System.out.println("  send(EventOfClient<WebSocket>)");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mapper.writeValue(byteArrayOutputStream, eventOfClient);
        System.out.println("    eventOfClient = " + eventOfClient);
        send(byteArrayOutputStream.toByteArray());
        System.out.println("---------- End of public void send(EventOfClient<WebSocket> eventOfClient)");
    }
}
