package timmax.tilegame.websocket.server;

import org.java_websocket.WebSocket;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.server.RemoteView;
import timmax.tilegame.transport.TransportOfModel;

import java.io.IOException;
import java.io.StringWriter;
import com.fasterxml.jackson.databind.ObjectMapper;

// Этот класс пока используется в первом варианте конкретных клиентов (Сапёр и Сокобан).
// ToDo: От класса можно будет вообще отказаться и воспользоваться классом MultiGameWebSocketServer,
//       который должен будет реализовать интерфейс TransportOfModel (с методом sendGameEvent с двумя параметрами).
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

    @Override
    public void sendGameEvent(RemoteView remoteView, GameEvent gameEvent) {
    }
}