package timmax.tilegame.websocket.client;

import org.java_websocket.client.WebSocketClient;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.protocol.client.LocalClientState;
import timmax.tilegame.basemodel.protocol.HashSetOfObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.EventOfClient;
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
        try {
            webSocketClient.send(writer.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send(EventOfClient transportPackageOfClient) {
    }

    @Override
    public LocalClientState getLocalClientState() {
        return null;
    }

    @Override
    public HashSetOfObserverOnAbstractEvent getHashSetOfObserverOnAbstractEvent() {
        return null;
    }
}
