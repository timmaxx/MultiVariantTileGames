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
import timmax.tilegame.basemodel.credential.ResultOfCredential;
import timmax.tilegame.basemodel.protocol.*;

import timmax.tilegame.game.minesweeper.model.MinesweeperModel;
import timmax.tilegame.game.sokoban.model.SokobanModel;

import static java.util.stream.Collectors.toList;
import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public class MultiGameWebSocketServer extends WebSocketServer {
    private final ObjectMapper mapper = new ObjectMapper();


    public MultiGameWebSocketServer(int port) {
        super(new InetSocketAddress(port));
    }
/*
    public void infoLogin( WebSocket webSocket) {
        sendRequest( webSocket, new TransportPackageOfServer( TypeOfTransportPackageOfServer.INFO_LOGIN));
    }

    public void infoLogout( WebSocket webSocket) {
        sendRequest( webSocket, new TransportPackageOfServer( TypeOfTransportPackageOfServer.INFO_LOGOUT));
    }
*/
/*
    public void infoGameTypeMap( WebSocket webSocket, Map< ServerBaseModel, String> mapOfServerBaseModel_String) {
        Map< String, Object> mapOfParamName_Value = new HashMap< >( );
        mapOfParamName_Value.put( "gameTypeMap", mapOfServerBaseModel_String);
        sendRequest( webSocket, new TransportPackageOfServer( TypeOfTransportPackageOfServer.INFO_GAME_TYPE_MAP, mapOfParamName_Value));
    }

    public void infoSelectGameType( WebSocket webSocket, Class serverBaseModelClass) {
        // Сервер ознакомился с желанием клиента выбирал для него тип игры.
        Map< String, Object> mapOfParamName_Value = new HashMap< >( );
        mapOfParamName_Value.put( "gameType", serverBaseModelClass);
        sendRequest( webSocket, new TransportPackageOfServer( TypeOfTransportPackageOfServer.INFO_SELECT_GAME_TYPE, mapOfParamName_Value));
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
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        System.out.println("onClose");
        System.out.println(webSocket + ".");
        System.out.println("Connect was closed.");
        System.out.println("Code = " + code + ". Reason = " + reason + ". Remote = " + remote + ".");
        System.out.println("----------");
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        System.out.println("onMessage");
        System.out.println(webSocket + ".");
/*
        System.out.println("--- begin of message ---");
        System.out.println(message);
        System.out.println("--- end of message ---");
*/
        try {
            TransportPackageOfClient transportPackageOfClient = mapper.readValue(message, TransportPackageOfClient.class);
            TypeOfTransportPackage typeOfTransportPackage = transportPackageOfClient.getTypeOfTransportPackage();
            if (typeOfTransportPackage == LOGIN) {
                onLogin(webSocket, transportPackageOfClient);
            } else if (typeOfTransportPackage == LOGOUT) {
                onLogout(webSocket);
            } else if (typeOfTransportPackage == GET_GAME_TYPE_SET) {
                onGetGameTypeSet(webSocket);
            } else if ( typeOfTransportPackage == SELECT_GAME_TYPE) {
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
            // другие методы) вызывается не в основном потоке-нити, а в дочернем.
            // throw new RuntimeException(jpe);

            // Тогда будем падать так:
            jpe.printStackTrace();
            System.exit(1);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception ex) {
        System.err.println("onError");
        System.out.println(webSocket + ".");
        ex.printStackTrace();
        System.err.println("----------");
    }

    @Override
    public void onStart() {
        System.out.println("MultiGameWebSocketServer started on port: " + getPort() + ".");
        System.err.println("----------");
    }

    protected void onLogin(WebSocket webSocket, TransportPackageOfClient transportPackageOfClient) {
        System.out.println("onLogin");
        Map<String, Object> mapOfParamName_Value = transportPackageOfClient.getMapOfParamName_Value();
        String userName = ((String) mapOfParamName_Value.get("userName"));
        String password = ((String) mapOfParamName_Value.get("password"));
        System.out.println("userName = " + userName + " | " + "password = *"); // Пароль не выводим:

        // Имя пользователя и пароль извлечены. Теперь можно в отдельном потоке (вероятно в том, который был
        // создан при onOpen) сверить входящие имя пользователя и пароль с теми, которые есть на сервере.
        // И результат отправить клиенту.
        // Но сейчас (пока с отдельными нитями не начали реализовывать) запросим в текущей нити.
        ResultOfCredential resultOfCredential = Credentials.verifyUserAndPassword(userName, password);
        if (resultOfCredential == ResultOfCredential.NOT_AUTHORISED) {
            userName = "";
            System.out.println("Не успешная идентификацию и/или аутентификацию и/или авторизация.");
        } else if (resultOfCredential == ResultOfCredential.AUTHORISED) {
            System.out.println("Успешная идентификация, аутентификация и авторизация.");
            // ToDo: Нужно зафиксировать для этого webSocket имя пользователя (и потом другие параметры авторизации).
        }
        String finalUserName = userName;

        TransportPackageOfServer transportPackageOfServer = null;
        try {
            transportPackageOfServer = new TransportPackageOfServer(
                    LOGIN,
                    Map.of("resultOfCredential", resultOfCredential,
                            "userName", finalUserName
                    )
            );
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }

        sendRequest(webSocket, transportPackageOfServer);
        System.out.println("----------");
    }

    protected void onLogout(WebSocket webSocket) {
        System.out.println("onLogout");

        TransportPackageOfServer transportPackageOfServer = null;
        try {
            transportPackageOfServer = new TransportPackageOfServer(LOGOUT);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
        sendRequest(webSocket, transportPackageOfServer);
        System.out.println("----------");
    }

    protected void onGetGameTypeSet(WebSocket webSocket) {
        System.out.println("onGetGameTypeSet");

        TransportPackageOfServer transportPackageOfServer = null;
        try {
            transportPackageOfServer = new TransportPackageOfServer(
                    GET_GAME_TYPE_SET,
                    Map.of("gameTypeSet",
                            Stream.of(
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
        System.out.println("----------");
    }

    protected void onSelectGameType(WebSocket webSocket, TransportPackageOfClient transportPackageOfClient) {
        System.out.println("onSelectGameType");

        // System.out.println("transportPackageOfClient = " + transportPackageOfClient);
        String model = (String)transportPackageOfClient.getMapOfParamName_Value().get("gameType");
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
        System.out.println("----------");
    }
}