package timmax.tilegame.game.minesweeper.model;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;

import java.util.Map;

import static timmax.tilegame.basemodel.protocol.server.GameMatch.PARAM_NAME_HEIGHT;
import static timmax.tilegame.basemodel.protocol.server.GameMatch.PARAM_NAME_WIDTH;
import static timmax.tilegame.game.minesweeper.model.GameMatchOfMinesweeper.PARAM_NAME_PERCENTS_OF_MINES;

public class GameTypeOfMinesweeper extends GameType {
    public GameTypeOfMinesweeper() throws ClassNotFoundException, NoSuchMethodException {
        super("Minesweeper", 1, GameMatchOfMinesweeper.class);
    }

    @Override
    public Map<String, ParamOfModelDescription> getMapOfParamsOfModelDescription() {
        return Map.of(
                PARAM_NAME_WIDTH, new ParamOfModelDescription(8, 2, 20),
                PARAM_NAME_HEIGHT, new ParamOfModelDescription(8, 2, 20),
                PARAM_NAME_PERCENTS_OF_MINES, new ParamOfModelDescription(10, 1, 99));
    }
}
