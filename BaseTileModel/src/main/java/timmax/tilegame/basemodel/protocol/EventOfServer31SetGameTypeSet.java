package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameType;

public class EventOfServer31SetGameTypeSet extends EventOfServer {
    private Set<GameType> gameTypeSet;

    public EventOfServer31SetGameTypeSet() {
        super();
    }

    public EventOfServer31SetGameTypeSet(Set<GameType> gameTypeSet) {
        this();
        this.gameTypeSet = gameTypeSet;
    }

    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.setGameTypeSet(gameTypeSet);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "gameTypeSet=" + gameTypeSet +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameTypeSet);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Object obj = in.readObject();
        if (obj instanceof Set<?> setOfObj) {
            for (Object objOfList : setOfObj) {
                if (!(objOfList instanceof GameType)) {
                    logger.error("readExternal(ObjectInput in)\n  element of collections is not GameType.");
                    System.exit(1);
                }
            }
            @SuppressWarnings("unchecked")
            Set<GameType> setOfGameType = (Set<GameType>) setOfObj;
            this.gameTypeSet = setOfGameType;
        } else {
            logger.error("readExternal(ObjectInput in)\n  in.readObject() is not instance of Set.");
            System.exit(1);
        }
    }
}
