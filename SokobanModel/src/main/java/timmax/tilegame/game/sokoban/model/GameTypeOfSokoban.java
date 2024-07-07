package timmax.tilegame.game.sokoban.model;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;

import java.util.Map;

public class GameTypeOfSokoban extends GameType {
    public GameTypeOfSokoban() throws ClassNotFoundException, NoSuchMethodException {
        super("Sokoban", 1, ModelOfServerOfSokoban.class);
    }

    @Override
    public Map<String, ParamOfModelDescription> getMapOfParamsOfModelDescription() {
        return Map.of();
    }
}
