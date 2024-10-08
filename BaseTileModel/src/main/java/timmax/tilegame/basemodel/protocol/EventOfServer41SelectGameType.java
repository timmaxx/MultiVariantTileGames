package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

public class EventOfServer41SelectGameType extends EventOfServer {
    private String gameTypeName;
    private Set<GameMatchDto> gameMatchDtoSet;

    public EventOfServer41SelectGameType() {
        super();
    }

    public EventOfServer41SelectGameType(String gameTypeName, Set<GameMatchDto> gameMatchDtoSet) {
        this();
        this.gameTypeName = gameTypeName;
        this.gameMatchDtoSet = gameMatchDtoSet;
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

        localClientStateAutomaton.selectGameType(gameType, gameMatchDtoSet);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "gameTypeName='" + gameTypeName + '\'' +
                ", gameMatchDtoSet=" + gameMatchDtoSet +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameTypeName);
        out.writeObject(gameMatchDtoSet);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameTypeName = (String) in.readObject();
        // ToDo: Избавиться от "Warning:(58, 27) Unchecked cast: 'java.lang.Object' to 'java.util.Set<timmax.tilegame.basemodel.protocol.server_client.GameMatchDto>'"
        //       Например как в readExternal в EventOfServer41SelectGameTypeSet
        gameMatchDtoSet = (Set<GameMatchDto>) in.readObject();
    }
}
