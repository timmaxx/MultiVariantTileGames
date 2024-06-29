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

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
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
        getLocalClientStateAutomaton().forgetUserName();
        logger.info("  Main game client status: {}.", modelMultiGameWebSocketClientManyTimesUse);
        getLocalClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnClose();
        logger.debug("  Code: {}. Reason: {}. Remote: {}.", code, reason, remote);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        logger.info("Connection was opened. Server URI: {}.", getURI());
        getLocalClientStateAutomaton().forgetUserName();
        logger.info("  Main game client status: {}.", modelMultiGameWebSocketClientManyTimesUse);
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
        logger.info("  Main game client status: {}.", modelMultiGameWebSocketClientManyTimesUse);
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
    public void login(String userName, String password) {
        logger.debug("login(String, String)");
        sendEventOfClient(new EventOfClient21Login<>(userName, password));
    }

    // 3
    @Override
    public void logout() {
        logger.debug("logout()");
        sendEventOfClient(new EventOfClient20Logout<>());
    }

    @Override
    public void giveGameTypeSet() {
        logger.debug("getGameTypeSet()");
        sendEventOfClient(new EventOfClient31GiveGameTypeSet<>());
    }

    // 4
    @Override
    public void forgetGameTypeSet() {
        logger.debug("forgetGameTypeSet()");
        sendEventOfClient(new EventOfClient30ForgetGameTypeSet<>());
    }

    @Override
    public void gameTypeSelect(ModelOfServerDescriptor modelOfServerDescriptor) {
        logger.debug("gameTypeSelect(String)");
        sendEventOfClient(new EventOfClient41SetGameType<>(modelOfServerDescriptor.getGameName()));
    }

    // 5
    @Override
    public void forgetGameType() {
        logger.debug("forgetGameType()");
        sendEventOfClient(new EventOfClient40ForgetGameType<>());
    }

    @Override
    public void getGameMatchSet() {
        logger.debug("getGameMatchSet()");
        sendEventOfClient(new EventOfClient51GiveGameMatchSet<>());
    }

    // 6
    @Override
    public void forgetGameMatchSet() {
        logger.debug("forgetGameMatchSet()");
        sendEventOfClient(new EventOfClient50ForgetGameMatchSet<>());
    }

    @Override
    public void gameMatchSelect(InstanceIdOfModel model) {
        logger.debug("gameMatchSelect(InstanceIdOfModel model)");
        sendEventOfClient(new EventOfClient61SetGameMatch<>(model));
    }

    // 7
    @Override
    public void forgetGameMatch() {
        logger.debug("forgetGameMatch()");
        sendEventOfClient(new EventOfClient60ForgetGameMatch<>());
    }

    @Override
    public void startGameMatchPlaying(Map<String, Integer> mapOfParamsOfModelValue) {
        logger.debug("startPlaying()");
        sendEventOfClient(new EventOfClient71StartGameMatchPlaying<>(mapOfParamsOfModelValue));
    }

    // 8
    @Override
    public void stopGameMatchPlaying() {
        logger.debug("stopPlaying()");
        sendEventOfClient(new EventOfClient70StopGameMatchPlaying<>());
    }
}
