package timmax.tilegame.basemodel.protocol.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.EventOfServer92GameEvent;
import timmax.tilegame.transport.TransportOfServer;

public class MapOfClientID_SetOfViewName<ClientId> extends HashMap<ClientId, Set<String>> {
    private final TransportOfServer<ClientId> transportOfServer;

    public MapOfClientID_SetOfViewName(TransportOfServer<ClientId> transportOfServer) {
        super();
        this.transportOfServer = transportOfServer;
    }

    public void addRemoteView(RemoteView<ClientId> remoteView) {
        Set<String> setOfViewName = get(remoteView.getClientId());
        if (setOfViewName == null) {
            setOfViewName = new HashSet<>();
            setOfViewName.add(remoteView.getViewName());
            put(remoteView.getClientId(), setOfViewName);
        } else {
            setOfViewName.add(remoteView.getViewName());
        }
    }

    public void sendGameEvent(GameEvent gameEvent) {
        for (Map.Entry<ClientId, Set<String>> entry : entrySet()) {
            // ToDo: Следует отправлять сообщение не всем выборкам, а только тем, которые подписаны на именно этот тип
            //       сообщения.
            for (String viewName : entry.getValue()) {
                EventOfServer eventOfServer = new EventOfServer92GameEvent(viewName, gameEvent);
                transportOfServer.sendEventOfServer(entry.getKey(), eventOfServer);
            }
        }
    }
}
