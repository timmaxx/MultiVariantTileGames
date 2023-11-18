package timmax.tilegame.websocket.client;

import java.net.URI;

import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.ClientState;

public class MultiGameWebSocketClientManyTimesUse {
    private final ClientState clientState = new ClientState();
    private final SetOfObserverOnAbstractEvent setOfObserverOnAbstractEvent = new SetOfObserverOnAbstractEvent();

    private MultiGameWebSocketClient multiGameWebSocketClient;
    private URI uri;


    public void addViewOnAnyEvent(ObserverOnAbstractEvent observerOnAbstractEvent) {
        setOfObserverOnAbstractEvent.add(observerOnAbstractEvent);
    }

    public ClientState getClientState() {
        return clientState;
    }

    public MultiGameWebSocketClientManyTimesUse() {
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

    public void close() {
        if (multiGameWebSocketClient == null) {
            return;
        }
        multiGameWebSocketClient.close();
        multiGameWebSocketClient = null;
    }

    public void connect() {
        multiGameWebSocketClient = new MultiGameWebSocketClient(uri, clientState, setOfObserverOnAbstractEvent);

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

    public void gameTypeSelect(Class<? extends ServerBaseModel> serverBaseModelClass) {
        multiGameWebSocketClient.gameTypeSelect(serverBaseModelClass);
    }
}