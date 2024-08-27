package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameType;

public class EventOfServer41SelectGameType extends EventOfServer {
    private GameType gameType;

    public EventOfServer41SelectGameType() {
        super();
    }

    public EventOfServer41SelectGameType(GameType gameType) {
        this();
        this.gameType = gameType;
    }

    // class EventOfServer
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.selectGameType(gameType);
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameType);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameType = (GameType) in.readObject();
    }

    // class Object
    @Override
    public String toString() {
        return "EventOfServer41SelectGameType{" +
                "gameType=" + gameType +
                '}';
    }
}
