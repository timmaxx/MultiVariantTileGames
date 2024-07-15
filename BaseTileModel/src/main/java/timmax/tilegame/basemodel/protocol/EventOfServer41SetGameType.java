package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

public class EventOfServer41SetGameType extends EventOfServer {
    private String gameTypeName;
    private Set<GameMatchId> gameMatchIdSet;

    public EventOfServer41SetGameType() {
        super();
    }

    public EventOfServer41SetGameType(String gameTypeName, Set<GameMatchId> gameMatchIdSet) {
        this();
        this.gameTypeName = gameTypeName;
        this.gameMatchIdSet = gameMatchIdSet;
    }

    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        GameType gameType =
                localClientStateAutomaton
                        .getGameTypeSet()
                        .stream()
                        .filter(x -> x.getGameTypeName().equals(gameTypeName))
                        .findAny()
                        .orElse(null);

        localClientStateAutomaton.setGameType(gameType, gameMatchIdSet);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "gameTypeName='" + gameTypeName + '\'' +
                ", gameMatchIdSet=" + gameMatchIdSet +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameTypeName);
        out.writeObject(gameMatchIdSet);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameTypeName = (String) in.readObject();
        // ToDo: Исправить "Warning:(58, 26) Unchecked cast: 'java.lang.Object' to 'java.util.Set<timmax.tilegame.basemodel.protocol.server_client.GameMatchId>'"
        //       Например как в readExternal в EventOfServer31SetGameTypeSet
        gameMatchIdSet = (Set<GameMatchId>) in.readObject();
    }
}
