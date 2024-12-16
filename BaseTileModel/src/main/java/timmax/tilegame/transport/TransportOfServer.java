package timmax.tilegame.transport;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.server.MatchPlayerList;
import timmax.tilegame.baseview.View;

import java.util.Map;

public interface TransportOfServer {
    <ClientId> void sendEventOfServer(ClientId clientId, EventOfServer eventOfServer);
    //  Не стал определять этот метод в interface TransportOfServer, поэтому не @Override.
    // void sendEventOfServer(User user, EventOfServer eventOfServer);

    void sendEventOfServer(MatchPlayerList matchPlayerList, EventOfServer eventOfServer);
    void sendGameEventToAllViews(
            MatchPlayerList matchPlayerList,
            GameEvent gameEvent,
            Map<String, Class<? extends View>> viewName_ViewClassMap);
}
