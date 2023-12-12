package timmax.tilegame.websocket.client;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

import timmax.tilegame.basemodel.protocol.TransportPackageOfServer;

public class ClientIncomingMessageHandler {
    private final MultiGameWebSocketClient multiGameWebSocketClient;
    private final TransportPackageOfServer transportPackageOfServer;


    public ClientIncomingMessageHandler(MultiGameWebSocketClient multiGameWebSocketClient, ByteBuffer byteBuffer) {
        this.multiGameWebSocketClient = multiGameWebSocketClient;

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        // Todo: улучшить качество кода:
        //       Вызов метода у объекта объекта - не хорошая практика!
        //       multiGameWebSocketClient.mapper.readValue
        transportPackageOfServer = multiGameWebSocketClient.mapper.readValue(byteArrayInputStream, TransportPackageOfServer.class);

        System.out.println("transportPackageOfServer = " + transportPackageOfServer);

        Thread thread = new Thread(() -> {
            // TypeOfTransportPackage typeOfTransportPackage = transportPackageOfServer.getTypeOfTransportPackage();

            transportPackageOfServer.execute(multiGameWebSocketClient);
/*
            if (typeOfTransportPackage == FORGET_GAME_TYPE) {
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
*/
            System.out.println("getMainGameClientStatus() = " + multiGameWebSocketClient.getMainGameClientStatus());
            System.out.println("---------- End of public ClientIncomingMessageHandler(MultiGameWebSocketClient multiGameWebSocketClient, ByteBuffer byteBuffer)");
        });
        thread.start();
    }

/*
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
*/
}