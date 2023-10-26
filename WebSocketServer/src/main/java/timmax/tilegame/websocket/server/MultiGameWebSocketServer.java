package timmax.tilegame.websocket.server;

import java.net.InetSocketAddress;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.basemodel.protocol.TransportPackageOfClient;
import timmax.tilegame.basemodel.protocol.TypeOfTransportPackageOfClient;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackageOfClient.*;


public class MultiGameWebSocketServer extends WebSocketServer {
    // private final StringWriter writer = new StringWriter( );
    private final ObjectMapper mapper = new ObjectMapper( );
    // private MultiGameInfo lastInfo;


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
/*
    private void sendRequest( WebSocket webSocket, TransportPackageOfServer transportPackageOfServer) {
        try {
            mapper.writeValue( writer, transportPackageOfServer);
            webSocket.send( writer.toString( ));
            // lastInfo = multiGameInfo;
        } catch ( IOException e) {
            throw new RuntimeException( e);
        }
    }
*/
    @Override
    public void onOpen( WebSocket conn, ClientHandshake handshake) {
        System.out.println( "Connect from '" + conn + "' are opened.");
    }

    @Override
    public void onClose( WebSocket conn, int code, String reason, boolean remote) {
        System.out.println( "Connect from '" + conn + "' was closed.");
        System.out.println( "Code = " + code + ". Reason = " + reason + ". Remote = " + remote + ".");
    }

    @Override
    public void onMessage( WebSocket conn, String message) {
        try {
            TransportPackageOfClient transportPackageOfClient = mapper.readValue( message, TransportPackageOfClient.class);
            TypeOfTransportPackageOfClient typeOfTransportPackageOfClient = transportPackageOfClient.getInOutPackType( );
            if ( typeOfTransportPackageOfClient == REQ_LOGIN) {
                // Клиент хочет залогиниться.
                System.out.println( "Клиент хочет залогиниться.");
                Map< String, Object> mapOfParamName_Value = transportPackageOfClient.getMapOfParamName_Value( );
                String userName = ( ( String)mapOfParamName_Value.get( "userName"));
                String password = ( ( String)mapOfParamName_Value.get( "password"));
                System.out.println( "userName = " + userName + " | " + "password = " + password);
            } else if ( typeOfTransportPackageOfClient == REQ_LOGOUT) {
                // Клиент хочет разлогиниться.
                System.out.println( "Клиент хочет разлогиниться.");
            } else if ( typeOfTransportPackageOfClient == REQ_GAME_TYPE_MAP) {
                // Клиент просит дать ему перечень вариантов типов игр.
                System.out.println( "Клиент просит дать ему перечень вариантов типов игр.");
            } else if ( typeOfTransportPackageOfClient == REQ_SELECT_GAME_TYPE) {
                // Клиент хочет выбрать определённый тип игры.
                System.out.println( "Клиент хочет выбрать определённый тип игры.");
                Map< String, Object> mapOfParamName_Value = transportPackageOfClient.getMapOfParamName_Value( );
                ServerBaseModel serverBaseModel = ( ( ServerBaseModel)mapOfParamName_Value.get( "gameType"));
                System.out.println( serverBaseModel);
            }
        } catch ( JsonProcessingException jpe) {
            throw new RuntimeException( jpe);
        }
    }

    @Override
    public void onError( WebSocket conn, Exception ex) {
    }

    @Override
    public void onStart( ) {
        System.out.println( "MultiGameWebSocketServer started on port: " + getPort() + ".");
    }
}