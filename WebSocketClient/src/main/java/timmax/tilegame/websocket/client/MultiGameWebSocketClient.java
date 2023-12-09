package timmax.tilegame.websocket.client;

import java.io.*;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.*;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.baseview.View;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public class MultiGameWebSocketClient extends WebSocketClient {
    private final ClientState<Object> clientState;
    private final HashSetOfObserverOnAbstractEvent hashSetOfObserverOnAbstractEvent;

    private ObjectOutput objectOutput;


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

    // ToDo: Вынести из этого класса.
    public void encodeExternalizable(Externalizable externalizable) throws IOException {
        objectOutput.writeObject(externalizable);
        System.out.println("----------");
    }

    private void send(TransportPackageOfClient transportPackageOfClient) {
        /*
        //  Ранее (при помощи Jackson перевод в JSON) применялась такая конструкция:
            // В начале класса:
            private final ObjectMapper mapper = new ObjectMapper();
            ...

            // В этом методе:
            StringWriter writer = new StringWriter();
            mapper.writeValue(writer, transportPackageOfClient);
            send(writer.toString());

        //  ToDo: переделать на такую конструкцию:
            // В начале класса:
            private final ??? mapper = new ???();
            ...
            byte[] writer = new ...;
            mapper.writeValue(writer, transportPackageOfClient);
            send(writer);

        //  В т.ч.:
        //  1. и 'encodeExternalizable(Externalizable externalizable)' из того класса будет удалена.
        //  2. аналогично для сервера сделать.
        */

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            objectOutput = new ObjectOutputStream(byteArrayOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            encodeExternalizable(transportPackageOfClient);
            send(byteArrayOutputStream.toByteArray());
        } catch (RuntimeException | IOException rte) {
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
    public void onMessage(ByteBuffer byteBuffer) {
        System.out.println("onMessage(ByteBuffer byteBuffer)");

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        ObjectInput objectInput;
        try {
            objectInput = new ObjectInputStream(byteArrayInputStream);
        } catch (IOException e) {
            System.out.println("catch (IOException e)");
            throw new RuntimeException(e);
        }

        TransportPackageOfServer transportPackageOfServer;
        try {
            transportPackageOfServer = (TransportPackageOfServer)objectInput.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("transportPackageOfServer = " + transportPackageOfServer);

        TypeOfTransportPackage typeOfTransportPackage = transportPackageOfServer.getTypeOfTransportPackage();
        System.out.println("typeOfTransportPackage = " + typeOfTransportPackage);
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
        } else if (typeOfTransportPackage == GAME_EVENT) {
            onGameEvent(transportPackageOfServer);
        } else {
            System.err.println("Client doesn't know received typeOfTransportPackage.");
            System.err.println("typeOfTransportPackage = " + typeOfTransportPackage);
            System.exit(1);
        }

        System.out.println("getMainGameClientStatus() = " + getMainGameClientStatus());
        System.out.println("----------");
    }

    @Override
    public void onMessage(String message) {
        System.err.println("onMessage(String message)");
        System.err.println("This type of message (String) should not be!");
        System.exit(1);
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
        ArrayList<Class<? extends ServerBaseModel>> arrayList = (ArrayList<Class<? extends ServerBaseModel>>) transportPackageOfServer.get("gameTypeSet");
        for (Class<? extends ServerBaseModel> serverBaseModelClass : arrayList) {
            clientState.addServerBaseModelClass(serverBaseModelClass);
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

        // ToDo: Если переделать на сервере отправку класса не строкой, а классом,
        //       то и здесь перевод из строки в класс не понадобится.
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

    protected void onGameEvent(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onGameEvent");

        String viewId = (String) (transportPackageOfServer.get("viewId"));
        System.out.println("viewId = " + viewId);

        GameEvent gameEvent = (GameEvent) (transportPackageOfServer.get("gameEvent"));
        System.out.println("gameEvent = " + gameEvent);
    }
}