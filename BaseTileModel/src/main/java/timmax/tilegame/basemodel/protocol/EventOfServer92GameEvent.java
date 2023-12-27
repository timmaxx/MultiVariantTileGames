package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer92GameEvent extends EventOfServer {
    private String viewId;
    private GameEvent gameEvent;

    public EventOfServer92GameEvent() {
        super();
    }

    public EventOfServer92GameEvent(String viewId, GameEvent gameEvent) {
        this();
        this.viewId = viewId;
        this.gameEvent = gameEvent;
    }

    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onGameEvent");

        // ToDo: метод executeOnClient вероятно и не нужен...
        // gameEvent.executeOnClient(transportOfClient);

        // 1. Найти в модели viewId.
        // RemoteView<T> remoteView = transportOfClient.getClientState().getSetOfRemoteView().getRemoteViewByViewId(viewId);
        // transportOfClient.

        // 2. Передать в выборку gameEvent.
        // remoteView.
    }

    @Override
    public String toString() {
        return "EventOfServer92GameEvent{" +
                "viewId='" + viewId + '\'' +
                ", gameEvent=" + gameEvent +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(viewId);
        out.writeObject(gameEvent);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        viewId = (String) in.readObject();
        gameEvent = (GameEvent) in.readObject();
    }
}
