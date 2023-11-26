package timmax.tilegame.websocket.server;

import java.io.IOException;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import timmax.tilegame.basemodel.credential.Credentials;
import timmax.tilegame.basemodel.protocol.*;

import timmax.tilegame.game.minesweeper.model.MinesweeperModel;
import timmax.tilegame.game.sokoban.model.SokobanModel;

import static java.util.stream.Collectors.toList;
import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public class MultiGameWebSocketServer extends WebSocketServer /*implements TransportOfModel*/ {
    private final ObjectMapper mapper = new ObjectMapper();


    public MultiGameWebSocketServer(int port) {
        super(new InetSocketAddress(port));
    }

    /*
        @Override
        public void sendGameEvent(Set<RemoteView> setOfRemoteViews, GameEvent gameEvent) {
            StringWriter writer = new StringWriter();
            try {
                mapper.writeValue(writer, gameEvent);

                for (RemoteView remoteView : setOfRemoteViews) {

                }

                webSocket.send(writer.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    */
    private void sendRequest(WebSocket webSocket, TransportPackageOfServer transportPackageOfServer) {
        try {
            StringWriter writer = new StringWriter();
            mapper.writeValue(writer, transportPackageOfServer);
/*
            System.out.println("writer. Begin");
            System.out.println(writer);
            System.out.println("writer. End");
*/
            webSocket.send(writer.toString());
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
        System.out.println(webSocket + ".");
        ex.printStackTrace();
        System.err.println("----------");
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        System.out.println("onMessage");
        System.out.println(webSocket);
/*
        System.out.println("--- begin of message ---");
        System.out.println(message);
        System.out.println("--- end of message ---");
*/
        try {
            TransportPackageOfClient transportPackageOfClient = mapper.readValue(message, TransportPackageOfClient.class);
            TypeOfTransportPackage typeOfTransportPackage = transportPackageOfClient.getTypeOfTransportPackage();
            if (typeOfTransportPackage == LOGOUT) {
                onLogout(webSocket);
            } else if (typeOfTransportPackage == LOGIN) {
                onLogin(webSocket, transportPackageOfClient);
            } else if (typeOfTransportPackage == FORGET_GAME_TYPE_SET) {
                onForgetGameTypeSet(webSocket);
            } else if (typeOfTransportPackage == GET_GAME_TYPE_SET) {
                onGetGameTypeSet(webSocket);
            } else if (typeOfTransportPackage == FORGET_GAME_TYPE) {
                onForgetGameType(webSocket);
            } else if (typeOfTransportPackage == SELECT_GAME_TYPE) {
                onSelectGameType(webSocket, transportPackageOfClient);
            } else {
                System.err.println("Server doesn't know received typeOfTransportPackage.");
                System.err.println("typeOfTransportPackage = " + typeOfTransportPackage);
                System.exit(1);
            }
        } catch (JsonProcessingException jpe) {
            // От клиента поступило что-то, что не понятно серверу.
            // Можно:
            // 1. Либо отключить такого клиента.
            // 2. Совсем упасть серверу.

            // throw Должно было привести к полному падению. Но так не получается, из-за того, что onMessage (да и
            // другие методы onXxx) вызывается не в основном потоке-нити, а в дочернем.
            // throw new RuntimeException(jpe);

            // Тогда будем падать так:
            jpe.printStackTrace();
            System.exit(1);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
        System.out.println("----------");
    }

    protected void onLogout(WebSocket webSocket) {
        System.out.println("onLogout");

        logoutAnswer(webSocket);
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
            loginAnswer(webSocket, userName);
        } else {
            logoutAnswer(webSocket);
        }
    }

    private void logoutAnswer(WebSocket webSocket) {
        TransportPackageOfServer transportPackageOfServer = null;
        try {
            transportPackageOfServer = new TransportPackageOfServer(LOGOUT);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
        sendRequest(webSocket, transportPackageOfServer);
    }

    private void loginAnswer(WebSocket webSocket, String userName) {
        TransportPackageOfServer transportPackageOfServer = null;
        try {
            transportPackageOfServer = new TransportPackageOfServer(
                    LOGIN,
                    Map.of("userName", userName)
            );
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
        sendRequest(webSocket, transportPackageOfServer);
    }

    protected void onForgetGameTypeSet(WebSocket webSocket) {
        System.out.println("onForgetGameTypeSet");

        TransportPackageOfServer transportPackageOfServer = null;
        try {
            transportPackageOfServer = new TransportPackageOfServer(FORGET_GAME_TYPE_SET);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
        sendRequest(webSocket, transportPackageOfServer);
    }

    protected void onGetGameTypeSet(WebSocket webSocket) {
        System.out.println("onGetGameTypeSet");

        TransportPackageOfServer transportPackageOfServer = null;
        try {
            transportPackageOfServer = new TransportPackageOfServer(
                    GET_GAME_TYPE_SET,
                    Map.of("gameTypeSet",
                            Stream.of(
                                    // ToDo: Перечень классов вариантов игр следует делать не в коде. Варианты:
                                    //       - файл параметров,
                                    //       - классы, хранящиеся по определённому пути.
                                    MinesweeperModel.class,
                                    SokobanModel.class
                            ).collect(toList())
                    )
            );
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
        sendRequest(webSocket, transportPackageOfServer);
    }

    protected void onForgetGameType(WebSocket webSocket) {
        System.out.println("onForgetGameType");

        TransportPackageOfServer transportPackageOfServer = null;
        try {
            transportPackageOfServer = new TransportPackageOfServer(FORGET_GAME_TYPE);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
        sendRequest(webSocket, transportPackageOfServer);
    }

    protected void onSelectGameType(WebSocket webSocket, TransportPackageOfClient transportPackageOfClient) {
        System.out.println("onSelectGameType");

        // System.out.println("transportPackageOfClient = " + transportPackageOfClient);
        String model = (String) transportPackageOfClient.get("gameType");
        // ToDo: Проверить, что model одна из списка возможных моделей, которые были отправлены ранее этому клиенту.

        TransportPackageOfServer transportPackageOfServer = null;
        try {
            transportPackageOfServer = new TransportPackageOfServer(
                    SELECT_GAME_TYPE,
                    Map.of("gameType", model)
            );
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
        sendRequest(webSocket, transportPackageOfServer);
    }
}