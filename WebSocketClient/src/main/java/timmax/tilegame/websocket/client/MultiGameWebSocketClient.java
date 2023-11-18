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
import timmax.tilegame.basemodel.protocol.ClientState;
import timmax.tilegame.basemodel.protocol.TransportPackageOfClient;
import timmax.tilegame.basemodel.protocol.TransportPackageOfServer;
import timmax.tilegame.basemodel.protocol.TypeOfTransportPackage;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public class MultiGameWebSocketClient extends WebSocketClient {
    private final ObjectMapper mapper = new ObjectMapper();
    private final ClientState clientState;
    private final SetOfObserverOnAbstractEvent setOfObserverOnAbstractEvent;


    public MainGameClientStatus getMainGameClientStatus() {
        if (!isOpen() || isClosed()) {
            return MainGameClientStatus.NO_CONNECT;
        }
        if (isOpen()) {
            return clientState.getMainGameClientStatus();
        }
        throw new RuntimeException("Unknown state.");
    }

    public MultiGameWebSocketClient(URI serverUri, ClientState clientState, SetOfObserverOnAbstractEvent setOfObserverOnAbstractEvent) {
        super(serverUri);
        this.clientState = clientState;
        this.setOfObserverOnAbstractEvent = setOfObserverOnAbstractEvent;
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

    // 4
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
        System.out.println("getMainGameClientStatus() = " + getMainGameClientStatus());
        setOfObserverOnAbstractEvent.updateConnectStatePane(CLOSE);

        System.out.println("----------");
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("onOpen");

        clientState.setUserName("");
        System.out.println("getMainGameClientStatus() = " + getMainGameClientStatus());
        setOfObserverOnAbstractEvent.updateConnectStatePane(OPEN);

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
        setOfObserverOnAbstractEvent.updateConnectStatePane(LOGOUT);
    }

    protected void onLogin(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onLogin");

        clientState.setUserName((String) transportPackageOfServer.get("userName"));
        setOfObserverOnAbstractEvent.updateConnectStatePane(LOGIN);
    }

    protected void onForgetGameTypeSet(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onForgetGameTypeSet");

        clientState.setArrayListOfServerBaseModelClass(new ArrayList<>());
        setOfObserverOnAbstractEvent.updateConnectStatePane(FORGET_GAME_TYPE_SET);
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
        setOfObserverOnAbstractEvent.updateConnectStatePane(GET_GAME_TYPE_SET);
    }

    protected void onSelectGameType(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onSelectGameType");

        String serverBaseModelString = (String) (transportPackageOfServer.get("gameType"));
        try {
            clientState.setServerBaseModelClass((Class<? extends ServerBaseModel>) Class.forName(serverBaseModelString));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setOfObserverOnAbstractEvent.updateConnectStatePane(SELECT_GAME_TYPE);
    }
}