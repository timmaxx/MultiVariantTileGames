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

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.LOGIN;
import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.LOGOUT;

public class MultiGameWebSocketClient extends WebSocketClient {
    private final ObjectMapper mapper = new ObjectMapper();

    private final Map<Observer011OnOpen, String> mapOfObserver_String__OnOpen = new HashMap<>();
    private final Map<Observer010OnClose, String> mapOfObserver_String__OnClose = new HashMap<>();
    private final Map<Observer021OnLogin, String> mapOfObserver_String__OnLogin = new HashMap<>();
    private final Map<Observer020OnLogout, String> mapOfObserver_String__OnLogout = new HashMap<>();
/*
    private final Map< MultiGameWebSocketClientObserverOnSelectGameType, String> mapOfMultiGameWebSocketClientObserver_String__OnSelectGameType = new HashMap< >( );
    private final Map< MultiGameWebSocketClientObserverOnGameTypeMap, String> mapOfMultiGameWebSocketClientObserver_String__OnGameTypeMap = new HashMap< >( );
*/


    public void addViewOnClose(Observer010OnClose observer010OnClose) {
        mapOfObserver_String__OnClose.put(observer010OnClose, "");
    }

    public void addViewOnOpen(Observer011OnOpen observer011OnOpen) {
        mapOfObserver_String__OnOpen.put(observer011OnOpen, "");
    }

    public void addViewOnLogout(Observer020OnLogout observerOnLogout) {
        mapOfObserver_String__OnLogout.put(observerOnLogout, "");
    }

    public void addViewOnLogin(Observer021OnLogin observer021OnLogin) {
        mapOfObserver_String__OnLogin.put(observer021OnLogin, "");
    }

/*
        public void addViewOnSelectGameType( MultiGameWebSocketClientObserverOnSelectGameType multiGameWebSocketClientObserverOnSelectGameType) {
            mapOfMultiGameWebSocketClientObserver_String__OnSelectGameType.put( multiGameWebSocketClientObserverOnSelectGameType, "");
        }

        public void addViewOnGameTypeMap( MultiGameWebSocketClientObserverOnGameTypeMap multiGameWebSocketClientObserverOnGameTypeMap) {
            mapOfMultiGameWebSocketClientObserver_String__OnGameTypeMap.put( multiGameWebSocketClientObserverOnGameTypeMap, "");
        }
    */

    public MultiGameWebSocketClient(URI serverUri) {
        super(serverUri);
        System.out.println(serverUri);
    }

    // 2
    public void login(String userName, String password) {
        Map<String, Object> mapOfParamName_Value = new HashMap<>();
        mapOfParamName_Value.put("userName", userName);
        mapOfParamName_Value.put("password", password);

        // Здесь и в других методах "оборачиваем" 'new TransportPackageOfClient()' try-ем, т.к. если исключения будут
        // возникать в глубине вызовов, но учитывая, что работа метода идёт не в основном потоке, а в дочернем,
        // JVM просто ничего не сделает с исключениями.
        // Ещё можно было-бы попробовать поработать с setUncaughtExceptionHandler(), если-бы WebSocketClient
        // (да и WebSocketServer) били-бы наследниками Thread. Но это не так.
        try {
            sendRequest(new TransportPackageOfClient(LOGIN, mapOfParamName_Value));
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
    }

    public void logout() {
        try {
        sendRequest(new TransportPackageOfClient(LOGOUT));
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
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

    private void sendRequest(TransportPackageOfClient transportPackageOfClient) {
        try {
            StringWriter writer = new StringWriter();
            mapper.writeValue(writer, transportPackageOfClient);
            // System.out.println("writer = " + writer);
            send(writer.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }/* catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }*/
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("onOpen");
        for (Observer011OnOpen observer011OnOpen : mapOfObserver_String__OnOpen.keySet()) {
            observer011OnOpen.updateOnOpen(handshakedata, this);
        }
    }

    @Override
    public void onMessage(String message) {
        System.out.println("onMessage");
        // System.out.println("message = " + message);
        try {
            TransportPackageOfServer transportPackageOfServer = mapper.readValue(message, TransportPackageOfServer.class);
            TypeOfTransportPackage typeOfTransportPackageOfServer = transportPackageOfServer.getTypeOfTransportPackage();
            if (typeOfTransportPackageOfServer == LOGIN) {
                onLogin(transportPackageOfServer);
            } else if (typeOfTransportPackageOfServer == LOGOUT) {
                onLogout(transportPackageOfServer);
            }/* else if ( typeOfTransportPackageOfServer == INFO_GAME_TYPE_MAP) {
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
        } catch (JsonProcessingException jpe) {
            // От сервера поступило что-то, что не понятно клиенту.
            // Можно:
            // 1. Либо отключиться от сервера.
            // 2. Совсем упасть клиенту.

            // throw Должно было привести к полному падению. Не получается почему-то.
            // throw new RuntimeException(jpe);

            // Тогда будем падать так:
            jpe.printStackTrace();
            System.exit(1);
        } catch (RuntimeException rte) {
            System.out.println("onMessage");
            rte.printStackTrace();
            System.exit(1);
            System.out.println("onMessage. End");
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("onClose");
        for (Observer010OnClose observer010OnClose : mapOfObserver_String__OnClose.keySet()) {
            observer010OnClose.updateOnClose();
        }
        System.out.println("----------");
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("onError");
        ex.printStackTrace();
        System.err.println("----------");
    }

    protected void onLogin(TransportPackageOfServer transportPackageOfServer) {
        ResultOfCredential resultOfCredential = ResultOfCredential.valueOf((String) (transportPackageOfServer.getMapOfParamName_Value().get("resultOfCredential")));
        if (resultOfCredential == ResultOfCredential.NOT_AUTHORISED) {
            System.out.println("Сервер сообщил о не успешных идентификации и/или аутентификации и/или авторизации.");
        } else if (resultOfCredential == ResultOfCredential.AUTHORISED) {
            System.out.println("Сервер сообщил об успешных идентификации, аутентификации и авторизации.");
        }

        for (Observer021OnLogin observer021OnLogin : mapOfObserver_String__OnLogin.keySet()) {
            observer021OnLogin.updateOnLogin(resultOfCredential);
        }
        System.out.println("----------");
    }

    protected void onLogout(TransportPackageOfServer transportPackageOfServer) {
        System.out.println("Сервер говорит о результате разлогирования.");
        for (Observer020OnLogout observer021OnLogout : mapOfObserver_String__OnLogout.keySet()) {
            observer021OnLogout.updateOnLogout();
        }
        System.out.println("----------");
    }
}