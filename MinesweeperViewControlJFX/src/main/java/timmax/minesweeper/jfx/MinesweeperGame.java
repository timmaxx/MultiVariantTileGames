package timmax.minesweeper.jfx;

import javafx.scene.layout.Pane;
import timmax.basetilemodel.*;
import timmax.tilegameenginejfx.*;
import timmax.minesweeper.model.*;
import timmax.minesweeper.jfx.controller.MinesweeperController;
import timmax.minesweeper.jfx.view.*;

public class MinesweeperGame extends Game {
/*
    private static final int REST_OF_MINE_INSTALLATION_IN_PERCENTS = 10;
    private final static int SIDE_OF_WIDTH = 15;
    private final static int SIDE_OF_HEIGHT = 10;
*/

    @Override
    public BaseModel initModel( ) {
        return new MinesweeperModel( );
    }

    @Override
    public GameController initGameController( BaseModel baseModel, Game game) {
        return new MinesweeperController( baseModel, game);
    }

    @Override
    public ViewMainArea initViewMainArea( BaseModel baseModel, Pane root) {
        return new MinesweeperViewMainArea( baseModel, root);
    }

    @Override
    public String initTitle( ) {
        return "Minesweeper";
    }
}