package timmax.tilegame.client.websocket;

import java.net.URI;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.LocalClientStateFabric;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.basemodel.protocol.client.LocalClientState;
import timmax.tilegame.basemodel.protocol.HashSetOfObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.transport.TransportOfClient;

// WebSocket клиент многоразовый
public class MultiGameWebSocketClientManyTimesUse implements TransportOfClient, IModelOfClient {
    private final LocalClientState localClientState;
    private final HashSetOfObserverOnAbstractEvent hashSetOfObserverOnAbstractEvent;

    private MultiGameWebSocketClient multiGameWebSocketClient;
    private URI uri;

    public MultiGameWebSocketClientManyTimesUse(LocalClientStateFabric localClientStateFabric) {
        localClientState = localClientStateFabric.newLocalClientState(this);
        hashSetOfObserverOnAbstractEvent = new HashSetOfObserverOnAbstractEvent();
        System.out.println("getMainGameClientStatus() = " + getMainGameClientStatus());
    }

    public MainGameClientStatus getMainGameClientStatus() {
        if (multiGameWebSocketClient == null) {
            return MainGameClientStatus.NO_CONNECT;
        }
        return multiGameWebSocketClient.getMainGameClientStatus();
    }

    public void setURI(URI uri) {
        this.uri = uri;
        if (multiGameWebSocketClient == null) {
            return;
        }
        multiGameWebSocketClient.close();
    }

    public void addCallBackOnIncomingTransportPackageEvent(ObserverOnAbstractEvent observerOnAbstractEvent) {
        hashSetOfObserverOnAbstractEvent.add(observerOnAbstractEvent);
    }

    // Overriden methods from interface TransportOfClient:
    @Override
    public void sendEventOfClient(EventOfClient eventOfClient) {
        multiGameWebSocketClient.sendEventOfClient(eventOfClient);
    }

    // Overriden methods from interface IModelOfClient:
    // 1
    @Override
    public void close() {
        if (multiGameWebSocketClient == null) {
            return;
        }
        multiGameWebSocketClient.close();
        multiGameWebSocketClient = null;
    }

    @Override
    public void connect() {
        multiGameWebSocketClient = new MultiGameWebSocketClient(uri, localClientState, hashSetOfObserverOnAbstractEvent);
        multiGameWebSocketClient.connect();
    }

    // 2
    @Override
    public void logout() {
        multiGameWebSocketClient.logout();
    }

    @Override
    public void login(String userName, String password) {
        multiGameWebSocketClient.login(userName, password);
    }

    // 3
    @Override
    public void forgetGameTypeSet() {
        multiGameWebSocketClient.forgetGameTypeSet();
    }

    @Override
    public void getGameTypeSet() {
        multiGameWebSocketClient.getGameTypeSet();
    }

    // 4
    @Override
    public void forgetGameType() {
        multiGameWebSocketClient.forgetGameType();
    }

    @Override
    public void gameTypeSelect(ModelOfServerDescriptor modelOfServerDescriptor) {
        multiGameWebSocketClient.gameTypeSelect(modelOfServerDescriptor);
    }

    // 5
    @Override
    public void forgetGameMatchSet() {
        multiGameWebSocketClient.forgetGameMatchSet();
    }

    @Override
    public void getGameMatchSet() {
        multiGameWebSocketClient.getGameMatchSet();
    }

    // 6
    @Override
    public void forgetGameMatch() {
        multiGameWebSocketClient.forgetGameMatch();
    }

    @Override
    public void gameMatchSelect(InstanceIdOfModel model) {
        multiGameWebSocketClient.gameMatchSelect(model);
    }

    // 7
    @Override
    public void stopGameMatchPlaying() {
        multiGameWebSocketClient.stopGameMatchPlaying();
    }

    @Override
    public void startGameMatchPlaying() {
        multiGameWebSocketClient.startGameMatchPlaying();
    }

    // X
    @Override
    public ObserverOnAbstractEvent getHashSetOfObserverOnAbstractEvent() {
        return hashSetOfObserverOnAbstractEvent;
    }

    @Override
    public LocalClientState getLocalClientState() {
        return localClientState;
    }
}
