package timmax.tilegame.websocket.server;

import java.io.StringWriter;

import org.java_websocket.WebSocket;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.TransportPackageOfServer;
import timmax.tilegame.basemodel.protocol.server.ModelOfServer;
import timmax.tilegame.basemodel.protocol.server.RemoteView;
import timmax.tilegame.transport.TransportOfServer;

// ToDo: удалить класс.

// Этот класс пока используется в первом варианте конкретных клиентов (Сапёр и Сокобан).
// ToDo: От класса можно будет вообще отказаться и воспользоваться классом MultiGameWebSocketServer,
//       который должен будет реализовать интерфейс TransportOfModel (с методом sendGameEvent с двумя параметрами).
public class TransportOfServerWebSocket implements TransportOfServer<WebSocket> {
    private final WebSocket webSocket;

    public TransportOfServerWebSocket(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    @Override
    public void sendGameEvent(GameEvent gameEvent) {
        StringWriter writer = new StringWriter();
        // ObjectMapper mapper = new ObjectMapper();
        try {
            // mapper.writeValue(writer, gameEvent);
            System.out.println("TransportOfModelWebSocket");
            System.out.println("public void sendGameEvent( GameEvent gameEvent)");
            System.out.println("-----");
            System.out.println("writer = " + writer);
            System.out.println("-----");
            webSocket.send(writer.toString());
        } /*catch (IOException e) {
            throw new RuntimeException(e);
        }*/ catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendGameEvent(RemoteView<WebSocket> remoteView, GameEvent gameEvent) {
    }

    @Override
    public ModelOfServer<WebSocket> getModelOfServer() {
        return null;
    }
/*
    @Override
    public void setModelOfServer(ModelOfServer<WebSocket> modelOfServer) {
        // this.modelOfServer = modelOfServer;
    }
*/
    @Override
    public void setModelOfServerTmp() {

    }

    @Override
    public void send(WebSocket clientId, TransportPackageOfServer<WebSocket> transportPackageOfServer) {

    }
}