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

    LocalClientStateAutomaton localClientStateJfx;

    public MultiGameWebSocketClientManyTimesUse(LocalClientStateAutomaton localClientStateJfx) {
        super();
        this.localClientStateJfx = localClientStateJfx;
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
    public LocalClientStateAutomaton getLocalClientState() {
        return localClientStateJfx;
    }

    @Override
    public void login(String userName, String password) {
        transportOfClient.login(userName, password);
    }

    @Override
    public void logout() {
        transportOfClient.logout();
    }

    @Override
    public void getGameTypeSet() {
        transportOfClient.getGameTypeSet();
    }

    @Override
    public void forgetGameTypeSet() {
        transportOfClient.forgetGameTypeSet();
    }

    @Override
    public void gameTypeSelect(ModelOfServerDescriptor modelOfServerDescriptor) {
        transportOfClient.gameTypeSelect(modelOfServerDescriptor);
    }

    @Override
    public void forgetGameType() {
        transportOfClient.forgetGameType();
    }

    @Override
    public void getGameMatchSet() {
        transportOfClient.getGameMatchSet();
    }

    @Override
    public void forgetGameMatchSet() {
        transportOfClient.forgetGameMatchSet();
    }

    @Override
    public void gameMatchSelect(InstanceIdOfModel model) {
        transportOfClient.gameMatchSelect(model);
    }

    @Override
    public void forgetGameMatch() {
        transportOfClient.forgetGameMatch();
    }

    @Override
    public void startGameMatchPlaying(Map<String, Integer> mapOfParamsOfModelValue) {
        transportOfClient.startGameMatchPlaying(mapOfParamsOfModelValue);
    }

    @Override
    public void stopGameMatchPlaying() {
        transportOfClient.stopGameMatchPlaying();
    }

    // class Object
    @Override
    public String toString() {
        return localClientStateJfx.toString();
    }
}
