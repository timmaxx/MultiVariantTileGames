package timmax.tilegame.game.minesweeper.model;

import javafx.scene.paint.Color;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;

import static javafx.scene.paint.Color.*;
import static timmax.tilegame.basemodel.protocol.server.GameMatch.PARAM_NAME_HEIGHT;
import static timmax.tilegame.basemodel.protocol.server.GameMatch.PARAM_NAME_WIDTH;
import static timmax.tilegame.game.minesweeper.model.GameMatchOfMinesweeper.PARAM_NAME_PERCENTS_OF_MINES;

//  Warning:(13, 44) Raw use of parameterized class 'GameType'
public class GameTypeOfMinesweeper extends GameType {

    // ToDo: Ниже относится к визуализации. Удалить это отсюда.
    //       Константы, описанные ниже относятся к визуализации.
    public static final Color UNOPENED_CELL_COLOR = ORANGE;
    public static final Color OPENED_CELL_COLOR = GREEN;

    public static final String FLAG = "🚩"; // "\uD83D\uDEA9";
    public static final Color FLAG_CELL_COLOR = YELLOW;

    public static final String MINE = "💣"; // "\uD83D\uDCA3";
    public static final Color MINE_CELL_COLOR = RED;

    public GameTypeOfMinesweeper() throws ClassNotFoundException, NoSuchMethodException {
        //  ToDo:   Warning:(28, 9) Unchecked call to 'GameType(String, int, Class<? extends IGameMatch>, Color, Color, String)' as a member of raw type 'timmax.tilegame.basemodel.protocol.server.GameType'
        super("Minesweeper", 1, GameMatchOfMinesweeper.class, UNOPENED_CELL_COLOR, BLACK, "");
        paramName_paramModelDescriptionMap.put(PARAM_NAME_WIDTH, new ParamOfModelDescription(8, 2, 20));
        paramName_paramModelDescriptionMap.put(PARAM_NAME_HEIGHT, new ParamOfModelDescription(8, 2, 20));
        paramName_paramModelDescriptionMap.put(PARAM_NAME_PERCENTS_OF_MINES, new ParamOfModelDescription(10, 1, 99));
    }
}
