package timmax.tilegame.websocket.server;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;

import timmax.tilegame.basemodel.protocol.TransportPackageOfClient;

// import timmax.tilegame.game.minesweeper.model.MinesweeperModel;
// import timmax.tilegame.game.sokoban.model.ModelOfServerOfSokoban;
// import timmax.tilegame.game.sokoban.model.SokobanModel;

public class ServerIncomingMessageHandler {
    private final MultiGameWebSocketServer multiGameWebSocketServer;
    private final WebSocket webSocket;
    private final TransportPackageOfClient<WebSocket> transportPackageOfClient;

    public ServerIncomingMessageHandler(MultiGameWebSocketServer multiGameWebSocketServer, WebSocket webSocket, ByteBuffer byteBuffer) {
        this.multiGameWebSocketServer = multiGameWebSocketServer;
        this.webSocket = webSocket;

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        // Todo: улучшить качество кода:
        //       Вызов метода у объекта объекта - не хорошая практика!
        //       multiGameWebSocketServer.mapper.readValue
        transportPackageOfClient = multiGameWebSocketServer.mapper.readValue(byteArrayInputStream, TransportPackageOfClient.class);

        System.out.println("transportPackageOfClient = " + transportPackageOfClient);

        Thread thread = new Thread(() -> {
            transportPackageOfClient.execute(multiGameWebSocketServer, webSocket);
            /*
            if (typeOfTransportPackage == FORGET_GAME_TYPE) {
                onForgetGameType();
            } else if (typeOfTransportPackage == SELECT_GAME_TYPE) {
                onSelectGameType();
            } else if (typeOfTransportPackage == ADD_VIEW) {
                onAddView();
            } else if (typeOfTransportPackage == CREATE_NEW_GAME) {
                onCreateNewGame();
            } else {
                System.err.println("Server doesn't know received typeOfTransportPackage.");
                System.err.println("typeOfTransportPackage = " + typeOfTransportPackage);
                System.exit(1);
            }
            */
            System.out.println("---------- End of public IncomingMessageHandler(MultiGameWebSocketServer multiGameWebSocketServer, WebSocket webSocket, ByteBuffer byteBuffer)");
        });
        thread.start();
    }

/*
    private void onSelectGameType() {
        System.out.println("onSelectGameType");

        // System.out.println("transportPackageOfClient = " + transportPackageOfClient);
        String model = (String) transportPackageOfClient.get("gameType");
        // ToDo: Проверить, что model одна из списка возможных моделей, которые были отправлены ранее этому клиенту.
        //       И если это не так, то отправить клиенту FORGET_GAME_TYPE.

        {   // Создать новую модель
            System.out.println("modelOfServer = " + multiGameWebSocketServer.modelOfServer);
            // ToDo: Вместо вызова конкретного конструктора, тут нужно создавать экземпляр того типа, который был выбран клиентом.
            multiGameWebSocketServer.modelOfServer = new ModelOfServerOfSokoban<>(multiGameWebSocketServer);
            System.out.println("modelOfServer = " + multiGameWebSocketServer.modelOfServer);
        }

        multiGameWebSocketServer.send(webSocket, new TransportPackageOfServer(
                SELECT_GAME_TYPE,
                Map.of("gameType", model)
        ));
    }

    private void onAddView() {
        System.out.println("onAddView");

        String viewId = (String) transportPackageOfClient.get("viewId");
        // System.out.println("viewId = " + viewId);
        multiGameWebSocketServer.modelOfServer.addRemoteView(new RemoteView<>(webSocket, viewId));

        multiGameWebSocketServer.send(webSocket, new TransportPackageOfServer(
                ADD_VIEW,
                Map.of("viewId", viewId)
        ));
    }

    private void onCreateNewGame() {
        System.out.println("onCreateNewGame");

        multiGameWebSocketServer.modelOfServer.createNewGame();
    }
*/
}