package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

public class EventOfServer51SetGameMatchSet extends EventOfServer {
    private Set<GameMatchId> gameMatchIdSet;

    public EventOfServer51SetGameMatchSet() {
        super();
    }

    public EventOfServer51SetGameMatchSet(Set<GameMatchId> GameMatchIdSet) {
        this();
        this.gameMatchIdSet = GameMatchIdSet;
    }

    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.setGameMatchSet(gameMatchIdSet);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "gameMatchIdSet=" + gameMatchIdSet +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameMatchIdSet);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // ToDo: Исправить "Warning:(44, 26) Unchecked cast: 'java.lang.Object' to 'java.util.Set<timmax.tilegame.basemodel.protocol.server_client.GameMatchId>'"
        //       Например как в readExternal в EventOfServer31SetGameTypeSet
        gameMatchIdSet = (Set<GameMatchId>) in.readObject();
    }
}
