package timmax.tilegame.websocket.server;

import java.io.IOException;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import timmax.tilegame.basemodel.credential.Credentials;
import timmax.tilegame.basemodel.credential.ResultOfCredential;
import timmax.tilegame.basemodel.protocol.*;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.LOGIN;
import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.LOGOUT;

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

/*
    public void infoCreateGameSeries() {}
    public void infoGameSeriesMap() {}
    public void infoGameMatchMap() {}
    public void infoSelectGameMatch() {}
    public void infoPlayerSideMap() {}
    public void infoSelectPlayerSide() {}
    public void infoDeclareReadiness() {}
    public void infoReadinessMap() {}
    public void infoStartGameMatch() {}
*/

    private void sendRequest(WebSocket webSocket, TransportPackageOfServer transportPackageOfServer) {
        try {
            StringWriter writer = new StringWriter();
            // System.out.println("sendRequest. After 'StringWriter writer = new StringWriter();'");
            mapper.writeValue(writer, transportPackageOfServer);
            // System.out.println("sendRequest. After 'mapper.writeValue(writer, transportPackageOfServer);'");
            // System.out.println("writer. Begin");
            // System.out.println(writer);
            // System.out.println("writer. End");
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
        System.out.println("Connect from '" + webSocket + "' are opened.");
        // ToDo: Для каждого соединения можно создавать отдельный поток-нить.
        //       Соответственно, нужна карта, в которой будет храниться:
        //       webSocket, нить, модель игры.
        //       Но, вполне возможно, что это и так уже делается ядром WinSocket...
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        System.out.println("Connect from '" + webSocket + "' was closed.");
        System.out.println("Code = " + code + ". Reason = " + reason + ". Remote = " + remote + ".");
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        // System.out.println("onMessage");
        // System.out.println("message = " + message);
        try {
            TransportPackageOfClient transportPackageOfClient = mapper.readValue(message, TransportPackageOfClient.class);
            // System.out.println("transportPackageOfClient = " + transportPackageOfClient);
            TypeOfTransportPackage typeOfTransportPackage = transportPackageOfClient.getTypeOfTransportPackage();
            // System.out.println("typeOfTransportPackage = " + typeOfTransportPackage);
            if (typeOfTransportPackage == LOGIN) {
                onLogin(webSocket, transportPackageOfClient);
            } else if (typeOfTransportPackage == LOGOUT) {
                onLogout(webSocket);
            }/* else if ( typeOfTransportPackageOfClient == REQ_GAME_TYPE_MAP) {
                // Клиент просит дать ему перечень вариантов типов игр.
                System.out.println( "Клиент просит дать ему перечень вариантов типов игр.");
            } else if ( typeOfTransportPackageOfClient == REQ_SELECT_GAME_TYPE) {
                // Клиент хочет выбрать определённый тип игры.
                System.out.println( "Клиент хочет выбрать определённый тип игры.");
                Map< String, Object> mapOfParamName_Value = transportPackageOfClient.getMapOfParamName_Value( );
                ServerBaseModel serverBaseModel = ( ( ServerBaseModel)mapOfParamName_Value.get( "gameType"));
                System.out.println( serverBaseModel);
            }*/ else {
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
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("onError");
        ex.printStackTrace();
        System.err.println("----------");
    }

    @Override
    public void onStart() {
        System.out.println("MultiGameWebSocketServer started on port: " + getPort() + ".");
    }

    protected void onLogin(WebSocket webSocket, TransportPackageOfClient transportPackageOfClient) {
        System.out.println("Клиент хочет пройти идентификацию, аутентификацию, авторизацию.");
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
        }
        System.out.println("Сообщим клиенту об этом.");
        String finalUserName = userName;
        Map<String, Object> mapOfParamName_Value__ForSendToClient = new HashMap<>(){{
            put("resultOfCredential", resultOfCredential);
            put("userName", finalUserName);
        }};

        TransportPackageOfServer transportPackageOfServer = null;
        try {
            // sendRequest( webSocket, new TransportPackageOfServer( TypeOfTransportPackageOfServer.INFO_LOGIN, mapOfParamName_Value__ForSendToClient));
            transportPackageOfServer = new TransportPackageOfServer(LOGIN, mapOfParamName_Value__ForSendToClient);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }

        sendRequest(webSocket, transportPackageOfServer);
        System.out.println("----------");
    }

    protected void onLogout(WebSocket webSocket) {
        System.out.println("Клиент хочет стать анонимным.");

        System.out.println("Сообщим клиенту об этом.");
        TransportPackageOfServer transportPackageOfServer = null;
        try {
            transportPackageOfServer = new TransportPackageOfServer(LOGOUT);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
        // System.out.println("onLogout. After 'TransportPackageOfServer transportPackageOfServer = new TransportPackageOfServer(LOGOUT);'");
        sendRequest(webSocket, transportPackageOfServer);
        System.out.println("----------");
    }
}