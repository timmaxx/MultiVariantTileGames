package timmax.tilegame.basemodel.dto;

import timmax.tilegame.basemodel.GameMatchStatus;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;
import java.util.Set;

public class GameMatchExtendedDto extends GameMatchDto {
    private Set<GameEventOneTile> gameEventOneTileSet;

    public GameMatchExtendedDto() {
    }

    public GameMatchExtendedDto(String id, GameMatchStatus gameMatchStatus, Map<String, Integer> paramsOfModelValueMap, Set<GameEventOneTile> gameEventOneTileSet) {
        super(id, gameMatchStatus, paramsOfModelValueMap);
        this.gameEventOneTileSet = gameEventOneTileSet;
    }

    //  ToDo:   Вместо предоставления доступа через геттер, лучше чтобы внутри класса была деятельность по обработке
    //          множества gameEventOneTileSet.
    public Set<GameEventOneTile> getGameEventOneTileSet() {
        return gameEventOneTileSet;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(gameEventOneTileSet);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        // ToDo: Избавиться от "Warning:(39, 31) Unchecked cast: 'java.lang.Object' to 'java.util.Set<timmax.tilegame.basemodel.gameevent.GameEventOneTile>'"
        gameEventOneTileSet = (Set<GameEventOneTile>) in.readObject();
    }

    @Override
    public String toString() {
        return "GameMatchExtendedDto{" +
                "gameEventOneTileSet=" + gameEventOneTileSet +
                '}';
    }
}
