package timmax.tilegame.websocket.client;

import org.java_websocket.client.WebSocketClient;
import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.transport.TransportOfController;

//import java.io.IOException;
import java.io.StringWriter;

public class TransportOfControllerWebSocket implements TransportOfController {
    private final WebSocketClient webSocketClient;

    public TransportOfControllerWebSocket(WebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
    }

    @Override
    public void sendCommand(GameCommand gameCommand) {
        StringWriter writer = new StringWriter();
        // ObjectMapper mapper = new ObjectMapper( );
        try {
            // mapper.writeValue( writer, gameCommand);
            webSocketClient.send(writer.toString());
        } /*catch ( IOException e) {
            throw new RuntimeException( e);
        }*/ catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}