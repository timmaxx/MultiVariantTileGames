package timmax.tilegame.basemodel.protocol.server;

import java.util.ArrayList;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfServer;

public class ListOfRemoteView<ClienId> extends ArrayList<RemoteView<ClienId>> {
    private final TransportOfServer<ClienId> transportOfServer;

    public ListOfRemoteView(TransportOfServer<ClienId> transportOfServer) {
        super();
        this.transportOfServer = transportOfServer;
    }

    public void sendGameEvent(GameEvent gameEvent) {
        for (RemoteView<ClienId> remoteView : this) {
            // ToDo: Следует отправлять сообщение не всем выборкам, а только тем, которые подписаны на именно этот тип
            //       сообщения.
            transportOfServer.sendGameEventToRemoteView(remoteView, gameEvent);
        }
    }
}
