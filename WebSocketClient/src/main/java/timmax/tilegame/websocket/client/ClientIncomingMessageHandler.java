package timmax.tilegame.websocket.client;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;

import timmax.tilegame.basemodel.protocol.TransportPackageOfServer;

public class ClientIncomingMessageHandler {
    private final MultiGameWebSocketClient multiGameWebSocketClient;
    private final TransportPackageOfServer<WebSocket> transportPackageOfServer;


    public ClientIncomingMessageHandler(MultiGameWebSocketClient multiGameWebSocketClient, ByteBuffer byteBuffer) {
        this.multiGameWebSocketClient = multiGameWebSocketClient;

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        // Todo: улучшить качество кода:
        //       Вызов метода у объекта объекта - не хорошая практика!
        //       multiGameWebSocketClient.mapper.readValue
        transportPackageOfServer = multiGameWebSocketClient.mapper.readValue(byteArrayInputStream, TransportPackageOfServer.class);

        System.out.println("transportPackageOfServer = " + transportPackageOfServer);

        Thread thread = new Thread(() -> {
            transportPackageOfServer.execute(multiGameWebSocketClient);
/*
            if (typeOfTransportPackage == GAME_EVENT) {
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
    private void onGameEvent(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("onGameEvent");

        String viewId = (String) (transportPackageOfServer.get("viewId"));
        System.out.println("viewId = " + viewId);

        GameEvent gameEvent = (GameEvent) (transportPackageOfServer.get("gameEvent"));
        System.out.println("gameEvent = " + gameEvent);
    }
*/
}