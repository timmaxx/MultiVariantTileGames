package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.EventOfServer92GameEvent;
import timmax.tilegame.baseview.View;

import java.util.Map;

public class GameEventSender {
    //  ToDo:   Здесь этот метод статический, но возможно стоит его вынести в другой класс (и сделать не статическим).
    //          Например, в RemoteClientStateAutomaton.
    public static <ClientId> void sendGameEventToAllViews(
            GameEvent gameEvent,
            RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton,
            Map<String, Class<? extends View>> viewName_ViewClassMap) {
        for (String viewName : viewName_ViewClassMap.keySet()) {
            EventOfServer eventOfServer = new EventOfServer92GameEvent(viewName, gameEvent);
            remoteClientStateAutomaton.sendEventOfServer(remoteClientStateAutomaton.getClientId(), eventOfServer);
        }
    }
}
