package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfServer;

public class TransportPackageOfClient92GameEvent<T> extends TransportPackageOfClient <T> {
    private GameEvent gameEvent;

    public TransportPackageOfClient92GameEvent() {
        super();
    }

    public TransportPackageOfClient92GameEvent(GameEvent gameEvent) {
        this();
        this.gameEvent = gameEvent;
    }

    @Override
    public void execute(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onGameEvent");

        gameEvent.execute(transportOfServer, clientId);
    }

    @Override
    public String toString() {
        return "TransportPackageOfClient92GameEvent{" +
                "gameEvent=" + gameEvent +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameEvent);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameEvent = (GameEvent) in.readObject();
    }
}