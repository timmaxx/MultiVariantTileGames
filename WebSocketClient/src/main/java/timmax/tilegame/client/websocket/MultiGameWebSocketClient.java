package timmax.tilegame.client.websocket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Map;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.common.ObjectMapperOfMvtg;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;
import timmax.tilegame.transport.TransportOfClient;

// Этот класс, к сожалению, я не смог использовать "многоразово".
// Т.е. у меня не получилось после открытия и закрытия вновь открыть соединение.
// Поэтому:
// - создал класс-обёртку MultiGameWebSocketClientManyTimesUse.
// - соответственно и работающий метод void setURI(URI uriFromControls) в этом классе не возможен,
//   но тогда пусть он бросает исключение.

public class MultiGameWebSocketClient extends WebSocketClient implements TransportOfClient {
    private static final Logger logger = LoggerFactory.getLogger(MultiGameWebSocketClient.class);

    private final ObjectMapperOfMvtg mapper = new ObjectMapperOfMvtg();
    private final TransportOfClient modelMultiGameWebSocketClientManyTimesUse;

    public MultiGameWebSocketClient(URI serverUri, TransportOfClient modelMultiGameWebSocketClientManyTimesUse) {
        super(serverUri);
        this.modelMultiGameWebSocketClientManyTimesUse = modelMultiGameWebSocketClientManyTimesUse;
    }

    // class WebSocketClient:
    @Override
    public void onClose(int code, String reason, boolean remote) {
        logger.info("Connection was closed.");
        getLocalClientStateAutomaton().closeConnect();
        logger.info("  Main game client status: {}.", modelMultiGameWebSocketClientManyTimesUse);
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
        logger.error("  Main game client status: {}.", modelMultiGameWebSocketClientManyTimesUse);
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
    public void sendEventOfClient(EventOfClient eventOfClient) {
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
    // 1
    @Override
    public void connectWithoutUserIdentify() {
        logger.debug("connectWithoutUserIdentify()");
        sendEventOfClient(new EventOfClient11OpenConnectWithoutUserIdentify());
    }

    // 2
    @Override
    public void identifyAuthenticateAuthorizeUser(String userName, String password) {
        logger.debug("identifyAuthenticateAuthorizeUser(String, String)");
        sendEventOfClient(new EventOfClient21IdentifyAuthenticateAuthorizeUser(userName, password));
    }

    // 4
    @Override
    public void reauthorizeUser() {
        logger.debug("reauthorizeUser()");
        sendEventOfClient(new EventOfClient42ReauthorizeUser());
    }

    @Override
    public void selectGameType(GameType gameType) {
        logger.debug("selectGameType(GameType)");
        sendEventOfClient(new EventOfClient41SelectGameType(gameType.getGameTypeName()));
    }

    // 6
    @Override
    public void reselectGameType() {
        logger.debug("reselectGameType()");
        sendEventOfClient(new EventOfClient62ReselectGameType());
    }

    @Override
    public void selectGameMatch(GameMatchDto gameMatchDto) {
        logger.debug("selectGameMatch(GameMatchDto gameMatchDto)");
        sendEventOfClient(new EventOfClient61SelectGameMatch(gameMatchDto));
    }

    // 7
    @Override
    public void reselectGameMatch() {
        logger.debug("reselectGameMatch()");
        sendEventOfClient(new EventOfClient72ReselectGameMatch());
    }

    @Override
    public void startGameMatch(Map<String, Integer> mapOfParamsOfModelValue) {
        logger.debug("startGameMatch(Map<String, Integer> mapOfParamsOfModelValue)");
        sendEventOfClient(new EventOfClient71StartGameMatch(mapOfParamsOfModelValue));
    }

    @Override
    public void resumeGameMatch() {
        logger.debug("resumeGameMatch()");
        sendEventOfClient(new EventOfClient73ResumeGameMatch());
    }

    // 8
}
