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

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackageOfClient.*;

public class MultiGameWebSocketServer extends WebSocketServer {
    private final StringWriter writer = new StringWriter( );
    private final ObjectMapper mapper = new ObjectMapper( );


    public MultiGameWebSocketServer( int port) {
        super( new InetSocketAddress( port));
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

    private void sendRequest( WebSocket webSocket, TransportPackageOfServer transportPackageOfServer) {
        try {
            mapper.writeValue( writer, transportPackageOfServer);
            webSocket.send( writer.toString( ));
        } catch (IOException e) {
            throw new RuntimeException( e);
        }
    }

    @Override
    public void onOpen( WebSocket webSocket, ClientHandshake clientHandshake) {
        System.out.println( "Connect from '" + webSocket + "' are opened.");
        // ToDo: Для каждого соединения можно создавать отдельный поток-нить.
        //       Соответственно, нужна карта, в которой будет храниться:
        //       webSocket, нить, модель игры.
    }

    @Override
    public void onClose( WebSocket webSocket, int code, String reason, boolean remote) {
        System.out.println( "Connect from '" + webSocket + "' was closed.");
        System.out.println( "Code = " + code + ". Reason = " + reason + ". Remote = " + remote + ".");
    }

    @Override
    public void onMessage( WebSocket webSocket, String message) {
        try {
            TransportPackageOfClient transportPackageOfClient = mapper.readValue( message, TransportPackageOfClient.class);
            TypeOfTransportPackageOfClient typeOfTransportPackageOfClient = transportPackageOfClient.getInOutPackType( );
            if ( typeOfTransportPackageOfClient == REQ_LOGIN) {
                onLogin( transportPackageOfClient, webSocket);
            } else if ( typeOfTransportPackageOfClient == REQ_LOGOUT) {
                // Клиент хочет разлогиниться.
                System.out.println( "Клиент хочет разлогиниться.");
            }/* else if ( typeOfTransportPackageOfClient == REQ_GAME_TYPE_MAP) {
                // Клиент просит дать ему перечень вариантов типов игр.
                System.out.println( "Клиент просит дать ему перечень вариантов типов игр.");
            } else if ( typeOfTransportPackageOfClient == REQ_SELECT_GAME_TYPE) {
                // Клиент хочет выбрать определённый тип игры.
                System.out.println( "Клиент хочет выбрать определённый тип игры.");
                Map< String, Object> mapOfParamName_Value = transportPackageOfClient.getMapOfParamName_Value( );
                ServerBaseModel serverBaseModel = ( ( ServerBaseModel)mapOfParamName_Value.get( "gameType"));
                System.out.println( serverBaseModel);
            }*/
        } catch ( JsonProcessingException jpe) {
            throw new RuntimeException( jpe);
        }
    }

    @Override
    public void onError( WebSocket conn, Exception ex) {
    }

    @Override
    public void onStart( ) {
        System.out.println( "MultiGameWebSocketServer started on port: " + getPort( ) + ".");
    }

    protected void onLogin( TransportPackageOfClient transportPackageOfClient, WebSocket webSocket) {
        System.out.println( "Клиент хочет пройти идентификацию, аутентификацию, авторизацию.");
        Map< String, Object> mapOfParamName_Value = transportPackageOfClient.getMapOfParamName_Value( );
        String userName = ( ( String)mapOfParamName_Value.get( "userName"));
        String password = ( ( String)mapOfParamName_Value.get( "password"));
        System.out.println( "userName = " + userName + " | " + "password = *"); // Пароль не выводим:

        // Имя пользователя и пароль извлечены. Теперь можно в отдельном потоке (вероятно в том, который был
        // создан при onOpen) сверить входящие имя пользователя и пароль с теми, которые есть на сервере.
        // И результат отправить клиенту.
        // Но сейчас (пока с отдельными нитями не начали реализовывать) запросим в текущей нити.
        Map< String, Object> mapOfParamName_Value__ForSendToClient = new HashMap< >( );
        ResultOfCredential resultOfCredential = Credentials.verifyUserAndPassword( userName, password);
        if ( resultOfCredential == ResultOfCredential.NOT_AUTHORISED) {
            System.out.println( "Не успешная идентификацию и/или аутентификацию и/или авторизация.");
        } else if ( resultOfCredential == ResultOfCredential.AUTHORISED) {
            System.out.println( "Успешная идентификация, аутентификация и авторизация.");
        }
        System.out.println( "Сообщим клиенту об этом.");
        mapOfParamName_Value__ForSendToClient.put( "resultOfCredential", resultOfCredential);

        // sendRequest( webSocket, new TransportPackageOfServer( TypeOfTransportPackageOfServer.INFO_LOGIN, mapOfParamName_Value__ForSendToClient));
        TransportPackageOfServer transportPackageOfServer = new TransportPackageOfServer( TypeOfTransportPackageOfServer.INFO_LOGIN, mapOfParamName_Value__ForSendToClient);
        sendRequest( webSocket, transportPackageOfServer);
    }
}