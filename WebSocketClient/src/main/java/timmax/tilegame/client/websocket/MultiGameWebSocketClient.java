package timmax.tilegame.client.websocket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

// ToDo: Делать ли этот класс реализующим TransportOfClient?
public class MultiGameWebSocketClient extends WebSocketClient /*implements TransportOfClient*/ {
    private static final Logger logger = LoggerFactory.getLogger(MultiGameWebSocketClient.class);

    private final ObjectMapperOfMvtg mapper = new ObjectMapperOfMvtg();
    // ToDo: Модель пришлось инициализировать через сеттер. А луше-бы через коструктор.
    private /*final*/ IModelOfClient iModelOfClient;

    public MultiGameWebSocketClient(URI serverUri) {
        super(serverUri);
        logger.info("serverUri = {}", serverUri);
    }

    public MainGameClientStatus getMainGameClientStatus() {
        return iModelOfClient.getMainGameClientStatus();
    }

    // Overriden methods from class WebSocketClient:
    @Override
    public void onClose(int code, String reason, boolean remote) {
        logger.info("onClose");
        iModelOfClient.getLocalClientState().forgetUserName();
        logger.info("  getMainGameClientStatus() = {}", getMainGameClientStatus());
        iModelOfClient.getLocalClientState().getHashSetOfObserverOnAbstractEvent().updateOnClose();
        logger.info("  Connect was closed.");
        logger.info("  Code = {}. Reason = {}. Remote = {}.", code, reason, remote);
        logger.info("---------- End of onClose");
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        logger.info("onOpen(ServerHandshake)");
        iModelOfClient.getLocalClientState().forgetUserName();
        logger.info("  getMainGameClientStatus() = {}", getMainGameClientStatus());
        iModelOfClient.getLocalClientState().getHashSetOfObserverOnAbstractEvent().updateOnOpen();
        logger.info("---------- End of onOpen");
    }

    @Override
    public void onError(Exception ex) {
        logger.error("onError(Exception)", ex);
        logger.error("---------- End of onError");
    }

    @Override
    public void onMessage(ByteBuffer byteBuffer) {
        logger.info("onMessage(ByteBuffer)");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        EventOfServer eventOfServer = mapper.readValue(byteArrayInputStream, EventOfServer.class);
        logger.info("  eventOfServer = {}", eventOfServer);
        eventOfServer.executeOnClient(iModelOfClient);
        logger.info("  getMainGameClientStatus() = {}", getMainGameClientStatus());
        logger.info("---------- End of onMessage(ByteBuffer)");
    }

    @Override
    public void onMessage(String message) {
        logger.error("onMessage(String)");
        logger.error("This type of message (String) should not be!");
        System.exit(1);
    }

    // Overriden methods from interface TransportOfClient:
    // @Override
    public void setModelOfClient(IModelOfClient iModelOfClient) {
        this.iModelOfClient = iModelOfClient;
    }

    // @Override
    public void sendEventOfClient(EventOfClient eventOfClient) {
        logger.info("  send(EventOfClient<WebSocket>)");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mapper.writeValue(byteArrayOutputStream, eventOfClient);
        logger.info("    eventOfClient = {}", eventOfClient);
        send(byteArrayOutputStream.toByteArray());
        logger.info("---------- End of public void send(EventOfClient<WebSocket> eventOfClient)");
    }
}
