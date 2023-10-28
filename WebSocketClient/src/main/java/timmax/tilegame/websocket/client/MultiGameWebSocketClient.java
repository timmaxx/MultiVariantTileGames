package timmax.tilegame.websocket.client;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import timmax.tilegame.basemodel.credential.ResultOfCredential;
import timmax.tilegame.basemodel.protocol.*;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackageOfServer.*;

public class MultiGameWebSocketClient extends WebSocketClient {
    private final StringWriter writer = new StringWriter( );
    private final ObjectMapper mapper = new ObjectMapper( );

    private final Map< MultiGameWebSocketClientObserverOnOpen, String> mapOfMultiGameWebSocketClientObserver_String__OnOpen = new HashMap< >( );
    private final Map< MultiGameWebSocketClientObserverOnClose, String> mapOfMultiGameWebSocketClientObserver_String__OnClose = new HashMap< >( );
    private final Map< MultiGameWebSocketClientObserverOnLogin, String> mapOfMultiGameWebSocketClientObserver_String__OnLogin = new HashMap< >( );
/*
    private final Map< MultiGameWebSocketClientObserverOnLogout, String> mapOfMultiGameWebSocketClientObserver_String__OnLogout = new HashMap< >( );
    private final Map< MultiGameWebSocketClientObserverOnSelectGameType, String> mapOfMultiGameWebSocketClientObserver_String__OnSelectGameType = new HashMap< >( );
    private final Map< MultiGameWebSocketClientObserverOnGameTypeMap, String> mapOfMultiGameWebSocketClientObserver_String__OnGameTypeMap = new HashMap< >( );
*/


    public void addViewOnOpen( MultiGameWebSocketClientObserverOnOpen multiGameWebSocketClientObserverOnOpen) {
        mapOfMultiGameWebSocketClientObserver_String__OnOpen.put( multiGameWebSocketClientObserverOnOpen, "");
    }

    public void addViewOnClose( MultiGameWebSocketClientObserverOnClose multiGameWebSocketClientObserverOnClose) {
        mapOfMultiGameWebSocketClientObserver_String__OnClose.put( multiGameWebSocketClientObserverOnClose, "");
    }

    public void addViewOnLogin( MultiGameWebSocketClientObserverOnLogin multiGameWebSocketClientObserverOnLogin) {
        mapOfMultiGameWebSocketClientObserver_String__OnLogin.put( multiGameWebSocketClientObserverOnLogin, "");
    }
/*
    public void addViewOnLogout( MultiGameWebSocketClientObserverOnLogout multiGameWebSocketClientObserverOnLogout) {
        mapOfMultiGameWebSocketClientObserver_String__OnLogout.put( multiGameWebSocketClientObserverOnLogout, "");
    }

    public void addViewOnSelectGameType( MultiGameWebSocketClientObserverOnSelectGameType multiGameWebSocketClientObserverOnSelectGameType) {
        mapOfMultiGameWebSocketClientObserver_String__OnSelectGameType.put( multiGameWebSocketClientObserverOnSelectGameType, "");
    }

    public void addViewOnGameTypeMap( MultiGameWebSocketClientObserverOnGameTypeMap multiGameWebSocketClientObserverOnGameTypeMap) {
        mapOfMultiGameWebSocketClientObserver_String__OnGameTypeMap.put( multiGameWebSocketClientObserverOnGameTypeMap, "");
    }
*/
    public MultiGameWebSocketClient( URI serverUri) {
        super( serverUri);
        System.out.println( serverUri);
    }

// 2
    public void login( String userName, String password) {
        Map< String, Object> mapOfParamName_Value = new HashMap< >( );
        mapOfParamName_Value.put( "userName", userName);
        mapOfParamName_Value.put( "password", password);
        sendRequest( new TransportPackageOfClient( TypeOfTransportPackageOfClient.REQ_LOGIN, mapOfParamName_Value));
    }

    public void logout( ) {
        sendRequest( new TransportPackageOfClient( TypeOfTransportPackageOfClient.REQ_LOGOUT));
    }

/*
// 3
    public void getGameTypeMap( ) {
        sendRequest( new TransportPackageOfClient( TypeOfTransportPackageOfClient.REQ_GAME_TYPE_MAP));
    }

    public void selectGameType( Class serverBaseModelClass) {
        Map< String, Object> mapOfParamName_Value = new HashMap<>();
        mapOfParamName_Value.put( "gameType", serverBaseModelClass);
        sendRequest( new TransportPackageOfClient( TypeOfTransportPackageOfClient.REQ_SELECT_GAME_TYPE, mapOfParamName_Value));
    }
*/

/*
// 4
    public void createGameSeries( ) {
        sendRequest( new TransportPackageOfClient( TypeOfClientTransportPackage.REQ_CREATE_GAME_SERIES));
    }

    public void getGameSeriesMap( ) {
        sendRequest( new TransportPackageOfClient( TypeOfClientTransportPackage.REQ_GAME_SERIES_MAP));
    }

    public void selectGameSeries() {
        sendRequest( new TransportPackageOfClient( TypeOfClientTransportPackage.REQ_SELECT_GAME_SERIES));
    }

// 5
    public void getGameMatchMap() {
        sendRequest( new TransportPackageOfClient( TypeOfClientTransportPackage.REQ_GAME_MATCH_MAP));
    }

    public void selectSelectGameMatch() {
        sendRequest( new TransportPackageOfClient( TypeOfClientTransportPackage.REQ_SELECT_GAME_MATCH));
    }

// 6
    public void getPlayerSideMap() {
        sendRequest( new TransportPackageOfClient( TypeOfClientTransportPackage.REQ_PLAYER_SIDE_MAP));
    }

    public void selectPlayerSide() {
        sendRequest( new TransportPackageOfClient( TypeOfClientTransportPackage.REQ_SELECT_PLAYER_SIDE));
    }

// 7
    public void declareReadiness() {
        sendRequest( new TransportPackageOfClient( TypeOfClientTransportPackage.REQ_DECLARE_READINESS));
    }

    public void getReadinessMap() {
        sendRequest( new TransportPackageOfClient( TypeOfClientTransportPackage.REQ_READINESS_MAP));
    }
*/
/*
    public void a() {
        sendRequest( new MultiGameRequest());
    }
*/


    private void sendRequest( TransportPackageOfClient transportPackageOfClient) {
        try {
            mapper.writeValue( writer, transportPackageOfClient);
            send( writer.toString( ));
        } catch ( IOException e) {
            throw new RuntimeException( e);
        }
    }

    @Override
    public void onOpen( ServerHandshake handshakedata) {
        System.out.println( "onOpen");
        for ( MultiGameWebSocketClientObserverOnOpen multiGameWebSocketClientObserverOnOpen: mapOfMultiGameWebSocketClientObserver_String__OnOpen.keySet()) {
            multiGameWebSocketClientObserverOnOpen.updateOnOpen( handshakedata);
        }
    }

    @Override
    public void onMessage( String message) {
        System.out.println( "onMessage");
        try {
            TransportPackageOfServer transportPackageOfServer = mapper.readValue( message, TransportPackageOfServer.class);
            TypeOfTransportPackageOfServer typeOfTransportPackageOfServer = transportPackageOfServer.getInOutPackType( );
            if ( typeOfTransportPackageOfServer == INFO_LOGIN) {
                onLogin( transportPackageOfServer);
            }/* else if ( typeOfTransportPackageOfServer == INFO_LOGOUT) {
                onLogout( transportPackageOfServer);
            }*/ /*else if ( typeOfTransportPackageOfServer == INFO_GAME_TYPE_MAP) {
                System.out.println( "Сервер говорит о результате запроса перечня типов игр.");
                // Читаем и передаём карту клиенту - пусть из неё выбирает себе тип игры.
                Map< String, Object> mapOfParamName_Value = transportPackageOfServer.getMapOfParamName_Value( );
                Map< Class, String> map = ( ( Map< Class, String>)mapOfParamName_Value.get( "gameTypeMap"));
                for ( Class clazz: map.keySet()) {
                    System.out.println( clazz);
                }
            } else if ( typeOfTransportPackageOfServer == INFO_SELECT_GAME_TYPE) {
                System.out.println( "Сервер рассмотрел желание клиента о выборе типа игры и сообщает ему о выбранном варианте.");
                Map< String, Object> mapOfParamName_Value = transportPackageOfServer.getMapOfParamName_Value( );
                ServerBaseModel serverBaseModel = ( ( ServerBaseModel)mapOfParamName_Value.get( "gameType"));
                System.out.println( serverBaseModel);
            }*/
        } catch ( JsonProcessingException jpe) {
            throw new RuntimeException( jpe);
        }
    }

    @Override
    public void onClose( int code, String reason, boolean remote) {
        System.out.println( "onClose");
        for ( MultiGameWebSocketClientObserverOnClose multiGameWebSocketClientObserverOnClose: mapOfMultiGameWebSocketClientObserver_String__OnClose.keySet()) {
            multiGameWebSocketClientObserverOnClose.updateOnClose( );
        }
    }

    @Override
    public void onError( Exception ex) {
        System.out.println( "onError");
    }

    protected void onLogin( TransportPackageOfServer transportPackageOfServer) {
        // Map< String, ResultOfCredential> m = (Map< String, ResultOfCredential>)(transportPackageOfServer.getMapOfParamName_Value());
        ResultOfCredential resultOfCredential = ResultOfCredential.valueOf( ( String)( transportPackageOfServer.getMapOfParamName_Value( ).get( "resultOfCredential")));
        if ( resultOfCredential == ResultOfCredential.NOT_AUTHORISED) {
            System.out.println( "Сервер сообщил о не успешных идентификации и/или аутентификации и/или авторизации.");
        } else if ( resultOfCredential == ResultOfCredential.AUTHORISED) {
            System.out.println( "Сервер сообщил об успешных идентификации, аутентификации и авторизации.");
        }

        for ( MultiGameWebSocketClientObserverOnLogin multiGameWebSocketClientObserverOnLogin: mapOfMultiGameWebSocketClientObserver_String__OnLogin.keySet()) {
            multiGameWebSocketClientObserverOnLogin.updateOnLogin( resultOfCredential);
        }
    }
/*
    protected void onLogout( TransportPackageOfServer transportPackageOfServer) {
        System.out.println( "Сервер говорит о результате разлогирования.");
    }
*/
}