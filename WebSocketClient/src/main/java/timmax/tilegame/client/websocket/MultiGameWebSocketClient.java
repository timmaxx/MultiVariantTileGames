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
import timmax.tilegame.transport.TransportOfClient;

// Этот класс, к сожалению, я не смог использовать "многоразово".
// Т.е. у меня не получилось после открытия и закрытия вновь открыть соединение.
// Поэтому:
// - пришлось создавать класс-обёртку MultiGameWebSocketClientManyTimesUse.
// - соответственно и работающий метод void setURI(URI uriFromControls) в этом классе не возможен,
//   но тогда пусть он бросает исключение.
public class MultiGameWebSocketClient extends WebSocketClient implements TransportOfClient {
    private static final Logger logger = LoggerFactory.getLogger(MultiGameWebSocketClient.class);

    private final ObjectMapperOfMvtg mapper = new ObjectMapperOfMvtg();
    // ToDo: Модель пришлось инициализировать через сеттер. А лучше-бы через коструктор.
    private /*final*/ IModelOfClient iModelOfClient;

    public MultiGameWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    public MainGameClientStatus getMainGameClientStatus() {
        return iModelOfClient.getMainGameClientStatus();
    }

    // Overriden methods from class WebSocketClient:
    @Override
    public void onClose(int code, String reason, boolean remote) {
        logger.info("Connection was closed.");
        iModelOfClient.getLocalClientState().forgetUserName();
        logger.info("  Main game client status: {}.", getMainGameClientStatus());
        iModelOfClient.getLocalClientState().getHashSetOfObserverOnAbstractEvent().updateOnClose();
        logger.debug("  Code: {}. Reason: {}. Remote: {}.", code, reason, remote);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        logger.info("Connection was opened. Server URI: {}.", getURI());
        iModelOfClient.getLocalClientState().forgetUserName();
        logger.info("  Main game client status: {}.", getMainGameClientStatus());
        iModelOfClient.getLocalClientState().getHashSetOfObserverOnAbstractEvent().updateOnOpen();
    }

    @Override
    public void onError(Exception ex) {
        logger.error("Error occurred.", ex);
        logger.error("  Main game client status: {}.", getMainGameClientStatus());
    }

    @Override
    public void onMessage(ByteBuffer byteBuffer) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        // ToDo: А вдруг здесь от сервера прилетит что-то не EventOfServer?
        //       Тогда нужно обрабатывать исключение и выводить в лог.
        EventOfServer eventOfServer = mapper.readValue(byteArrayInputStream, EventOfServer.class);
        logger.info("Incoming message. EventOfServer: {}.", eventOfServer);
        eventOfServer.executeOnClient(iModelOfClient);
        logger.debug("  Main game client status: {}.", getMainGameClientStatus());
    }

    @Override
    public void onMessage(String message) {
        logger.error("Incoming message. This type of message (String) should not be!");
        logger.error("  Main game client status: {}.", getMainGameClientStatus());
        System.exit(1);
    }

    // Overriden methods from interface TransportOfClient:
    @Override
    public void setModelOfClient(IModelOfClient iModelOfClient) {
        this.iModelOfClient = iModelOfClient;
    }

    @Override
    public void setURI(URI uriFromControls) {
        String errLogMessage = "You can not use this method!";
        logger.error(errLogMessage);
        throw new RuntimeException(errLogMessage);
    }

    @Override
    public void sendEventOfClient(EventOfClient eventOfClient) {
        logger.info("Outcoming message. EventOfClient: {}.", eventOfClient);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mapper.writeValue(byteArrayOutputStream, eventOfClient);
        send(byteArrayOutputStream.toByteArray());
    }
}
