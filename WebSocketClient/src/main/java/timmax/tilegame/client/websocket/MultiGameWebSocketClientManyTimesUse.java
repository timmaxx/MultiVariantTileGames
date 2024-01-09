package timmax.tilegame.client.websocket;

import java.net.URI;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.client.LocalClientState;
import timmax.tilegame.basemodel.protocol.HashSetOfObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfClient;

// WebSocket клиент многоразовый
public class MultiGameWebSocketClientManyTimesUse implements BaseModel {
    // ToDo: Параметр не должен быть null!
    private final LocalClientState localClientState = new LocalClientState(null);
    private final HashSetOfObserverOnAbstractEvent hashSetOfObserverOnAbstractEvent = new HashSetOfObserverOnAbstractEvent();

    private MultiGameWebSocketClient multiGameWebSocketClient;
    private URI uri;

    public MultiGameWebSocketClientManyTimesUse() {
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

    // Overiden methods from interface BaseModel:
    @Override
    public void createNewGame() {
        multiGameWebSocketClient.createNewGame();
    }

    @Override
    public void addView(View view) {
        multiGameWebSocketClient.addView(view);
    }

    @Override
    public void restart() {
    }

    @Override
    public void nextLevel() {
    }

    @Override
    public void prevLevel() {
    }

    @Override
    public void win() {
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

    public void logout() {
        multiGameWebSocketClient.logout();
    }

    public void login(String userName, String password) {
        multiGameWebSocketClient.login(userName, password);
    }

    public void forgetGameTypeSet() {
        multiGameWebSocketClient.forgetGameTypeSet();
    }

    public void getGameTypeSet() {
        multiGameWebSocketClient.getGameTypeSet();
    }

    public void forgetGameType() {
        multiGameWebSocketClient.forgetGameType();
    }

    public void gameTypeSelect(String serverBaseModelClass) {
        multiGameWebSocketClient.gameTypeSelect(serverBaseModelClass);
    }

    // ToDo: Это временное решение. Потом удалить.
    public TransportOfClient getMultiGameWebSocketClient() {
        return multiGameWebSocketClient;
    }
}
