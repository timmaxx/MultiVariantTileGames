package timmax.tilegame.websocket.client;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MultiGameWebSocketClientManyTimesUse {
    private MultiGameWebSocketClient multiGameWebSocketClient;
    private URI uri;

    private final Map<Observer010OnClose, String> mapOfObserver_String__OnClose = new HashMap<>();
    private final Map<Observer011OnOpen, String> mapOfObserver_String__OnOpen = new HashMap<>();
    private final Map<Observer020OnLogout, String> mapOfObserver_String__OnLogout = new HashMap<>();
    private final Map<Observer021OnLogin, String> mapOfObserver_String__OnLogin = new HashMap<>();
    private final Map<Observer032OnGetGameTypeSet, String> mapOfObserver_String__OnGetGameTypeSet = new HashMap< >( );
    /*
        private final Map< MultiGameWebSocketClientObserverOnSelectGameType, String> mapOfMultiGameWebSocketClientObserver_String__OnSelectGameType = new HashMap< >( );
    */

    public MultiGameWebSocketClientManyTimesUse() {
        System.out.println("getMainGameClientStatus() = " + getMainGameClientStatus());
    }

    public MainGameClientStatus getMainGameClientStatus() {
        if (multiGameWebSocketClient == null) {
            return MainGameClientStatus.NO_CONNECT;
        }
        return multiGameWebSocketClient.getMainGameClientStatus();
    }

    public void addViewOnClose(Observer010OnClose observer010OnClose) {
        mapOfObserver_String__OnClose.put(observer010OnClose, "");
        updateListOfViewOfCloseForWebSocketClient();
    }

    private void updateListOfViewOfCloseForWebSocketClient() {
        if (multiGameWebSocketClient == null) {
            return;
        }
        for (Observer010OnClose observer010OnClose : mapOfObserver_String__OnClose.keySet()) {
            multiGameWebSocketClient.addViewOnClose(observer010OnClose);
        }
    }

    public void addViewOnOpen(Observer011OnOpen observer011OnOpen) {
        mapOfObserver_String__OnOpen.put(observer011OnOpen, "");
        updateListOfViewOfOpenForWebSocketClient();
    }

    private void updateListOfViewOfOpenForWebSocketClient() {
        if (multiGameWebSocketClient == null) {
            return;
        }
        for (Observer011OnOpen observer011OnOpen : mapOfObserver_String__OnOpen.keySet()) {
            multiGameWebSocketClient.addViewOnOpen(observer011OnOpen);
        }
    }

    public void addViewOnLogout(Observer020OnLogout observer020OnLogout) {
        mapOfObserver_String__OnLogout.put(observer020OnLogout, "");
        updateListOfViewOfLogoutForWebSocketClient();
    }

    private void updateListOfViewOfLogoutForWebSocketClient() {
        if (multiGameWebSocketClient == null) {
            return;
        }
        for (Observer020OnLogout observer020OnLogout: mapOfObserver_String__OnLogout.keySet()) {
            multiGameWebSocketClient.addViewOnLogout(observer020OnLogout);
        }
    }

    public void addViewOnLogin(Observer021OnLogin observer021OnLogin) {
        mapOfObserver_String__OnLogin.put(observer021OnLogin, "");
        updateListOfViewOfLoginForWebSocketClient();
    }

    private void updateListOfViewOfLoginForWebSocketClient() {
        if (multiGameWebSocketClient == null) {
            return;
        }
        for (Observer021OnLogin observer021OnLogin : mapOfObserver_String__OnLogin.keySet()) {
            multiGameWebSocketClient.addViewOnLogin(observer021OnLogin);
        }
    }

    public void addViewOnGetGameTypeSet(Observer032OnGetGameTypeSet observer032OnGetGameTypeSet) {
        mapOfObserver_String__OnGetGameTypeSet.put(observer032OnGetGameTypeSet, "");
        updateListOfViewOfGetGameTypeSetForWebSocketClient();
    }

    private void updateListOfViewOfGetGameTypeSetForWebSocketClient() {
        if (multiGameWebSocketClient == null) {
            return;
        }
        for (Observer032OnGetGameTypeSet observer032OnGetGameTypeSet : mapOfObserver_String__OnGetGameTypeSet.keySet()) {
            multiGameWebSocketClient.addViewOnGetGameTypeSet(observer032OnGetGameTypeSet);
        }
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
        multiGameWebSocketClient = new MultiGameWebSocketClient(uri);

        updateListOfViewOfCloseForWebSocketClient();
        updateListOfViewOfOpenForWebSocketClient();
        updateListOfViewOfLogoutForWebSocketClient();
        updateListOfViewOfLoginForWebSocketClient();
        updateListOfViewOfGetGameTypeSetForWebSocketClient();

        multiGameWebSocketClient.connect();
    }

    public void logout() {
        multiGameWebSocketClient.logout();
    }

    public void login(String userName, String password) {
        multiGameWebSocketClient.login( userName, password);
    }

    public void getGameTypeSet() {
        multiGameWebSocketClient.getGameTypeSet();
    }
}