package timmax.tilegame.server.websocket;

import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timmax.common.ObjectMapperOfMvtg;
import timmax.tilegame.basemodel.credential.User;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.EventOfServer92GameEvent;
import timmax.tilegame.basemodel.protocol.server.MatchPlayerList;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.ISenderOfEventOfServer;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public class SenderOfEventOfServer implements ISenderOfEventOfServer {
    private static final Logger logger = LoggerFactory.getLogger(SenderOfEventOfServer.class);

    //  ToDo:   Две переменные ниже и сам этот класс можно было-бы сделать синглтонами и/или их инициализацию вынести
    //          в другой класс.
    private final ObjectMapperOfMvtg mapper;
    private final Map<WebSocket, RemoteClientStateAutomaton> webSocket_RemoteClientStateAutomaton_Map;

    public SenderOfEventOfServer(Map<WebSocket, RemoteClientStateAutomaton> webSocket_RemoteClientStateAutomaton_Map) {
        this.mapper = new ObjectMapperOfMvtg();
        this.webSocket_RemoteClientStateAutomaton_Map = webSocket_RemoteClientStateAutomaton_Map;
    }

    //  Не стал определять этот метод в interface ISenderOfEventOfServer, поэтому не @Override.
    private void sendEventOfServer(User user, EventOfServer eventOfServer) {
        if (user == null) {
            logger.error("user is null.");
            throw new RuntimeException("user is null.");
        }
        for (var webSocket_RemoteClientStateAutomaton : webSocket_RemoteClientStateAutomaton_Map.entrySet()) {
            if (webSocket_RemoteClientStateAutomaton.getValue().getUser().equals(user)) {
                sendEventOfServer(webSocket_RemoteClientStateAutomaton.getKey(), eventOfServer);
            }
        }
    }

    private void sendEventOfServer(MatchPlayerList matchPlayerList, EventOfServer92GameEvent eventOfServer) {
        sendEventOfServer(matchPlayerList, (EventOfServer) eventOfServer);
    }

    //  interface ISenderOfEventOfServer:
    @Override
    public void sendEventOfServer(WebSocket webSocket, EventOfServer eventOfServer) {
        logger.info("WebSocket: {}. Outcoming message. EventOfServer: {}.", webSocket, eventOfServer);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mapper.writeValue(byteArrayOutputStream, eventOfServer);
        webSocket.send(byteArrayOutputStream.toByteArray());
    }

    @Override
    public void sendEventOfServer(MatchPlayerList matchPlayerList, EventOfServer eventOfServer) {
        if (matchPlayerList == null || matchPlayerList.size() == 0) {
            logger.error("matchPlayerList is null or empty.");
            throw new RuntimeException("matchPlayerList is null or empty.");
        }
        for (int i = 0; i < matchPlayerList.size(); i++) {
            sendEventOfServer(matchPlayerList.get(i), eventOfServer);
        }
    }

    //  ToDo:   Вероятно этот метод нужно вынести за пределы этого класса, потому-что:
    //          1. На вход ему поступает не базовый EventOfServer, а GameEvent - более высокоуровневый.
    //          2. Генерируется EventOfServer92GameEvent - т.е. именно игровое событие
    //             (а не события перехода состояний клиента).
    @Override
    public void sendGameEventToAllViews(
            MatchPlayerList matchPlayerList,
            GameEvent gameEvent,
            Map<String, Class<? extends View>> viewName_ViewClassMap) {
        for (String viewName : viewName_ViewClassMap.keySet()) {
            sendEventOfServer(matchPlayerList, new EventOfServer92GameEvent(viewName, gameEvent));
        }
    }
}
