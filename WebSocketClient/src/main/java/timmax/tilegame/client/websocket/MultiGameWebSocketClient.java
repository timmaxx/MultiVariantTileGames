package timmax.tilegame.client.websocket;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.common.ObjectMapperOfMvtg;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.transport.ISenderOfEventOfClient;

// Этот класс, к сожалению, я не смог использовать "многоразово".
// Т.е. у меня не получилось после открытия и закрытия вновь открыть соединение.
// Поэтому:

// - создал класс-обёртку MultiGameWebSocketClientManyTimesUse.

public class MultiGameWebSocketClient extends WebSocketClient {
    private static final Logger logger = LoggerFactory.getLogger(MultiGameWebSocketClient.class);

    //  ToDo:   ObjectMapperOfMvtg mapper сделать синглтоном.
    private final ObjectMapperOfMvtg mapper = new ObjectMapperOfMvtg();
    private final ISenderOfEventOfClient senderOfEventOfClient;

    public MultiGameWebSocketClient(URI serverUri, ISenderOfEventOfClient senderOfEventOfClient) {
        super(serverUri);
        this.senderOfEventOfClient = senderOfEventOfClient;
    }

    private LocalClientStateAutomaton getLocalClientStateAutomaton() {
        return senderOfEventOfClient.getLocalClientStateAutomaton();
    }

    // class WebSocketClient:
    @Override
    public void onClose(int code, String reason, boolean remote) {
        logger.info("Connection was closed.");
        getLocalClientStateAutomaton().close();
        logger.info("  Main game client status: {}.", senderOfEventOfClient);
        logger.debug("  Code: {}. Reason: {}. Remote: {}.", code, reason, remote);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.info("Connection was opened. Server URI: {}.", getURI());
        getLocalClientStateAutomaton().connect();
    }

    @Override
    public void onError(Exception ex) {
        logger.error("Error occurred.", ex);
        logger.error("  Main game client status: {}.", senderOfEventOfClient);
    }

    @Override
    public void onMessage(ByteBuffer byteBuffer) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        // ToDo: А вдруг здесь от сервера прилетит что-то не EventOfServer?
        //       Тогда нужно обрабатывать исключение и выводить в лог.
        EventOfServer eventOfServer = mapper.readValue(byteArrayInputStream);
        logger.info("Incoming message. EventOfServer: {}.", eventOfServer);
        // ToDo: События, поступающие от сервера для разных выборок, нужно складывать в очередь и обрабатывать после
        //       того, как будут обработаны предыдущие.
        //       Также см. комментарий в EventOfServer92GameEvent :: void executeOnClient(...).
        eventOfServer.executeOnClient(getLocalClientStateAutomaton());
        logger.debug("  Main game client status: {}.", senderOfEventOfClient);
    }

    @Override
    public void onMessage(String message) {
        logger.error("Incoming message. This type of message (String) should not be!");
        logger.error("  Main game client status: {}.", senderOfEventOfClient);
        System.exit(1);
    }
}
