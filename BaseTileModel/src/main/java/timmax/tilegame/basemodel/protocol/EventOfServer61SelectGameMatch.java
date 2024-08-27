package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameMatch;

public class EventOfServer61SelectGameMatch<ClientId> extends EventOfServer {
    GameMatch<ClientId> gameMatch;

    public EventOfServer61SelectGameMatch() {
        super();
    }

    public EventOfServer61SelectGameMatch(GameMatch<ClientId> gameMatch) {
        this();
        this.gameMatch = gameMatch;
    }

    // class EventOfServer
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.selectGameMatch(gameMatch);
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
                "gameMatchDto=" + gameMatch +
                '}';
    }
}
