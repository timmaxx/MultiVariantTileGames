package timmax.tilegame.basemodel.protocol.server;

import java.util.ArrayList;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfServer;

public class ListOfRemoteView<T> extends ArrayList<RemoteView<T>> {
    private final TransportOfServer<T> transportOfServer;

    public ListOfRemoteView(TransportOfServer<T> transportOfServer) {
        super();
        this.transportOfServer = transportOfServer;
    }

    public void sendGameEvent(GameEvent gameEvent) {
        for (RemoteView<T> remoteView : this) {
            // ToDo: Следует отправлять сообщение не всем выборкам, а только тем, которые подписаны на именно этот тип
            //       сообщения.
            transportOfServer.sendGameEventToRemoteView(remoteView, gameEvent);
        }
    }
}
