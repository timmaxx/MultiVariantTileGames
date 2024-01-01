package timmax.tilegame.websocket.server;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.basemodel.gamecommand.GameCommand;

import timmax.tilegame.transport.TransportOfServer;

import timmax.tilegame.game.sokoban.model.SokobanModel;

public class GameServerWebSocket extends WebSocketServer {
    BidiMap<WebSocket, ServerBaseModel<WebSocket>> webSocketBaseModelBidiMap;
    Map<WebSocket, String> webSocketViewNameMap;

    public GameServerWebSocket(int port) {
        super(new InetSocketAddress(port));
        webSocketBaseModelBidiMap = new DualHashBidiMap<>();
        webSocketViewNameMap = new HashMap<>();
    }

    @Override
    public void onStart() {
        System.out.println("Server started");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(10000);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        webSocket.send("Welcome to the server!"); // This method sends a message to the new client
        System.out.println(
                webSocket.getRemoteSocketAddress()
                        .getAddress()
                        .getHostAddress()
                        + " entered the room!");

        TransportOfServer<WebSocket> transportOfServer = new TransportOfServerWebSocket(webSocket);
        // Создать модель для клиента

        // ServerBaseModel baseModel = new MinesweeperModel( transportOfModel);
        ServerBaseModel<WebSocket> baseModel = new SokobanModel<>(transportOfServer);

        // И добавить её в карту
        webSocketBaseModelBidiMap.put(webSocket, baseModel);
        // System.out.println( "Socked and model were added to two-way map socket - model.");
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        // System.out.println( webSocket + " has left the room!");

        // Удалить из карты сокет - модель
        webSocketBaseModelBidiMap.remove(webSocket);
        // Удалить из карты сокет - выборки
        webSocketViewNameMap.remove(webSocket);
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        // System.out.println( webSocket + ": " + message);

        // Принять команду от клиента
        if (message.startsWith("addViewListener view = ")) {
            String viewName = message.substring(23);
            webSocketViewNameMap.put(webSocket, viewName);
            // System.out.println( "view " + viewName + " was added to map socket - view");
        } else if (message.startsWith("CreateNewGame")) {
            // System.out.println( "CreateNewGame");
            webSocketBaseModelBidiMap.get(webSocket).createNewGame();
        } else {
            //ObjectMapper mapper = new ObjectMapper( );
            try {
                GameCommand gameCommand = null; // mapper.readValue( message, GameCommand.class);
                // System.out.println( gameCommand.getClass().getName());
                {   // Возможно это нужно выполнить в отложенном потоке.
                    // Т.е. после завершения работы onMessage (по аналогии с JFX)
                    // // gameCommand.execute(webSocketBaseModelBidiMap.get(webSocket));
                    // !!! gameCommand.executeOnServer(webSocketBaseModelBidiMap.get(webSocket), webSocket);
                }
            } /*catch ( JsonProcessingException jpe) {
                throw new RuntimeException( jpe);
            }*/ catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        // ???
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteBuffer message) {
        // System.out.println( webSocket + ": " + message);

        // Принять команду от клиента
        // ???
    }

    @Override
    public void onError(WebSocket webSocket, Exception exception) {
        exception.printStackTrace();
        /*
        if (webSocket != null) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
        */

        // Не знаю, что здесь делать...
    }
}
