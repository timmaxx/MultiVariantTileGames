package timmax.tilegame.websocket.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.stream.Stream;

import org.java_websocket.WebSocket;

import timmax.tilegame.basemodel.credential.Credentials;
import timmax.tilegame.basemodel.protocol.TransportPackageOfClient;
import timmax.tilegame.basemodel.protocol.TransportPackageOfServer;
import timmax.tilegame.basemodel.protocol.TypeOfTransportPackage;
import timmax.tilegame.basemodel.protocol.server.RemoteView;

//import timmax.tilegame.game.minesweeper.model.MinesweeperModel;
import timmax.tilegame.game.sokoban.model.ModelOfServerOfSokoban;
import timmax.tilegame.game.sokoban.model.SokobanModel;

import static java.util.stream.Collectors.toList;
import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;
import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.CREATE_NEW_GAME;

public class ServerIncomingMessageHandler {
    protected MultiGameWebSocketServer multiGameWebSocketServer;
    protected WebSocket webSocket;
    protected TransportPackageOfClient transportPackageOfClient;

    public ServerIncomingMessageHandler(MultiGameWebSocketServer multiGameWebSocketServer, WebSocket webSocket, ByteBuffer byteBuffer) {
        this.multiGameWebSocketServer = multiGameWebSocketServer;
        this.webSocket = webSocket;

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        ObjectInput objectInput;
        try {
            objectInput = new ObjectInputStream(byteArrayInputStream);
        } catch (IOException e) {
            System.out.println("catch (IOException e)");
            throw new RuntimeException(e);
        }

        try {
            transportPackageOfClient = (TransportPackageOfClient) objectInput.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("transportPackageOfClient = " + transportPackageOfClient);

        Thread thread = new Thread(() -> {
            TypeOfTransportPackage typeOfTransportPackage = transportPackageOfClient.getTypeOfTransportPackage();

            if (typeOfTransportPackage == LOGOUT) {
                onLogout();
            } else if (typeOfTransportPackage == LOGIN) {
                onLogin();
            } else if (typeOfTransportPackage == FORGET_GAME_TYPE_SET) {
                onForgetGameTypeSet();
            } else if (typeOfTransportPackage == GET_GAME_TYPE_SET) {
                onGetGameTypeSet();
            } else if (typeOfTransportPackage == FORGET_GAME_TYPE) {
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
            System.out.println("---------- End of public IncomingMessageHandler(MultiGameWebSocketServer multiGameWebSocketServer, WebSocket webSocket, ByteBuffer byteBuffer)");
        });
        thread.start();
    }

    private void onLogout() {
        System.out.println("onLogout");

        sendLogoutAnswer();
    }

    private void onLogin() {
        System.out.println("onLogin");

        String userName = ((String) transportPackageOfClient.get("userName"));
        String password = ((String) transportPackageOfClient.get("password"));

        System.out.println("userName = " + userName + " | " + "password = *"); // Пароль не выводим:

        if (Credentials.isUserAndPasswordCorrect(userName, password)) {
            // ToDo: Нужно зафиксировать для этого webSocket имя пользователя (и потом другие параметры авторизации).
            sendLoginAnswer(userName);
        } else {
            sendLogoutAnswer();
        }
    }

    private void sendLogoutAnswer() {
        multiGameWebSocketServer.send(webSocket, new TransportPackageOfServer(LOGOUT));
    }

    private void sendLoginAnswer(String userName) {
        multiGameWebSocketServer.send(webSocket, new TransportPackageOfServer(
                LOGIN,
                Map.of("userName", userName)
        ));
    }

    private void onForgetGameTypeSet() {
        System.out.println("onForgetGameTypeSet");
        multiGameWebSocketServer.send(webSocket, new TransportPackageOfServer(FORGET_GAME_TYPE_SET));
    }

    private void onGetGameTypeSet() {
        System.out.println("onGetGameTypeSet");
        multiGameWebSocketServer.send(webSocket, new TransportPackageOfServer(
                GET_GAME_TYPE_SET,
                Map.of("gameTypeSet",
                        Stream.of(
                                // ToDo: Перечень классов вариантов игр следует делать не константами в коде. Варианты:
                                //       - файл параметров,
                                //       - классы, хранящиеся по определённому пути.
                                /*MinesweeperModel.class,*/
                                SokobanModel.class
                        ).collect(toList())
                )
        ));
    }

    private void onForgetGameType() {
        System.out.println("onForgetGameType");

        System.out.println("modelOfServer = " + multiGameWebSocketServer.modelOfServer);
        multiGameWebSocketServer.modelOfServer = null;
        System.out.println("modelOfServer = " + multiGameWebSocketServer.modelOfServer);
        multiGameWebSocketServer.send(webSocket, new TransportPackageOfServer(FORGET_GAME_TYPE));
    }

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
}