package timmax.tilegame.websocket.client;

import java.net.URI;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.ClientState;
import timmax.tilegame.basemodel.protocol.HashSetOfObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.baseview.View;

public class MultiGameWebSocketClientManyTimesUse implements BaseModel {
    private final ClientState<Object> clientState = new ClientState<>(null);
    private final HashSetOfObserverOnAbstractEvent hashSetOfObserverOnAbstractEvent = new HashSetOfObserverOnAbstractEvent();

    private MultiGameWebSocketClient multiGameWebSocketClient;
    private URI uri;


    public void addCallBackOnIncomingTransportPackageEvent(ObserverOnAbstractEvent observerOnAbstractEvent) {
        hashSetOfObserverOnAbstractEvent.add(observerOnAbstractEvent);
    }

    public ClientState<Object> getClientState() {
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
        multiGameWebSocketClient = new MultiGameWebSocketClient(uri, clientState, hashSetOfObserverOnAbstractEvent);

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

    public void gameTypeSelect(Class<? extends ServerBaseModel> serverBaseModelClass) {
        multiGameWebSocketClient.gameTypeSelect(serverBaseModelClass);
    }

    // ----------------------------------------------------------------------------------------------------------------
    @Override
    public void createNewGame() {

    }

    @Override
    public void addView(View view) {
/*
        while ( !getConnection( ).isOpen( )) {
            try {
                Thread.sleep( 50);
            } catch ( InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // System.out.println( "addViewListener view = " + view.toString( ));
        send( "addViewListener view = " + view.toString( ));
        viewList.add( view);
*/
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
}