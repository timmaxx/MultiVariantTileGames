package timmax.tilegame.websocket.client;

//import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.util.*;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.baseview.View;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public class MultiGameWebSocketClient extends WebSocketClient {
    // private final ObjectMapper mapper = new ObjectMapper();
    private final ClientState<Object> clientState;
    private final HashSetOfObserverOnAbstractEvent hashSetOfObserverOnAbstractEvent;


    public MultiGameWebSocketClient(URI serverUri, ClientState<Object> clientState, HashSetOfObserverOnAbstractEvent hashSetOfObserverOnAbstractEvent) {
        super(serverUri);
        this.clientState = clientState;
        this.hashSetOfObserverOnAbstractEvent = hashSetOfObserverOnAbstractEvent;
        System.out.println(serverUri);
    }

    public MainGameClientStatus getMainGameClientStatus() {
        if (!isOpen() || isClosed()) {
            return MainGameClientStatus.NO_CONNECT;
        }
        if (isOpen()) {
            return clientState.getMainGameClientStatus();
        }
        throw new RuntimeException("Unknown state.");
    }

    // 2
    public void logout() {
        send(new TransportPackageOfClient(LOGOUT));
    }

    public void login(String userName, String password) {
        send(new TransportPackageOfClient(
                LOGIN,
                Map.of(
                        "userName", userName,
                        "password", password
                ))
        );
    }

    // 3
    public void forgetGameTypeSet() {
        send(new TransportPackageOfClient(FORGET_GAME_TYPE_SET));
    }

    public void getGameTypeSet() {
        send(new TransportPackageOfClient(GET_GAME_TYPE_SET));
    }

    // 4
    public void forgetGameType() {
        send(new TransportPackageOfClient(FORGET_GAME_TYPE));
    }

    public void gameTypeSelect(Class<? extends ServerBaseModel> serverBaseModelClass) {
        send(new TransportPackageOfClient(
                SELECT_GAME_TYPE,
                Map.of(
                        "gameType",
                        serverBaseModelClass.getName()
                ))
        );
    }

    public void addView(View view) {
        System.out.println("addView(View view)");
        System.out.println("viewId = " + view.toString());
        clientState.addView(view.toString());
        send(new TransportPackageOfClient(
                ADD_VIEW,
                Map.of(
                        "viewId",
                        view.toString()
                ))
        );
    }

    public void createNewGame() {
        System.out.println("createNewGame");
        send(new TransportPackageOfClient(CREATE_NEW_GAME));
    }

    private void send(TransportPackageOfClient transportPackageOfClient) {
        // System.out.println("getMainGameClientStatus() = " + getMainGameClientStatus());

        // Здесь "оборачиваем" 'new TransportPackageOfClient()' try-ем, т.к. если исключения будут
        // возникать в глубине вызовов, JVM просто ничего не сделает с исключениями.
        // Это из-за того, что работа метода идёт не в основном потоке, а в дочернем.
        // Ещё можно было-бы попробовать поработать с setUncaughtExceptionHandler(), если-бы WebSocketClient
        // (да и WebSocketServer) были-бы наследниками Thread. Но это не так.

        try {
            StringWriter writer = new StringWriter();
            // mapper.writeValue(writer, transportPackageOfClient);
/*
            System.out.println("sendRequest");
            System.out.println("--- begin of message ---");
            System.out.println(writer);
            System.out.println("--- end of message ---");
*/
            send(writer.toString());
        } /*catch (IOException e) {
            throw new RuntimeException(e);
        }*/ catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("onClose");

        clientState.setUserName("");
        System.out.println("getMainGameClientStatus() = " + getMainGameClientStatus());
        hashSetOfObserverOnAbstractEvent.updateConnectStatePane(CLOSE);

        System.out.println("----------");
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("onOpen");

        clientState.setUserName("");
        System.out.println("getMainGameClientStatus() = " + getMainGameClientStatus());
        hashSetOfObserverOnAbstractEvent.updateConnectStatePane(OPEN);

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
            TransportPackageOfServer transportPackageOfServer = null; // mapper.readValue(message, TransportPackageOfServer.class);
            TypeOfTransportPackage typeOfTransportPackage = transportPackageOfServer.getTypeOfTransportPackage();
            if (typeOfTransportPackage == LOGOUT) {
                onLogout(transportPackageOfServer);
            } else if (typeOfTransportPackage == LOGIN) {
                onLogin(transportPackageOfServer);
            } else if (typeOfTransportPackage == FORGET_GAME_TYPE_SET) {
                onForgetGameTypeSet(transportPackageOfServer);
            } else if (typeOfTransportPackage == GET_GAME_TYPE_SET) {
                onGetGameTypeSet(transportPackageOfServer);
            } else if (typeOfTransportPackage == FORGET_GAME_TYPE) {
                onForgetGameType(transportPackageOfServer);
            } else if (typeOfTransportPackage == SELECT_GAME_TYPE) {
                onSelectGameType(transportPackageOfServer);
            } else if (typeOfTransportPackage == ADD_VIEW) {
                onAddView(transportPackageOfServer);
            } else {
                System.err.println("Client doesn't know received typeOfTransportPackage.");
                System.err.println("typeOfTransportPackage = " + typeOfTransportPackage);
                System.exit(1);
            }
        } /*catch (JsonProcessingException jpe) {
            // От сервера поступило что-то, что не понятно клиенту.
            // Можно:
            // 1. Либо отключиться от сервера.
            // 2. Совсем упасть клиенту.

            // throw Должно было привести к полному падению. Не получается почему-то.
            // throw new RuntimeException(jpe);

            // Тогда будем падать так:
            jpe.printStackTrace();
            System.exit(1);
        }*/ catch (RuntimeException rte) {
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
        hashSetOfObserverOnAbstractEvent.updateConnectStatePane(LOGOUT);
    }

    protected void onLogin(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onLogin");

        clientState.setUserName((String) transportPackageOfServer.get("userName"));
        hashSetOfObserverOnAbstractEvent.updateConnectStatePane(LOGIN);
    }

    protected void onForgetGameTypeSet(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onForgetGameTypeSet");

        clientState.setArrayListOfServerBaseModelClass(new ArrayList<>());
        hashSetOfObserverOnAbstractEvent.updateConnectStatePane(FORGET_GAME_TYPE_SET);
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
        hashSetOfObserverOnAbstractEvent.updateConnectStatePane(GET_GAME_TYPE_SET);
    }

    protected void onForgetGameType(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onForgetGameType");

        clientState.setServerBaseModelClass(null);
        hashSetOfObserverOnAbstractEvent.updateConnectStatePane(FORGET_GAME_TYPE);
    }

    protected void onSelectGameType(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onSelectGameType");

        String serverBaseModelString = (String) (transportPackageOfServer.get("gameType"));
        try {
            clientState.setServerBaseModelClass((Class<? extends ServerBaseModel>) Class.forName(serverBaseModelString));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        hashSetOfObserverOnAbstractEvent.updateConnectStatePane(SELECT_GAME_TYPE);
    }

    protected void onAddView(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onAddView");

        String viewId = (String) (transportPackageOfServer.get("viewId"));
        System.out.println("viewId = " + viewId);

        clientState.confirmView(viewId);
    }
}