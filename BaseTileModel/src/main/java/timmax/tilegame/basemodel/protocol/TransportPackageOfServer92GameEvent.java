package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfClient;

public class TransportPackageOfServer92GameEvent<T> extends TransportPackageOfServer<T> {
    private String viewId;
    private GameEvent gameEvent;

    public TransportPackageOfServer92GameEvent() {
        super();
    }

    public TransportPackageOfServer92GameEvent(String viewId, GameEvent gameEvent) {
        this();
        this.viewId = viewId;
        this.gameEvent = gameEvent;
    }

    @Override
    public void execute(TransportOfClient<T> transportOfClient) {
        System.out.println("  onGameEvent");

        System.out.println("    viewId = " + viewId);
        System.out.println("    gameEvent = " + gameEvent);
    }

    @Override
    public String toString() {
        return "TransportPackageOfServer93GameEvent{" +
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