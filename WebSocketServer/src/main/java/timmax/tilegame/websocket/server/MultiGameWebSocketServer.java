package timmax.tilegame.websocket.server;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.stream.Stream;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import timmax.tilegame.basemodel.credential.Credentials;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server.ModelOfServer;
import timmax.tilegame.basemodel.protocol.server.RemoteView;
import timmax.tilegame.game.sokoban.model.ModelOfServerOfSokoban;
import timmax.tilegame.transport.TransportOfModel;

//import timmax.tilegame.game.minesweeper.model.MinesweeperModel;
import timmax.tilegame.game.sokoban.model.SokobanModel;

import static java.util.stream.Collectors.toList;
import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public class MultiGameWebSocketServer extends WebSocketServer implements TransportOfModel<WebSocket> {
    private ModelOfServer<WebSocket> modelOfServer;

    private ObjectOutput objectOutput;


    public MultiGameWebSocketServer(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void sendGameEvent(GameEvent gameEvent) {
        throw new RuntimeException("Не использовать этот метод. Потом удалить его из классов и интерфейсов!");
    }

    @Override
    public void sendGameEvent(RemoteView<WebSocket> remoteView, GameEvent gameEvent) {
/*
        System.out.println("MultiGameWebSocketServer");
        System.out.println("public void sendGameEvent(RemoteView<WebSocket> remoteView, GameEvent gameEvent)");
        System.out.println("remoteView = " + remoteView);
*/
        TransportPackageOfServer transportPackageOfServer = new TransportPackageOfServer(
                GAME_EVENT,
                Map.of("viewId", remoteView.getViewId(),
                        "gameEvent", gameEvent)
        );
        send(remoteView.getClientId(), transportPackageOfServer);
    }

    public void encodeExternalizable(Externalizable externalizable) throws IOException {
        objectOutput.writeObject(externalizable);
        System.out.println("----------");
    }

    private void send(WebSocket webSocket, TransportPackageOfServer transportPackageOfServer) {
        System.out.println("private void send(WebSocket webSocket, TransportPackageOfServer transportPackageOfServer)");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            objectOutput = new ObjectOutputStream(byteArrayOutputStream); // new ObjectOutputStream(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            encodeExternalizable(transportPackageOfServer);
            webSocket.send(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            System.err.println("catch (IOException e)");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void onStart() {
        System.out.println("MultiGameWebSocketServer started on port: " + getPort() + ".");
        System.err.println("----------");
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        System.out.println("onClose");
        System.out.println(webSocket + ".");
        System.out.println("Connect was closed.");
        System.out.println("Code = " + code + ". Reason = " + reason + ". Remote = " + remote + ".");
        System.out.println("----------");
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        System.out.println("onOpen");
        // System.out.println("Connect from '" + webSocket + "' are opened.");
        System.out.println(webSocket + ".");
        // System.out.println(webSocket + ". Connect are opened.");
        // ToDo: Для каждого соединения можно создавать отдельный поток-нить.
        //       Соответственно, нужна карта, в которой будет храниться:
        //       webSocket, нить, модель игры.
        //       Но, вполне возможно, что это и так уже делается ядром WinSocket...
        System.out.println("----------");
    }

    @Override
    public void onError(WebSocket webSocket, Exception ex) {
        System.err.println("onError");
        System.err.println(webSocket + ".");
        ex.printStackTrace();
        System.err.println("----------");
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        System.out.println("onMessage(WebSocket webSocket, ByteBuffer byteBuffer)");
        System.out.println(webSocket);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        ObjectInput objectInput;
        try {
            objectInput = new ObjectInputStream(byteArrayInputStream);
        } catch (IOException e) {
            System.out.println("catch (IOException e)");
            throw new RuntimeException(e);
        }
        TransportPackageOfClient transportPackageOfClient;
        try {
            transportPackageOfClient = (TransportPackageOfClient)objectInput.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("transportPackageOfClient = " + transportPackageOfClient);

        TypeOfTransportPackage typeOfTransportPackage = transportPackageOfClient.getTypeOfTransportPackage();
        if (typeOfTransportPackage == LOGOUT) {
            // System.out.println("typeOfTransportPackage == LOGOUT");
            onLogout(webSocket);
        } else if (typeOfTransportPackage == LOGIN) {
            // System.out.println("typeOfTransportPackage == LOGIN");
            onLogin(webSocket, transportPackageOfClient);
        } else if (typeOfTransportPackage == FORGET_GAME_TYPE_SET) {
            onForgetGameTypeSet(webSocket);
        } else if (typeOfTransportPackage == GET_GAME_TYPE_SET) {
            onGetGameTypeSet(webSocket);
        } else if (typeOfTransportPackage == FORGET_GAME_TYPE) {
            onForgetGameType(webSocket);
        } else if (typeOfTransportPackage == SELECT_GAME_TYPE) {
            onSelectGameType(webSocket, transportPackageOfClient);
        } else if (typeOfTransportPackage == ADD_VIEW) {
            onAddView(webSocket, transportPackageOfClient);
        } else if (typeOfTransportPackage == CREATE_NEW_GAME) {
            onCreateNewGame(webSocket, transportPackageOfClient);
        } else {
            System.err.println("Server doesn't know received typeOfTransportPackage.");
            System.err.println("typeOfTransportPackage = " + typeOfTransportPackage);
            System.exit(1);
        }
        System.out.println("----------");
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        System.err.println("onMessage(WebSocket webSocket, String message)");
        System.err.println(webSocket);
        System.err.println("This type of message (String) should not be!");
        System.exit(1);
    }

    protected void onLogout(WebSocket webSocket) {
        System.out.println("onLogout");

        sendLogoutAnswer(webSocket);
    }

    protected void onLogin(WebSocket webSocket, TransportPackageOfClient transportPackageOfClient) {
        System.out.println("onLogin");

        String userName = ((String) transportPackageOfClient.get("userName"));
        String password = ((String) transportPackageOfClient.get("password"));

        System.out.println("userName = " + userName + " | " + "password = *"); // Пароль не выводим:

        // Имя пользователя и пароль извлечены. Теперь можно в отдельном потоке (вероятно в том, который был
        // создан при onOpen) сверить входящие имя пользователя и пароль с теми, которые есть на сервере.
        // И результат отправить клиенту.
        // Но сейчас (пока с отдельными нитями не начали реализовывать) запросим в текущей нити.

        if (Credentials.isUserAndPasswordCorrect(userName, password)) {
            // ToDo: Нужно зафиксировать для этого webSocket имя пользователя (и потом другие параметры авторизации).
            sendLoginAnswer(webSocket, userName);
        } else {
            sendLogoutAnswer(webSocket);
        }
    }

    private void sendLogoutAnswer(WebSocket webSocket) {
        send(webSocket, new TransportPackageOfServer(LOGOUT));
    }

    private void sendLoginAnswer(WebSocket webSocket, String userName) {
        send(webSocket, new TransportPackageOfServer(
                LOGIN,
                Map.of("userName", userName)
        ));
    }

    protected void onForgetGameTypeSet(WebSocket webSocket) {
        System.out.println("onForgetGameTypeSet");
        send(webSocket, new TransportPackageOfServer(FORGET_GAME_TYPE_SET));
    }

    protected void onGetGameTypeSet(WebSocket webSocket) {
        System.out.println("onGetGameTypeSet");
        send(webSocket, new TransportPackageOfServer(
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

    protected void onForgetGameType(WebSocket webSocket) {
        System.out.println("onForgetGameType");

        System.out.println("modelOfServer = " + modelOfServer);
        modelOfServer = null;
        System.out.println("modelOfServer = " + modelOfServer);
        send(webSocket, new TransportPackageOfServer(FORGET_GAME_TYPE));
    }

    protected void onSelectGameType(WebSocket webSocket, TransportPackageOfClient transportPackageOfClient) {
        System.out.println("onSelectGameType");

        // System.out.println("transportPackageOfClient = " + transportPackageOfClient);
        String model = (String) transportPackageOfClient.get("gameType");
        // ToDo: Проверить, что model одна из списка возможных моделей, которые были отправлены ранее этому клиенту.
        //       И если это не так, то отправить клиенту FORGET_GAME_TYPE.

        {   // Создать новую модель
            System.out.println("modelOfServer = " + modelOfServer);
            // ToDo: Вместо вызова конкретного конструктора, тут нужно создавать экземпляр того типа, который был выбран клиентом.
            modelOfServer = new ModelOfServerOfSokoban<>(this);
            System.out.println("modelOfServer = " + modelOfServer);
        }

        send(webSocket, new TransportPackageOfServer(
                SELECT_GAME_TYPE,
                Map.of("gameType", model)
        ));
    }

    protected void onAddView(WebSocket webSocket, TransportPackageOfClient transportPackageOfClient) {
        System.out.println("onAddView");

        String viewId = (String) transportPackageOfClient.get("viewId");
        // System.out.println("viewId = " + viewId);
        modelOfServer.addRemoteView(new RemoteView<>(webSocket, viewId));

        send(webSocket, new TransportPackageOfServer(
                ADD_VIEW,
                Map.of("viewId", viewId)
        ));
    }

    protected void onCreateNewGame(WebSocket webSocket, TransportPackageOfClient transportPackageOfClient) {
        System.out.println("onCreateNewGame");

        modelOfServer.createNewGame();
    }
}