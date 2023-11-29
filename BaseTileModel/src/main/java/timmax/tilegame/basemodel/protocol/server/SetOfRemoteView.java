package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfModel;

import java.util.HashSet;

public class SetOfRemoteView<T> extends HashSet<RemoteView<T>> {
    protected TransportOfModel<T> transportOfModel;

    public SetOfRemoteView(TransportOfModel<T> transportOfModel) {
        super();
        System.out.println("SetOfRemoteView<T>");
        System.out.println("public SetOfRemoteView(TransportOfModel<T> transportOfModel)");
        System.out.println("transportOfModel = " + transportOfModel);
        this.transportOfModel = transportOfModel;
    }

    public void sendGameEvent(GameEvent gameEvent) {
        System.out.println("SetOfRemoteView<T>");
        System.out.println("public void sendGameEvent(GameEvent gameEvent)");
        System.out.println("transportOfModel = " + transportOfModel);
        for (RemoteView<T> remoteView : this) {
            // ToDo: Следует отправлять сообщение не всем выборкам, а только тем, которые подписаны на именно этот тип
            //       сообщения.
            transportOfModel.sendGameEvent(remoteView, gameEvent);
        }
    }
}