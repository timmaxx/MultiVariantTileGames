package timmax.tilegame.websocket.client;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

public class MultiGameWebSocketClientManyTimesUse {
    private MultiGameWebSocketClient multiGameWebSocketClient;
    private URI uri;

    // ToDo: Переделать Map на Set.
    private final Map<Observer010OnClose, String> mapOfObserver_String__OnClose = new HashMap<>();
    private final Map<Observer011OnOpen, String> mapOfObserver_String__OnOpen = new HashMap<>();
    private final Map<Observer020OnLogout, String> mapOfObserver_String__OnLogout = new HashMap<>();
    private final Map<Observer021OnLogin, String> mapOfObserver_String__OnLogin = new HashMap<>();
    private final Map<Observer030OnForgetGameTypeSet, String> mapOfObserver_String__OnForgetGameTypeSet = new HashMap<>();
    private final Map<Observer031OnGetGameTypeSet, String> mapOfObserver_String__OnGetGameTypeSet = new HashMap<>();
    private final Map<Observer041OnSelectGameType, String> mapOfObserver_String__OnSelectGameType = new HashMap<>();


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
        for (Observer020OnLogout observer020OnLogout : mapOfObserver_String__OnLogout.keySet()) {
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

    public void addViewOnForgetGameTypeSet(Observer030OnForgetGameTypeSet observer030OnForgetGameTypeSet) {
        mapOfObserver_String__OnForgetGameTypeSet.put(observer030OnForgetGameTypeSet, "");
        updateListOfViewOfForgetGameTypeSetForWebSocketClient();
    }

    private void updateListOfViewOfForgetGameTypeSetForWebSocketClient() {
        if (multiGameWebSocketClient == null) {
            return;
        }
        for (Observer030OnForgetGameTypeSet observer030OnForgetGameTypeSet : mapOfObserver_String__OnForgetGameTypeSet.keySet()) {
            multiGameWebSocketClient.addViewOnForgetGameTypeSet(observer030OnForgetGameTypeSet);
        }
    }

    public void addViewOnGetGameTypeSet(Observer031OnGetGameTypeSet observer031OnGetGameTypeSet) {
        mapOfObserver_String__OnGetGameTypeSet.put(observer031OnGetGameTypeSet, "");
        updateListOfViewOfGetGameTypeSetForWebSocketClient();
    }

    private void updateListOfViewOfGetGameTypeSetForWebSocketClient() {
        if (multiGameWebSocketClient == null) {
            return;
        }
        for (Observer031OnGetGameTypeSet observer031OnGetGameTypeSet : mapOfObserver_String__OnGetGameTypeSet.keySet()) {
            multiGameWebSocketClient.addViewOnGetGameTypeSet(observer031OnGetGameTypeSet);
        }
    }

    public void addViewOnSelectGameType(Observer041OnSelectGameType observer041OnSelectGameType) {
        mapOfObserver_String__OnSelectGameType.put(observer041OnSelectGameType, "");
        updateListOfViewOfSelectGameTypeForWebSocketClient();
    }

    private void updateListOfViewOfSelectGameTypeForWebSocketClient() {
        if (multiGameWebSocketClient == null) {
            return;
        }
        for (Observer041OnSelectGameType observer041OnSelectGameType : mapOfObserver_String__OnSelectGameType.keySet()) {
            multiGameWebSocketClient.addViewOnSelectGameType(observer041OnSelectGameType);
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
        updateListOfViewOfForgetGameTypeSetForWebSocketClient();
        updateListOfViewOfGetGameTypeSetForWebSocketClient();
        updateListOfViewOfSelectGameTypeForWebSocketClient();

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