package timmax.tilegame.minesweeper.model;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;
import timmax.tilegame.basemodel.protocol.server_client.GuiDefaultConstants;

import static timmax.tilegame.basemodel.protocol.server.GameMatch.PARAM_NAME_HEIGHT;
import static timmax.tilegame.basemodel.protocol.server.GameMatch.PARAM_NAME_WIDTH;
import static timmax.tilegame.minesweeper.model.GameMatchOfMinesweeper.*;

public class GameTypeOfMinesweeper extends GameType {
    public GameTypeOfMinesweeper() throws ClassNotFoundException, NoSuchMethodException {
        super("Minesweeper",
                1,
                //  ToDo:   Элементами Set должны быть только классы, являющиеся наследниками класса
                //          MinesweeperGameObject (который уже наследник GameObject).
                //          Сейчас это соответствие не отслеживается, например можно написать так:
                //              Set.of(Object.class),
                //          и компилятор ничего не скажет.
                //  Set.of(MGOSMineIsNotOpenedWithFlag.class, MGOSMineIsNotOpenedWithoutFlag.class, MGOSMineIsOpened.class,
                //          MGOSNoMineIsNotOpenedWithFlag.class, MGOSNoMineIsNotOpenedWithoutFlag.class, MGOSNoMineIsOpened.class
                //  ),
                GameMatchOfMinesweeper.class,
                new GuiDefaultConstants(UNOPENED_BACKGROUND_COLOR, UNOPENED_TEXT_COLOR, UNOPENED_TEXT)
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
