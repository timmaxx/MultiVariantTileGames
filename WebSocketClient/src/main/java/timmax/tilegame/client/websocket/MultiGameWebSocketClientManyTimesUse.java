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
    public void setUser(String userName, String password) {
        transportOfClient.setUser(userName, password);
    }

    // 3
    @Override
    public void forgetUser() {
        transportOfClient.forgetUser();
    }

    @Override
    public void setGameTypeSet() {
        transportOfClient.setGameTypeSet();
    }

    // 4
    @Override
    public void forgetGameTypeSet() {
        transportOfClient.forgetGameTypeSet();
    }

    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        transportOfClient.setGameType(modelOfServerDescriptor);
    }

    // 5
    @Override
    public void forgetGameType() {
        transportOfClient.forgetGameType();
    }

    @Override
    public void setGameMatchSet() {
        transportOfClient.setGameMatchSet();
    }

    // 6
    @Override
    public void forgetGameMatchSet() {
        transportOfClient.forgetGameMatchSet();
    }

    @Override
    public void setGameMatch(InstanceIdOfModel model) {
        transportOfClient.setGameMatch(model);
    }

    // 7
    @Override
    public void forgetGameMatch() {
        transportOfClient.forgetGameMatch();
    }

    @Override
    public void setGameMatchPlaying(Map<String, Integer> mapOfParamsOfModelValue) {
        transportOfClient.setGameMatchPlaying(mapOfParamsOfModelValue);
    }

    // 8
    @Override
    public void forgetGameMatchPlaying() {
        transportOfClient.forgetGameMatchPlaying();
    }

    // class Object
    @Override
    public String toString() {
        return localClientStateAutomatonJfx.toString();
    }
}
