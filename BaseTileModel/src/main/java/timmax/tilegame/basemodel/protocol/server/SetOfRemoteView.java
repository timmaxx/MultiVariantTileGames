package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfServer;

import java.util.HashSet;

public class SetOfRemoteView<T> extends HashSet<RemoteView<T>> {
    protected TransportOfServer<T> transportOfServer;

    public SetOfRemoteView(TransportOfServer<T> transportOfServer) {
        super();
/*
        System.out.println("SetOfRemoteView<T>");
        System.out.println("public SetOfRemoteView(TransportOfModel<T> transportOfModel)");
        System.out.println("transportOfModel = " + transportOfModel);
*/
        this.transportOfServer = transportOfServer;
    }

    public void sendGameEvent(GameEvent gameEvent) {
/*
        System.out.println("SetOfRemoteView<T>");
        System.out.println("public void sendGameEvent(GameEvent gameEvent)");
        System.out.println("transportOfModel = " + transportOfModel);
*/
        for (RemoteView<T> remoteView : this) {
            // ToDo: Следует отправлять сообщение не всем выборкам, а только тем, которые подписаны на именно этот тип
            //       сообщения.
            transportOfServer.sendGameEvent(remoteView, gameEvent);
        }
    }
}