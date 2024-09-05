package timmax.tilegame.game.minesweeper.model;

import javafx.scene.paint.Color;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;
import timmax.tilegame.game.minesweeper.model.gameobject.Flag;
import timmax.tilegame.game.minesweeper.model.gameobject.Mine;
import timmax.tilegame.game.minesweeper.model.gameobject.OpenedTile;

import java.util.Set;

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
        //  ToDo:   Warning:(33, 9) Unchecked call to 'GameType(String, int, Class<? extends IGameMatch>, Color, Color, String)' as a member of raw type 'timmax.tilegame.basemodel.protocol.server.GameType'
        super("Minesweeper",
                1,
                //  ToDo:   Переписать GameMatchOfMinesweeper так, что-бы вместо
                //              class TileOfMinesweeper extends Tile
                //          использовались классы
                //              class Mine extends MinesweeperGameObject,
                //              class Flag extends MinesweeperGameObject,
                //              class OpenedTile extends MinesweeperGameObject.
                //          04.09.2024 Создано три класса (Mine, Flag, OpenedTile), но они не задействованы в модели.
                //  ToDo:   Удалить класс Tile после решения предыдущего ToDo (и подобного в другой игре).
                //  ToDo:   Элементами Set должны быть только классы, являющиеся наследниками класса
                //          MinesweeperGameObject (который уже наследник OneTileGameObject).
                //          Сейчас это соответствие не отслеживается, например можно написать так:
                //              Set.of(Object.class),
                //          и компилятор ничего не скажет.
                Set.of(Mine.class, Flag.class, OpenedTile.class),
                GameMatchOfMinesweeper.class,
                UNOPENED_CELL_COLOR, BLACK, ""
        );
        //  Это пример того, как хотелось-бы что-бы компилятор отреагировал в предыдущих строках:
        //      - компилятор возражает и это хорошо:
        //  Set<Class<? extends MinesweeperGameObject>> abcClassSet1 = Set.of(Mine.class, Flag.class, OpenedTile.class, Object.class);
        //      - компилятор не возражает и это тоже хорошо:
        //  Set<Class<? extends MinesweeperGameObject>> abcClassSet2 = Set.of(Mine.class, Flag.class, OpenedTile.class);
        paramName_paramModelDescriptionMap.put(PARAM_NAME_WIDTH, new ParamOfModelDescription(8, 2, 20));
        paramName_paramModelDescriptionMap.put(PARAM_NAME_HEIGHT, new ParamOfModelDescription(8, 2, 20));
        paramName_paramModelDescriptionMap.put(PARAM_NAME_PERCENTS_OF_MINES, new ParamOfModelDescription(10, 1, 99));
    }
}
