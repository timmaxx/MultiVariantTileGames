package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient92GameCommand<ClientId> extends EventOfClient<ClientId> {
    private GameCommand gameCommand;

    public EventOfClient92GameCommand() {
        super();
    }

    public EventOfClient92GameCommand(GameCommand gameCommand) {
        this();
        this.gameCommand = gameCommand;
    }

    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        gameCommand.executeOnServer(remoteClientStateAutomaton.getGameMatchX());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "gameCommand=" + gameCommand +
                '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameCommand);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameCommand = (GameCommand) in.readObject();
    }
}
