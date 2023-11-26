package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfModel;

import java.util.HashSet;

public class SetOfRemoteView extends HashSet<RemoteView> {
    protected TransportOfModel transportOfModel;

    public SetOfRemoteView(TransportOfModel transportOfModel) {
        super();
        this.transportOfModel = transportOfModel;
    }

    public void sendGameEvent( GameEvent gameEvent) {
        for (RemoteView remoteView: this) {
            // ToDo: Следует отправлять сообщение не всем выборкам, а только тем, которые подписаны на именно этот тип
            //       сообщения.
            transportOfModel.sendGameEvent(remoteView, gameEvent);
        }
    }
}