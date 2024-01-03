package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient92GameCommand extends EventOfClient {
    private GameCommand gameCommand;

    public EventOfClient92GameCommand() {
        super();
    }

    public EventOfClient92GameCommand(GameCommand gameCommand) {
        this();
        this.gameCommand = gameCommand;
    }

    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onGameEvent");

        gameCommand.executeOnServer(transportOfServer, clientId);
    }

    @Override
    public String toString() {
        return "EventOfClient92GameEvent{" +
                "gameCommand=" + gameCommand +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameCommand);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameCommand = (GameCommand) in.readObject();
    }
}
