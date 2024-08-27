package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient61SelectGameMatch<ClientId> extends EventOfClient {
    private GameMatch<ClientId> gameMatch;

    public EventOfClient61SelectGameMatch() {
        super();
    }

    public EventOfClient61SelectGameMatch(GameMatch<ClientId> gameMatch) {
        this();
        this.gameMatch = gameMatch;
    }

    // class EventOfClient
    @Override
    public <ClientId> void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton) {
        remoteClientStateAutomaton.selectGameMatch(gameMatch);
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameMatch);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameMatch = (GameMatch) in.readObject();
    }

    // class Object
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "gameMatch=" + gameMatch +
                '}';
    }
}
