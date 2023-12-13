package timmax.tilegame.websocket.client;

import org.java_websocket.client.WebSocketClient;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.protocol.ClientState;
import timmax.tilegame.basemodel.protocol.HashSetOfObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.TransportPackageOfClient;
import timmax.tilegame.transport.TransportOfClient;

import java.io.StringWriter;

// ToDo: удалить класс.
public class TransportOfClientWebSocket implements TransportOfClient {
    private final WebSocketClient webSocketClient;

    public TransportOfClientWebSocket(WebSocketClient webSocketClient) {
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

    @Override
    public void send(TransportPackageOfClient transportPackageOfClient) {
    }

    @Override
    public ClientState<Object> getClientState() {
        return null;
    }

    @Override
    public HashSetOfObserverOnAbstractEvent getHashSetOfObserverOnAbstractEvent() {
        return null;
    }
}