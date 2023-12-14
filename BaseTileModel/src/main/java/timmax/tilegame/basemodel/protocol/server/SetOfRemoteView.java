package timmax.tilegame.basemodel.protocol.server;

import java.util.HashSet;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfServer;

public class SetOfRemoteView<T> extends HashSet<RemoteView<T>> {
    protected TransportOfServer<T> transportOfServer;

    public SetOfRemoteView(TransportOfServer<T> transportOfServer) {
        super();
        this.transportOfServer = transportOfServer;
    }

    public void sendGameEvent(GameEvent gameEvent) {
        for (RemoteView<T> remoteView : this) {
            // ToDo: Следует отправлять сообщение не всем выборкам, а только тем, которые подписаны на именно этот тип
            //       сообщения.
            transportOfServer.sendGameEvent(remoteView, gameEvent);
        }
    }
}