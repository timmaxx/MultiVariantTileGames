package timmax.tilegame.client.websocket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Map;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.common.ObjectMapperOfMvtg;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;
import timmax.tilegame.transport.TransportOfClient;

// Этот класс, к сожалению, я не смог использовать "многоразово".
// Т.е. у меня не получилось после открытия и закрытия вновь открыть соединение.
// Поэтому:
// - создал класс-обёртку MultiGameWebSocketClientManyTimesUse.
// - соответственно и работающий метод void setURI(URI uriFromControls) в этом классе не возможен,
//   но тогда пусть он бросает исключение.

public class MultiGameWebSocketClient extends WebSocketClient implements TransportOfClient<WebSocket> {
    private static final Logger logger = LoggerFactory.getLogger(MultiGameWebSocketClient.class);

    private final ObjectMapperOfMvtg mapper = new ObjectMapperOfMvtg();
    private final TransportOfClient<WebSocket> modelMultiGameWebSocketClientManyTimesUse;

    public MultiGameWebSocketClient(URI serverUri, TransportOfClient<WebSocket> modelMultiGameWebSocketClientManyTimesUse) {
        super(serverUri);
        this.modelMultiGameWebSocketClientManyTimesUse = modelMultiGameWebSocketClientManyTimesUse;
    }

    // class WebSocketClient:
    @Override
    public void onClose(int code, String reason, boolean remote) {
        logger.info("Connection was closed.");
        getLocalClientStateAutomaton().forgetUser();
        logger.info("  Main game client status: {}.", modelMultiGameWebSocketClientManyTimesUse);
        getLocalClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnClose();
        logger.debug("  Code: {}. Reason: {}. Remote: {}.", code, reason, remote);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        logger.info("Connection was opened. Server URI: {}.", getURI());
        getLocalClientStateAutomaton().forgetUser();
        getLocalClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnOpen();
    }

    @Override
    public void onError(Exception ex) {
        logger.error("Error occurred.", ex);
        logger.error("  Main game client status: {}.", modelMultiGameWebSocketClientManyTimesUse);
    }

    @Override
    public void onMessage(ByteBuffer byteBuffer) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        // ToDo: А вдруг здесь от сервера прилетит что-то не EventOfServer?
        //       Тогда нужно обрабатывать исключение и выводить в лог.
        EventOfServer eventOfServer = mapper.readValue(byteArrayInputStream, EventOfServer.class);
        logger.info("Incoming message. EventOfServer: {}.", eventOfServer);
        eventOfServer.executeOnClient(modelMultiGameWebSocketClientManyTimesUse.getLocalClientStateAutomaton());
        logger.debug("  Main game client status: {}.", modelMultiGameWebSocketClientManyTimesUse);
    }

    @Override
    public void onMessage(String message) {
        logger.error("Incoming message. This type of message (String) should not be!");
        logger.error("  Main game client status: {}.", modelMultiGameWebSocketClientManyTimesUse);
        System.exit(1);
    }

    // interface TransportOfClient:
    @Override
    public void setURI(URI uriFromControls) {
        String errLogMessage = "You can not use this method!";
        logger.error(errLogMessage);
        throw new RuntimeException(errLogMessage);
    }

    @Override
    public void sendEventOfClient(EventOfClient<WebSocket> eventOfClient) {
        logger.info("Outcoming message. EventOfClient: {}.", eventOfClient);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mapper.writeValue(byteArrayOutputStream, eventOfClient);
        send(byteArrayOutputStream.toByteArray());
    }

    @Override
    public LocalClientStateAutomaton getLocalClientStateAutomaton() {
        return modelMultiGameWebSocketClientManyTimesUse.getLocalClientStateAutomaton();
    }

    // interface TransportOfClient:
    // 2
    @Override
    public void setUser(String userName, String password) {
        logger.debug("setUser(String, String)");
        sendEventOfClient(new EventOfClient21SetUser<>(userName, password));
    }

    // 3
    @Override
    public void forgetUser() {
        logger.debug("forgetUser()");
        sendEventOfClient(new EventOfClient20ForgetUser<>());
    }

    @Override
    public void setGameTypeSet() {
        logger.debug("setGameTypeSet()");
        sendEventOfClient(new EventOfClient31SetGameTypeSet<>());
    }

    // 4
    @Override
    public void forgetGameTypeSet() {
        logger.debug("forgetGameTypeSet()");
        sendEventOfClient(new EventOfClient30ForgetGameTypeSet<>());
    }

    @Override
    public void setGameType(GameType gameType) {
        logger.debug("setGameType(GameType)");
        sendEventOfClient(new EventOfClient41SetGameType<>(gameType.getGameName()));
    }

    // 5
    @Override
    public void forgetGameType() {
        logger.debug("forgetGameType()");
        sendEventOfClient(new EventOfClient40ForgetGameType<>());
    }

    @Override
    public void setGameMatchSet() {
        logger.debug("setGameMatchSet()");
        sendEventOfClient(new EventOfClient51SetGameMatchSet<>());
    }

    // 6
    @Override
    public void forgetGameMatchSet() {
        logger.debug("forgetGameMatchSet()");
        sendEventOfClient(new EventOfClient50ForgetGameMatchSet<>());
    }

    @Override
    public void setGameMatch(GameMatchId gameMatchId) {
        logger.debug("setGameMatch(GameMatchId gameMatchId)");
        sendEventOfClient(new EventOfClient61SetGameMatch<>(gameMatchId));
    }

    // 7
    @Override
    public void forgetGameMatch() {
        logger.debug("forgetGameMatch()");
        sendEventOfClient(new EventOfClient60ForgetGameMatch<>());
    }

    @Override
    public void setGameMatchPlaying(Map<String, Integer> mapOfParamsOfModelValue) {
        logger.debug("setGameMatchPlaying(Map<String, Integer> mapOfParamsOfModelValue)");
        sendEventOfClient(new EventOfClient71SetGameMatchPlaying<>(mapOfParamsOfModelValue));
    }

    // 8
    @Override
    public void forgetGameMatchPlaying() {
        logger.debug("forgetGameMatchPlaying()");
        sendEventOfClient(new EventOfClient70ForgetGameMatchPlaying<>());
    }
}
