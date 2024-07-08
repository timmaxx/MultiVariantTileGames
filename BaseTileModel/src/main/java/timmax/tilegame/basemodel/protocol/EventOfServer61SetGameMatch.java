package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

public class EventOfServer61SetGameMatch extends EventOfServer {
    GameMatchId gameMatchId;

    public EventOfServer61SetGameMatch() {
        super();
    }

    public EventOfServer61SetGameMatch(GameMatchId gameMatchId) {
        this();
        this.gameMatchId = gameMatchId;
    }

    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.setGameMatch(gameMatchId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "gameMatchId=" + gameMatchId +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameMatchId);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameMatchId = (GameMatchId) in.readObject();
    }
}
