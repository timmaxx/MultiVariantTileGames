package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient92GameEvent<T> extends EventOfClient<T> {
    private GameEvent gameEvent;

    public EventOfClient92GameEvent() {
        super();
    }

    public EventOfClient92GameEvent(GameEvent gameEvent) {
        this();
        this.gameEvent = gameEvent;
    }

    @Override
    public void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onGameEvent");

        gameEvent.executeOnServer(transportOfServer, clientId);
    }

    @Override
    public String toString() {
        return "EventOfClient92GameEvent{" +
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