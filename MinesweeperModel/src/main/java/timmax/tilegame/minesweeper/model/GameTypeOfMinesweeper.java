package timmax.tilegame.minesweeper.model;

import javafx.scene.paint.Color;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;
import timmax.tilegame.minesweeper.model.placement.gameobject.*;

import java.util.Set;

import static javafx.scene.paint.Color.*;
import static timmax.tilegame.basemodel.protocol.server.GameMatch.PARAM_NAME_HEIGHT;
import static timmax.tilegame.basemodel.protocol.server.GameMatch.PARAM_NAME_WIDTH;
import static timmax.tilegame.minesweeper.model.GameMatchOfMinesweeper.PARAM_NAME_PERCENTS_OF_MINES;

//  Warning:(13, 44) Raw use of parameterized class 'GameType'
public class GameTypeOfMinesweeper extends GameType {

    // ToDo: Удалить отсюда константы, описанные ниже, т.к. они относятся к визуализации.
    public static final String UNOPENED = "";
    public static final Color UNOPENED_BACKGROUND_COLOR = ORANGE;
    public static final Color UNOPENED_TEXT_COLOR = BLACK;

    public static final String FLAG = "🚩"; // "\uD83D\uDEA9";
    public static final Color FLAG_BACKGROUND_COLOR = YELLOW;

    public static final Color NOMINE_BACKGROUND_COLOR = GREEN;
    public static final Color NOMINE_TEXT_COLOR = BLACK;

    public static final String MINE = "💣"; // "\uD83D\uDCA3";
    public static final Color MINE_BACKGROUND_COLOR = RED;
    public static final Color MINE_TEXT_COLOR = BLACK;

    public GameTypeOfMinesweeper() throws ClassNotFoundException, NoSuchMethodException {
        //  ToDo:   Warning:(34, 9) Unchecked call to 'GameType(String, int, Class<? extends IGameMatch>, Color, Color, String)' as a member of raw type 'timmax.tilegame.basemodel.protocol.server.GameType'
        super("Minesweeper",
                1,
                //  ToDo:   Элементами Set должны быть только классы, являющиеся наследниками класса
                //          MinesweeperGameObject (который уже наследник GameObject).
                //          Сейчас это соответствие не отслеживается, например можно написать так:
                //              Set.of(Object.class),
                //          и компилятор ничего не скажет.
                Set.of(MGOSMineIsNotOpenedWithFlag.class, MGOSMineIsNotOpenedWithoutFlag.class, MGOSMineIsOpened.class,
                        MGOSNoMineIsNotOpenedWithFlag.class, MGOSNoMineIsNotOpenedWithoutFlag.class, MGOSNoMineIsOpened.class
                ),
                GameMatchOfMinesweeper.class,
                UNOPENED_BACKGROUND_COLOR, UNOPENED_TEXT_COLOR, UNOPENED
        );
        //  Это пример того, как хотелось-бы что-бы компилятор отреагировал в предыдущих строках:
        //      - компилятор возражает и это хорошо:
        //  Set<Class<? extends MinesweeperGameObject>> abcClassSet1 = Set.of(MGOMine.class, Flag.class, UnOpenedTile.class, OpenedTile.class, Object.class);
        //      - компилятор не возражает и это тоже хорошо:
        //  Set<Class<? extends MinesweeperGameObject>> abcClassSet2 = Set.of(MGOMine.class, Flag.class, UnOpenedTile.class, OpenedTile.class);
        paramName_paramModelDescriptionMap.put(PARAM_NAME_WIDTH, new ParamOfModelDescription(8, 2, 20));
        paramName_paramModelDescriptionMap.put(PARAM_NAME_HEIGHT, new ParamOfModelDescription(8, 2, 20));
        paramName_paramModelDescriptionMap.put(PARAM_NAME_PERCENTS_OF_MINES, new ParamOfModelDescription(10, 1, 99));
    }
}
