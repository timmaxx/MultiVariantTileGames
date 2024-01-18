package timmax.tilegame.client.websocket;

import java.net.URI;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.basemodel.protocol.client.LocalClientState;
import timmax.tilegame.basemodel.protocol.HashSetOfObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.baseview.View;

// WebSocket клиент многоразовый
public class MultiGameWebSocketClientManyTimesUse implements IModelOfClient {
    // ToDo: Параметр не должен быть null!
    private final LocalClientState localClientState;
    private final HashSetOfObserverOnAbstractEvent hashSetOfObserverOnAbstractEvent;

    private MultiGameWebSocketClient multiGameWebSocketClient;
    private URI uri;

    public MultiGameWebSocketClientManyTimesUse() {
        localClientState = new LocalClientState(this);
        hashSetOfObserverOnAbstractEvent = new HashSetOfObserverOnAbstractEvent();
        System.out.println("getMainGameClientStatus() = " + getMainGameClientStatus());
    }

    public LocalClientState getLocalClientState() {
        return localClientState;
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

    // Overriden methods from interface IModelOfClient:
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
    public void forgetGamePlaySet() {
        multiGameWebSocketClient.forgetGamePlaySet();
    }

    @Override
    public void getGamePlaySet() {
        multiGameWebSocketClient.getGamePlaySet();
    }

    // X
    @Override
    public void createNewGame() {
        multiGameWebSocketClient.createNewGame();
    }

    @Override
    public void addView(View view) {
        multiGameWebSocketClient.addView(view);
    }

    @Override
    public ObserverOnAbstractEvent getHashSetOfObserverOnAbstractEvent() {
        return hashSetOfObserverOnAbstractEvent;
    }

    // Own methods of the class:
    public void addCallBackOnIncomingTransportPackageEvent(ObserverOnAbstractEvent observerOnAbstractEvent) {
        hashSetOfObserverOnAbstractEvent.add(observerOnAbstractEvent);
    }

    public void close() {
        if (multiGameWebSocketClient == null) {
            return;
        }
        multiGameWebSocketClient.close();
        multiGameWebSocketClient = null;
    }

    public void connect() {
        multiGameWebSocketClient = new MultiGameWebSocketClient(uri, localClientState, hashSetOfObserverOnAbstractEvent);
        multiGameWebSocketClient.connect();
    }
}
