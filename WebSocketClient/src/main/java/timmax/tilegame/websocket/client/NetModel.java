package timmax.tilegame.websocket.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.baseview.View;

public class NetModel extends WebSocketClient implements BaseModel {
    // private MainGameClientStatus mainGameClientStatus = NO_CONNECT;
    private final List<View> viewList;


    public NetModel(URI serverURI) throws InterruptedException {
        super(serverURI);
        connect();
        viewList = new ArrayList<>();
        // Thread.sleep( 1000);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        // System.out.println( "onOpen( serverHandshake = " + serverHandshake + ").");
        // mainGameClientStatus = CONNECT_NON_IDENT;
        // mainGameClientStatus = CONNECT_AUTHORIZED;
    }

    @Override
    public void onMessage(String message) {
        // System.out.println( "onMessage( message = " + message + ").");
        if (message.startsWith("Welcome")) {
            return;
        }
        // Принято игровое сообщение от серверной модели. Нужно:
        // 1. Определить выборку, которой оно предназначено.
        // Пока отправлять будем всем выборкам.

        // 2. Распарсить сообщение.
        // ObjectMapper mapper = new ObjectMapper( );
        try {
            GameEvent gameEvent = null; // mapper.readValue( message, GameEvent.class);
            // System.out.println( gameEvent.getClass().getName());

            // 3. Отправить в выборку. (всем - см. п. 1)
            for (View view : viewList) {
                view.update(gameEvent);
            }
        } /*catch ( JsonProcessingException jpe) {
            throw new RuntimeException( jpe);
        }*/ catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // System.out.println( "onClose( code " + code + ", reason = " + reason + ", remote = " + remote + ").");
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("onError( exception = " + ex + ").");
    }

    @Override
    public void createNewGame() {
        // System.out.println( "CreateNewGame");
        send("CreateNewGame");
    }

    @Override
    public void addView(View view) {
        while (!getConnection().isOpen()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // System.out.println( "addViewListener view = " + view.toString( ));
        send("addViewListener view = " + view.toString());
        viewList.add(view);
    }

    @Override
    public void restart() {
    }

    @Override
    public void nextLevel() {
    }

    @Override
    public void prevLevel() {
    }

    @Override
    public void win() {
    }
}