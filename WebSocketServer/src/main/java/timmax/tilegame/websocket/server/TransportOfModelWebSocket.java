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