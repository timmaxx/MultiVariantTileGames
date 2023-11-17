package timmax.tilegame.websocket.client;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.credential.ResultOfCredential;
import timmax.tilegame.basemodel.protocol.ClientState;
import timmax.tilegame.basemodel.protocol.TransportPackageOfClient;
import timmax.tilegame.basemodel.protocol.TransportPackageOfServer;
import timmax.tilegame.basemodel.protocol.TypeOfTransportPackage;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public class MultiGameWebSocketClient extends WebSocketClient {
    private final ObjectMapper mapper = new ObjectMapper();

    // ToDo: Переделать Map на Set.
    private final Map<Observer010OnClose, String> mapOfObserver_String__OnClose = new HashMap<>();
    private final Map<Observer011OnOpen, String> mapOfObserver_String__OnOpen = new HashMap<>();
    private final Map<Observer020OnLogout, String> mapOfObserver_String__OnLogout = new HashMap<>();
    private final Map<Observer021OnLogin, String> mapOfObserver_String__OnLogin = new HashMap<>();
    private final Map<Observer030OnForgetGameTypeSet, String> mapOfObserver_String__OnForgetGameTypeSet = new HashMap<>();
    private final Map<Observer031OnGetGameTypeSet, String> mapOfObserver_String__OnGetGameTypeSet = new HashMap<>();
    private final Map<Observer041OnSelectGameType, String> mapOfObserver_String__OnSelectGameType = new HashMap<>();
    private final ClientState clientState = new ClientState();


    public MainGameClientStatus getMainGameClientStatus() {
        if (!isOpen() || isClosed()) {
            return MainGameClientStatus.NO_CONNECT;
        }
        if (isOpen()) {
            return clientState.getMainGameClientStatus();
        }
        throw new RuntimeException("Unknown state.");
    }

    public void addViewOnClose(Observer010OnClose observer010OnClose) {
        mapOfObserver_String__OnClose.put(observer010OnClose, "");
    }

    public void addViewOnOpen(Observer011OnOpen observer011OnOpen) {
        mapOfObserver_String__OnOpen.put(observer011OnOpen, "");
    }

    public void addViewOnLogout(Observer020OnLogout observerOnLogout) {
        mapOfObserver_String__OnLogout.put(observerOnLogout, "");
    }

    public void addViewOnLogin(Observer021OnLogin observer021OnLogin) {
        mapOfObserver_String__OnLogin.put(observer021OnLogin, "");
    }

    public void addViewOnForgetGameTypeSet(Observer030OnForgetGameTypeSet observer030OnForgetGameTypeSet) {
        mapOfObserver_String__OnForgetGameTypeSet.put(observer030OnForgetGameTypeSet, "");
    }

    public void addViewOnGetGameTypeSet(Observer031OnGetGameTypeSet ObserverOnGetGameTypeSet) {
        mapOfObserver_String__OnGetGameTypeSet.put(ObserverOnGetGameTypeSet, "");
    }

    public void addViewOnSelectGameType(Observer041OnSelectGameType observer041OnSelectGameType) {
        mapOfObserver_String__OnSelectGameType.put(observer041OnSelectGameType, "");
    }

    public MultiGameWebSocketClient(URI serverUri) {
        super(serverUri);
        System.out.println(serverUri);
    }

    // 2
    public void logout() {
        try {
            sendRequest(new TransportPackageOfClient(LOGOUT));
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
    }

    public void login(String userName, String password) {
        // Здесь и в других методах "оборачиваем" 'new TransportPackageOfClient()' try-ем, т.к. если исключения будут
        // возникать в глубине вызовов, но учитывая, что работа метода идёт не в основном потоке, а в дочернем,
        // JVM просто ничего не сделает с исключениями.
        // Ещё можно было-бы попробовать поработать с setUncaughtExceptionHandler(), если-бы WebSocketClient
        // (да и WebSocketServer) били-бы наследниками Thread. Но это не так.
        try {
            sendRequest(new TransportPackageOfClient(
                    LOGIN,
                    Map.of(
                            "userName", userName,
                            "password", password
                    ))
            );
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
    }

    // 3
    public void forgetGameTypeSet() {
        // ToDo: forgetSelectGameType()
        try {
            sendRequest(new TransportPackageOfClient(FORGET_GAME_TYPE_SET));
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
    }

    public void getGameTypeSet() {
        try {
            sendRequest(new TransportPackageOfClient(GET_GAME_TYPE_SET));
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
    }

    public void gameTypeSelect(Class<? extends ServerBaseModel> serverBaseModelClass) {
        try {
            sendRequest(new TransportPackageOfClient(
                    SELECT_GAME_TYPE,
                    Map.of(
                            "gameType",
                            serverBaseModelClass.getName()
                    ))
            );
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
    }

    private void sendRequest(TransportPackageOfClient transportPackageOfClient) {
        // System.out.println("getMainGameClientStatus() = " + getMainGameClientStatus());
        try {
            StringWriter writer = new StringWriter();
            mapper.writeValue(writer, transportPackageOfClient);
/*
            System.out.println("sendRequest");
            System.out.println("--- begin of message ---");
            System.out.println(writer);
            System.out.println("--- end of message ---");
*/
            send(writer.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("onClose");

        clientState.setUserName("");
        for (Observer010OnClose observer010OnClose : mapOfObserver_String__OnClose.keySet()) {
            observer010OnClose.updateOnClose();
        }
        System.out.println("getMainGameClientStatus() = " + getMainGameClientStatus());
        System.out.println("----------");
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("onOpen");

        clientState.setUserName("");
        System.out.println("getMainGameClientStatus() = " + getMainGameClientStatus());
        for (Observer011OnOpen observer011OnOpen : mapOfObserver_String__OnOpen.keySet()) {
            observer011OnOpen.updateOnOpen();
        }
        System.out.println("----------");
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("onError");

        ex.printStackTrace();
        System.err.println("----------");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("onMessage");

        // System.out.println("getMainGameClientStatus() = " + getMainGameClientStatus());
/*
            System.out.println("--- begin of message ---");
            System.out.println(message);
            System.out.println("--- end of message ---");
*/
        try {
            TransportPackageOfServer transportPackageOfServer = mapper.readValue(message, TransportPackageOfServer.class);
            TypeOfTransportPackage typeOfTransportPackageOfServer = transportPackageOfServer.getTypeOfTransportPackage();
            if (typeOfTransportPackageOfServer == LOGOUT) {
                onLogout(transportPackageOfServer);
            } else if (typeOfTransportPackageOfServer == LOGIN) {
                onLogin(transportPackageOfServer);
            } else if (typeOfTransportPackageOfServer == FORGET_GAME_TYPE_SET) {
                onForgetGameTypeSet(transportPackageOfServer);
            } else if (typeOfTransportPackageOfServer == GET_GAME_TYPE_SET) {
                onGetGameTypeSet(transportPackageOfServer);
            } else if (typeOfTransportPackageOfServer == SELECT_GAME_TYPE) {
                onSelectGameType(transportPackageOfServer);
            }
        } catch (JsonProcessingException jpe) {
            // От сервера поступило что-то, что не понятно клиенту.
            // Можно:
            // 1. Либо отключиться от сервера.
            // 2. Совсем упасть клиенту.

            // throw Должно было привести к полному падению. Не получается почему-то.
            // throw new RuntimeException(jpe);

            // Тогда будем падать так:
            jpe.printStackTrace();
            System.exit(1);
        } catch (RuntimeException rte) {
            System.out.println("onMessage");
            rte.printStackTrace();
            System.exit(1);
            System.out.println("onMessage. End");
        }
        System.out.println("getMainGameClientStatus() = " + getMainGameClientStatus());
        System.out.println("----------");
    }

    protected void onLogout(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onLogout");

        clientState.setUserName("");
        for (Observer020OnLogout observer021OnLogout : mapOfObserver_String__OnLogout.keySet()) {
            observer021OnLogout.updateOnLogout();
        }
    }

    protected void onLogin(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onLogin");

        ResultOfCredential resultOfCredential = ResultOfCredential.valueOf((String) (transportPackageOfServer.get("resultOfCredential")));
        if (resultOfCredential == ResultOfCredential.NOT_AUTHORISED) {
            System.out.println("Сервер сообщил о не успешных идентификации и/или аутентификации и/или авторизации.");
            clientState.setUserName("");
        } else if (resultOfCredential == ResultOfCredential.AUTHORISED) {
            System.out.println("Сервер сообщил об успешных идентификации, аутентификации и авторизации.");
            clientState.setUserName((String) transportPackageOfServer.get("userName"));
        }

        for (Observer021OnLogin observer021OnLogin : mapOfObserver_String__OnLogin.keySet()) {
            observer021OnLogin.updateOnLogin(resultOfCredential);
        }
    }

    protected void onForgetGameTypeSet(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onForgetGameTypeSet");

        clientState.setArrayListOfServerBaseModelClass(new ArrayList<>());
        for (Observer030OnForgetGameTypeSet observer030OnForgetGameTypeSet : mapOfObserver_String__OnForgetGameTypeSet.keySet()) {
            observer030OnForgetGameTypeSet.updateOnForgetGameTypeSet();
        }
    }

    protected void onGetGameTypeSet(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onGetGameTypeSet");

        clientState.setArrayListOfServerBaseModelClass(new ArrayList<>());
        ArrayList<String> arrayList = (ArrayList<String>) transportPackageOfServer.get("gameTypeSet");
        for (String serverBaseModelClass : arrayList) {
            Class<? extends ServerBaseModel> clazz;
            try {
                clazz = (Class<? extends ServerBaseModel>) Class.forName(serverBaseModelClass);
                clientState.addServerBaseModelClass(clazz);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        for (Observer031OnGetGameTypeSet observer031OnGetGameTypeSet : mapOfObserver_String__OnGetGameTypeSet.keySet()) {
            observer031OnGetGameTypeSet.updateOnGetGameTypeSet(clientState.getArrayListOfServerBaseModelClass());
        }
    }

    protected void onSelectGameType(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onSelectGameType");

        String serverBaseModelString = (String) (transportPackageOfServer.get("gameType"));
        try {
            clientState.setServerBaseModelClass((Class<? extends ServerBaseModel>) Class.forName(serverBaseModelString));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Observer041OnSelectGameType observer041OnSelectGameType : mapOfObserver_String__OnSelectGameType.keySet()) {
            observer041OnSelectGameType.updateOnSelectGameType(clientState.getServerBaseModelClass());
        }
    }
}