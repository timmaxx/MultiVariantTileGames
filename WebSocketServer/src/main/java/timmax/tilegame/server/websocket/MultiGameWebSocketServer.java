package timmax.tilegame.server.websocket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Objects;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server.ModelOfServer;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server.RemoteView;
import timmax.tilegame.transport.TransportOfServer;

public class MultiGameWebSocketServer extends WebSocketServer implements TransportOfServer<WebSocket> {
    private final ObjectMapperOfMvtg mapper = new ObjectMapperOfMvtg();
    private ModelOfServerLoader modelLoader;
    private Collection<ModelOfServerDescriptor> collectionOfModelOfServerDescriptor;

    private ModelOfServer<WebSocket> modelOfServer;

    public MultiGameWebSocketServer(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void sendGameEvent(GameEvent gameEvent) {
        throw new RuntimeException("Не использовать этот метод. Потом удалить его из классов и интерфейсов!");
    }

    @Override
    public void sendGameEvent(RemoteView<WebSocket> remoteView, GameEvent gameEvent) {
        EventOfServer eventOfServer = new EventOfServer92GameEvent(remoteView.getViewId(), gameEvent);
        send(remoteView.getClientId(), eventOfServer);
    }

    @Override
    public void send(WebSocket clientId, EventOfServer eventOfServer) {
        System.out.println("  send(WebSocket, EventOfServer<WebSocket>)");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.out.println("    eventOfServer = " + eventOfServer);
        mapper.writeValue(byteArrayOutputStream, eventOfServer);
        clientId.send(byteArrayOutputStream.toByteArray());
        System.out.println("---------- End of send(WebSocket, EventOfServer<WebSocket>)");
    }

    @Override
    public ModelOfServer<WebSocket> getModelOfServer() {
        return modelOfServer;
    }

    @Override
    public void setModelOfServer(ModelOfServer<WebSocket> modelOfServer) {
        System.out.println("class MultiGameWebSocketServer. method setModelOfServer");
        System.out.println("  modelOfServer = " + modelOfServer);
        this.modelOfServer = modelOfServer;
    }

    @Override
    public Collection<ModelOfServerDescriptor> getCollectionOfModelOfServerDescriptor() {
        return collectionOfModelOfServerDescriptor;
    }

    @Override
    public void onStart() {
        System.out.println("onStart()");
        System.out.println("  MultiGameWebSocketServer started on port: " + getPort() + ".");

        // ToDo: Загрузить перечень классов-моделей. Они должны быть:
        //      +1. наследниками ModelOfServer.
        //      +2. иметь конструктор с параметром типа TransportOfServer.
        //      +3. попробовать создать экземпляр каждого из класса и вытащить из него некоторые свойства-описания,
        //          необходимые для информирования клиента (в т.ч. классифицирующая информация), например:
        //       3.1. Наименование игры (для UI).
        //       3.2. Количество игроков.
        //      +4. удалить экземляры.
        //  + Часть логики при этом вынести из EventOfClient31GameTypeSelect

        try {
            // modelLoader = new ModelOfServerLoader(Paths.get(MultiGameWebSocketServer.class.getResource("models.txt").toURI()));
            modelLoader = new ModelOfServerLoader(Paths.get(Objects.requireNonNull(MultiGameWebSocketServer.class.getResource("models.txt")).toURI()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.exit(1);
        }
        collectionOfModelOfServerDescriptor = modelLoader.getCollectionOfModelOfServerDescriptor();

        System.out.println("---------- End of onStart");
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        System.out.println("onClose(WebSocket, int, String, boolean)");
        System.out.println("  " + webSocket);
        System.out.println("  Connect was closed.");
        System.out.println("  Code = " + code + ". Reason = " + reason + ". Remote = " + remote + ".");
        System.out.println("---------- End of onClose");
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        System.out.println("onOpen(WebSocket, ClientHandshake)");
        System.out.println("  " + webSocket);
        // ToDo: Для каждого соединения можно создавать отдельный поток-нить.
        //       Соответственно, нужна карта, в которой будет храниться:
        //       webSocket, нить, модель игры.
        //       Но, вполне возможно, что это и так уже делается ядром WinSocket...
        System.out.println("---------- End of onOpen");
    }

    @Override
    public void onError(WebSocket webSocket, Exception ex) {
        System.err.println("onError(WebSocket, Exception)");
        System.err.println("  " + webSocket);
        ex.printStackTrace();
        System.err.println("---------- End of onError");
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        System.out.println("onMessage(WebSocket, ByteBuffer)");
        System.out.println("  " + webSocket);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        EventOfClient eventOfClient = mapper.readValue(byteArrayInputStream, EventOfClient.class);

        System.out.println("  eventOfClient = " + eventOfClient);
        System.out.println("---------- End of onMessage(WebSocket, ByteBuffer)");

        Thread thread = new Thread(() -> eventOfClient.executeOnServer(this, webSocket));
        thread.start();
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        System.err.println("onMessage(WebSocket, String)");
        System.err.println("  " + webSocket);
        System.err.println("  This type of message (String) should not be!");
        System.exit(1);
    }
}
