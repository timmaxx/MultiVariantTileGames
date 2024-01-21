package timmax.tilegame.basemodel.protocol.server;

import java.util.ArrayList;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.EventOfServer92GameEvent;
import timmax.tilegame.transport.TransportOfServer;

public class ListOfRemoteView<ClientId> extends ArrayList<RemoteView<ClientId>> {
    private final TransportOfServer<ClientId> transportOfServer;

    public ListOfRemoteView(TransportOfServer<ClientId> transportOfServer) {
        super();
        this.transportOfServer = transportOfServer;
    }

    public void sendGameEvent(GameEvent gameEvent) {
        for (RemoteView<ClientId> remoteView : this) {
            // ToDo: Следует отправлять сообщение не всем выборкам, а только тем, которые подписаны на именно этот тип
            //       сообщения.
            EventOfServer eventOfServer = new EventOfServer92GameEvent(remoteView.getViewId(), gameEvent);
            transportOfServer.sendEventOfServer(remoteView.getClientId(), eventOfServer);
        }
    }
}
