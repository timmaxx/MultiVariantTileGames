package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfModel;

import java.util.HashSet;

public class SetOfRemoteView<T> extends HashSet<RemoteView<T>> {
    protected TransportOfModel<T> transportOfModel;

    public SetOfRemoteView(TransportOfModel<T> transportOfModel) {
        super();
        this.transportOfModel = transportOfModel;
    }

    public void sendGameEvent(GameEvent gameEvent) {
        for (RemoteView<T> remoteView : this) {
            // ToDo: Следует отправлять сообщение не всем выборкам, а только тем, которые подписаны на именно этот тип
            //       сообщения.
            transportOfModel.sendGameEvent(remoteView, gameEvent);
        }
    }
}