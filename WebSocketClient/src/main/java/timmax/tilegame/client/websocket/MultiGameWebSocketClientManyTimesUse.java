package timmax.tilegame.client.websocket;

import java.net.URI;
import java.util.Map;

import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.transport.TransportOfClient;

// WebSocket клиент многоразовый
public class MultiGameWebSocketClientManyTimesUse implements TransportOfClient<WebSocket> {
    private static final Logger logger = LoggerFactory.getLogger(MultiGameWebSocketClientManyTimesUse.class);

    private TransportOfClient<WebSocket> transportOfClient;
    private URI uri;

    LocalClientStateAutomaton localClientStateAutomatonJfx;

    public MultiGameWebSocketClientManyTimesUse(LocalClientStateAutomaton localClientStateAutomatonJfx) {
        super();
        this.localClientStateAutomatonJfx = localClientStateAutomatonJfx;
        // ToDo: Возможно, что-бы не использовать null, стоит реализовать класс, реализующий IClientState01NoConnect
        //       и здесь им инициализировать.
        logger.info("  Main game client status: {}.", "NO_CONNECT");
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
    public void sendEventOfClient(EventOfClient<WebSocket> eventOfClient) {
        transportOfClient.sendEventOfClient(eventOfClient);
    }

    @Override
    public LocalClientStateAutomaton getLocalClientStateAutomaton() {
        return localClientStateAutomatonJfx;
    }

    // interface TransportOfClient:
    // 2
    @Override
    public void login(String userName, String password) {
        transportOfClient.login(userName, password);
    }

    // 3
    @Override
    public void forgetUser() {
        transportOfClient.forgetUser();
    }

    @Override
    public void giveGameTypeSet() {
        transportOfClient.giveGameTypeSet();
    }

    // 4
    @Override
    public void forgetGameTypeSet() {
        transportOfClient.forgetGameTypeSet();
    }

    @Override
    public void gameTypeSelect(ModelOfServerDescriptor modelOfServerDescriptor) {
        transportOfClient.gameTypeSelect(modelOfServerDescriptor);
    }

    // 5
    @Override
    public void forgetGameType() {
        transportOfClient.forgetGameType();
    }

    @Override
    public void getGameMatchSet() {
        transportOfClient.getGameMatchSet();
    }

    // 6
    @Override
    public void forgetGameMatchSet() {
        transportOfClient.forgetGameMatchSet();
    }

    @Override
    public void gameMatchSelect(InstanceIdOfModel model) {
        transportOfClient.gameMatchSelect(model);
    }

    // 7
    @Override
    public void forgetGameMatch() {
        transportOfClient.forgetGameMatch();
    }

    @Override
    public void startGameMatchPlaying(Map<String, Integer> mapOfParamsOfModelValue) {
        transportOfClient.startGameMatchPlaying(mapOfParamsOfModelValue);
    }

    // 8
    @Override
    public void stopGameMatchPlaying() {
        transportOfClient.stopGameMatchPlaying();
    }

    // class Object
    @Override
    public String toString() {
        return localClientStateAutomatonJfx.toString();
    }
}
