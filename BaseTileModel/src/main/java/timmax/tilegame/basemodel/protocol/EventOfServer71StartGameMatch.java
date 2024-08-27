package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameMatch;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class EventOfServer71StartGameMatch<ClientId> extends EventOfServer {
    private GameMatch<ClientId> gameMatch;

    public EventOfServer71StartGameMatch() {
        super();
    }

    public EventOfServer71StartGameMatch(GameMatch<ClientId> gameMatch) {
        this();
        this.gameMatch = gameMatch;
    }

    // class EventOfServer
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.startGameMatch(gameMatch);
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(gameMatch);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        gameMatch = (GameMatch<ClientId>) in.readObject();
    }

    // class Object
    @Override
    public String toString() {
        return "EventOfServer71StartGameMatch{" +
                "gameMatch=" + gameMatch +
                '}';
    }
}
