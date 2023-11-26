package timmax.tilegame.websocket.server;

import org.java_websocket.WebSocket;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfModel;

import java.io.IOException;
import java.io.StringWriter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransportOfModelWebSocket implements TransportOfModel {
    private final WebSocket webSocket;

    public TransportOfModelWebSocket( WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    @Override
    public void sendGameEvent( GameEvent gameEvent) {
        StringWriter writer = new StringWriter( );
        ObjectMapper mapper = new ObjectMapper( );
        try {
            mapper.writeValue( writer, gameEvent);
            webSocket.send( writer.toString( ));
        } catch ( IOException e) {
            throw new RuntimeException( e);
        }
    }
}

/*
import java.io.IOException;
import java.io.StringWriter;
import java.util.Set;

import org.java_websocket.WebSocket;
import com.fasterxml.jackson.databind.ObjectMapper;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.server.RemoteView;
import timmax.tilegame.transport.TransportOfModel;

public class TransportOfModelWebSocket implements TransportOfModel {
    private final WebSocket webSocket;

    public TransportOfModelWebSocket(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    @Override
    public void sendGameEvent(Set<RemoteView> setOfRemoteViews, GameEvent gameEvent) {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(writer, gameEvent);
            webSocket.send(writer.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
*/
