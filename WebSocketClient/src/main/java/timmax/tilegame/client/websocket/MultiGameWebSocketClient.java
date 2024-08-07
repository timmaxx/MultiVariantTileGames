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
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;
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
        getLocalClientStateAutomaton().openConnectWithoutUserIdentify();
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
        logger.debug("setUser(String, String)");
        sendEventOfClient(new EventOfClient21IdentifyAuthenticateAuthorizeUser(userName, password));
    }

    // 4
    @Override
    public void reauthorizeUser() {
        logger.debug("reauthorizeUser()");
        sendEventOfClient(new EventOfClient42ReauthorizeUser());
    }

    @Override
    public void setGameType(GameType gameType) {
        logger.debug("setGameType(GameType)");
        sendEventOfClient(new EventOfClient41SetGameType(gameType.getGameTypeName()));
    }

    // 6
    @Override
    public void resetGameType() {
        logger.debug("resetGameType()");
        sendEventOfClient(new EventOfClient62ResetGameType());
    }

    @Override
    public void setGameMatch(GameMatchId gameMatchId) {
        logger.debug("setGameMatch(GameMatchId gameMatchId)");
        sendEventOfClient(new EventOfClient61SetGameMatch(gameMatchId));
    }

    // 7
    @Override
    public void setGameMatchIsPlaying(Map<String, Integer> mapOfParamsOfModelValue) {
        logger.debug("setGameMatchIsPlaying(Map<String, Integer> mapOfParamsOfModelValue)");
        sendEventOfClient(new EventOfClient71SetGameMatchIsPlaying(mapOfParamsOfModelValue));
    }

    @Override
    public void resetGameMatch() {
        logger.debug("resetGameMatch()");
        sendEventOfClient(new EventOfClient72ResetGameMatch());
    }

    // 8
}
