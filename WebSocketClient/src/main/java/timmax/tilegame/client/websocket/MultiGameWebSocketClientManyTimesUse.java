package timmax.tilegame.client.websocket;

import java.net.URI;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;
import timmax.tilegame.transport.TransportOfClient;

// WebSocket клиент многоразовый
public class MultiGameWebSocketClientManyTimesUse implements TransportOfClient {
    private static final Logger logger = LoggerFactory.getLogger(MultiGameWebSocketClientManyTimesUse.class);

    private URI uri;
    // ToDo: Привести к единому виду взаимоиспользование:
    //       - на клиенте переменных типов TransportOfClient и LocalClientStateAutomaton,
    //       - на сервере переменных типов TransportOfServer и RemoteClientStateAutomaton.
    //       Переменные типов TransportOfClient и LocalClientStateAutomaton на клиенте на одном уровне.
    //       А для сервера переменная типа TransportOfServer входит в состав RemoteClientStateAutomaton.
    private TransportOfClient transportOfClient;
    LocalClientStateAutomaton localClientStateAutomatonJfx;

    public MultiGameWebSocketClientManyTimesUse(LocalClientStateAutomaton localClientStateAutomatonJfx) {
        super();
        this.localClientStateAutomatonJfx = localClientStateAutomatonJfx;
        // ToDo: Возможно, что-бы не использовать null, стоит реализовать класс, реализующий IClientState01NoConnect
        //       и здесь им инициализировать.
        logger.info("  Main game client status: {}.", localClientStateAutomatonJfx);
    }

    // interface TransportOfClient
    @Override
    public void close() {
        if (transportOfClient == null) {
            return;
        }
        transportOfClient.close();
        transportOfClient = null;
    }

    @Override
    public void connect() {
        transportOfClient = new MultiGameWebSocketClient(uri, this);
        transportOfClient.connect();
    }

    @Override
    public void setURI(URI uri) {
        this.uri = uri;
        if (transportOfClient == null) {
            return;
        }
        transportOfClient.close();
    }

    @Override
    public void sendEventOfClient(EventOfClient eventOfClient) {
        transportOfClient.sendEventOfClient(eventOfClient);
    }

    @Override
    public LocalClientStateAutomaton getLocalClientStateAutomaton() {
        return localClientStateAutomatonJfx;
    }

    // interface TransportOfClient:
    // 1
    @Override
    public void connectWithoutUserIdentify() {
        transportOfClient.connectWithoutUserIdentify();
    }

    // 2
    @Override
    public void identifyAuthenticateAuthorizeUser(String userName, String password) {
        transportOfClient.identifyAuthenticateAuthorizeUser(userName, password);
    }

    // 4
    @Override
    public void reauthorizeUser() {
        transportOfClient.reauthorizeUser();
    }

    @Override
    public void setGameType(GameType gameType) {
        transportOfClient.setGameType(gameType);
    }

    // 6
    @Override
    public void resetGameType() {
        transportOfClient.resetGameType();
    }

    @Override
    public void setGameMatch(GameMatchDto gameMatchDto) {
        transportOfClient.setGameMatch(gameMatchDto);
    }

    // 7
    //  Нет возвращаемого значения!
    @Override
    public void startGameMatch(Map<String, Integer> mapOfParamsOfModelValue) {
        transportOfClient.startGameMatch(mapOfParamsOfModelValue);
    }

    @Override
    public void resetGameMatch() {
        transportOfClient.resetGameMatch();
    }

    // class Object
    @Override
    public String toString() {
        return localClientStateAutomatonJfx.toString();
    }
}
