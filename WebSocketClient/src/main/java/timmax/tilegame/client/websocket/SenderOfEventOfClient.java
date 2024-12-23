package timmax.tilegame.client.websocket;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.Map;

import org.java_websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.common.ObjectMapperOfMvtg;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;
import timmax.tilegame.transport.ISenderOfEventOfClient;

//  Отправитель клиентсих событий.
public class SenderOfEventOfClient implements ISenderOfEventOfClient {
    private static final Logger logger = LoggerFactory.getLogger(SenderOfEventOfClient.class);

    //  ToDo:   ObjectMapperOfMvtg mapper сделать синглтоном.
    private final ObjectMapperOfMvtg mapper = new ObjectMapperOfMvtg();
    private final LocalClientStateAutomaton localClientStateAutomatonJfx;

    private URI uri;

    // ToDo: Привести к единому виду взаимоиспользование:
    //       - на клиенте переменных типов WebSocketClient и LocalClientStateAutomaton,
    //       - на сервере переменных типов TransportOfServer и RemoteClientStateAutomaton.
    //       Переменные типов WebSocketClient и LocalClientStateAutomaton на клиенте на одном уровне.
    //       А для сервера переменная типа TransportOfServer входит в состав RemoteClientStateAutomaton.
    private WebSocketClient webSocketClient;

    public SenderOfEventOfClient(LocalClientStateAutomaton localClientStateAutomatonJfx) {
        super();
        this.localClientStateAutomatonJfx = localClientStateAutomatonJfx;
        // ToDo: Возможно, что-бы не использовать null, стоит реализовать класс, реализующий IClientState01NoConnect
        //       и здесь им инициализировать.
        logger.info("  Main game client status: {}.", localClientStateAutomatonJfx);
    }

    private void sendEventOfClient(EventOfClient eventOfClient) {
        logger.info("Outcoming message. EventOfClient: {}.", eventOfClient);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mapper.writeValue(byteArrayOutputStream, eventOfClient);
        webSocketClient.send(byteArrayOutputStream.toByteArray());
    }

    // interface TransportOfClient
    @Override
    public void close() {
        if (webSocketClient == null) {
            return;
        }
        webSocketClient.close();
        webSocketClient = null;
    }

    @Override
    public void connect() {
        webSocketClient = new MultiGameWebSocketClient(uri, this);
        webSocketClient.connect();
    }

    @Override
    public void setURI(URI uri) {
        this.uri = uri;
        if (webSocketClient == null) {
            return;
        }
        webSocketClient.close();
    }

    @Override
    public void sendEventOfClient92GameCommand(EventOfClient92GameCommand eventOfClient92GameCommand) {
        sendEventOfClient(eventOfClient92GameCommand);
    }

    @Override
    public LocalClientStateAutomaton getLocalClientStateAutomaton() {
        return localClientStateAutomatonJfx;
    }

    // interface TransportOfClient:
    // 1
    @Override
    public void connectWithoutUserIdentify() {
        sendEventOfClient(new EventOfClient11ConnectWithoutUserIdentify());
    }

    // 2
    @Override
    public void identifyAuthenticateAuthorizeUser(String userName, String password) {
        sendEventOfClient(new EventOfClient21IdentifyAuthenticateAuthorizeUser(userName, password));
    }

    // 4
    @Override
    public void reauthorizeUser() {
        sendEventOfClient(new EventOfClient42ReauthorizeUser());
    }

    @Override
    public void setGameType(GameType gameType) {
        sendEventOfClient(new EventOfClient41SetGameType(gameType.getId()));
    }

    // 6
    @Override
    public void resetGameType() {
        sendEventOfClient(new EventOfClient62ResetGameType());
    }

    @Override
    public void setGameMatch(GameMatchDto gameMatchDto) {
        sendEventOfClient(new EventOfClient61SetGameMatch(gameMatchDto));
    }

    // 7
    //  Нет возвращаемого значения!
    @Override
    public void startGameMatch(Map<String, Integer> mapOfParamsOfModelValue) {
        sendEventOfClient(new EventOfClient71StartGameMatch(mapOfParamsOfModelValue));
    }

    @Override
    public void resetGameMatch() {
        sendEventOfClient(new EventOfClient72ResetGameMatch());
    }

    // class Object
    @Override
    public String toString() {
        return localClientStateAutomatonJfx.toString();
    }
}
