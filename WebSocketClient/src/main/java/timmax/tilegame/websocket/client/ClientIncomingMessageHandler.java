package timmax.tilegame.websocket.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.TransportPackageOfServer;
import timmax.tilegame.basemodel.protocol.TypeOfTransportPackage;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;


public class ClientIncomingMessageHandler {
    private final MultiGameWebSocketClient multiGameWebSocketClient;
    private final TransportPackageOfServer transportPackageOfServer;


    public ClientIncomingMessageHandler(MultiGameWebSocketClient multiGameWebSocketClient, ByteBuffer byteBuffer) {
        this.multiGameWebSocketClient = multiGameWebSocketClient;

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        ObjectInput objectInput;
        try {
            objectInput = new ObjectInputStream(byteArrayInputStream);
        } catch (IOException e) {
            System.out.println("catch (IOException e)");
            throw new RuntimeException(e);
        }

        try {
            transportPackageOfServer = (TransportPackageOfServer)objectInput.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("transportPackageOfServer = " + transportPackageOfServer);

        Thread thread = new Thread(() -> {
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
            } else if (typeOfTransportPackage == GAME_EVENT) {
                onGameEvent(transportPackageOfServer);
            } else {
                System.err.println("Client doesn't know received typeOfTransportPackage.");
                System.err.println("typeOfTransportPackage = " + typeOfTransportPackage);
                System.exit(1);
            }

            System.out.println("getMainGameClientStatus() = " + multiGameWebSocketClient.getMainGameClientStatus());
            System.out.println("---------- End of public ClientIncomingMessageHandler(MultiGameWebSocketClient multiGameWebSocketClient, ByteBuffer byteBuffer)");
        });
        thread.start();
    }


    private void onLogout(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onLogout");

        multiGameWebSocketClient.clientState.setUserName("");
        multiGameWebSocketClient.hashSetOfObserverOnAbstractEvent.updateConnectStatePane(LOGOUT);
    }

    private void onLogin(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onLogin");

        multiGameWebSocketClient.clientState.setUserName((String) transportPackageOfServer.get("userName"));
        multiGameWebSocketClient.hashSetOfObserverOnAbstractEvent.updateConnectStatePane(LOGIN);
    }

    private void onForgetGameTypeSet(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onForgetGameTypeSet");

        multiGameWebSocketClient.clientState.setArrayListOfServerBaseModelClass(new ArrayList<>());
        multiGameWebSocketClient.hashSetOfObserverOnAbstractEvent.updateConnectStatePane(FORGET_GAME_TYPE_SET);
    }

    private void onGetGameTypeSet(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onGetGameTypeSet");

        multiGameWebSocketClient.clientState.setArrayListOfServerBaseModelClass(new ArrayList<>());
        ArrayList<Class<? extends ServerBaseModel>> arrayList = (ArrayList<Class<? extends ServerBaseModel>>) transportPackageOfServer.get("gameTypeSet");
        for (Class<? extends ServerBaseModel> serverBaseModelClass : arrayList) {
            multiGameWebSocketClient.clientState.addServerBaseModelClass(serverBaseModelClass);
        }
        multiGameWebSocketClient.hashSetOfObserverOnAbstractEvent.updateConnectStatePane(GET_GAME_TYPE_SET);
    }

    private void onForgetGameType(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onForgetGameType");

        multiGameWebSocketClient.clientState.setServerBaseModelClass(null);
        multiGameWebSocketClient.hashSetOfObserverOnAbstractEvent.updateConnectStatePane(FORGET_GAME_TYPE);
    }

    private void onSelectGameType(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onSelectGameType");

        // ToDo: Если переделать на сервере отправку класса не строкой, а классом,
        //       то и здесь перевод из строки в класс не понадобится.
        String serverBaseModelString = (String) (transportPackageOfServer.get("gameType"));
        try {
            multiGameWebSocketClient.clientState.setServerBaseModelClass((Class<? extends ServerBaseModel>) Class.forName(serverBaseModelString));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        multiGameWebSocketClient.hashSetOfObserverOnAbstractEvent.updateConnectStatePane(SELECT_GAME_TYPE);
    }

    private void onAddView(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onAddView");

        String viewId = (String) (transportPackageOfServer.get("viewId"));
        System.out.println("viewId = " + viewId);

        multiGameWebSocketClient.clientState.confirmView(viewId);
    }

    private void onGameEvent(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onGameEvent");

        String viewId = (String) (transportPackageOfServer.get("viewId"));
        System.out.println("viewId = " + viewId);

        GameEvent gameEvent = (GameEvent) (transportPackageOfServer.get("gameEvent"));
        System.out.println("gameEvent = " + gameEvent);
    }
}