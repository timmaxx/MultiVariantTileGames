package timmax.tilegame.client.websocket;

import java.net.URI;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.basemodel.protocol.client.LocalClientState;
import timmax.tilegame.basemodel.protocol.HashSetOfObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfClient;

// WebSocket клиент многоразовый
public class MultiGameWebSocketClientManyTimesUse implements IModelOfClient {
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

    // Overriden methods from interface IModelOfClient:
    @Override
    public void logout() {
        multiGameWebSocketClient.logout();
    }

    @Override
    public void login(String userName, String password) {
        multiGameWebSocketClient.login(userName, password);
    }

    @Override
    public void forgetGameTypeSet() {
        multiGameWebSocketClient.forgetGameTypeSet();
    }

    @Override
    public void getGameTypeSet() {
        multiGameWebSocketClient.getGameTypeSet();
    }

    @Override
    public void forgetGameType() {
        multiGameWebSocketClient.forgetGameType();
    }

    @Override
    public void gameTypeSelect(ModelOfServerDescriptor modelOfServerDescriptor) {
        multiGameWebSocketClient.gameTypeSelect(modelOfServerDescriptor);
    }

    @Override
    public void createNewGame() {
        multiGameWebSocketClient.createNewGame();
    }

    @Override
    public void addView(View view) {
        multiGameWebSocketClient.addView(view);
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

    // ToDo: Это временное решение. Потом удалить.
    public TransportOfClient getMultiGameWebSocketClient() {
        return multiGameWebSocketClient;
    }
}
