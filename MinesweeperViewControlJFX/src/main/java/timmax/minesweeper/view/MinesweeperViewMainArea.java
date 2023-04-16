package timmax.minesweeper.view;

import javafx.scene.paint.Color;
import timmax.basetilemodel.ViewMainArea;
import timmax.minesweeper.model.*;
import timmax.minesweeper.model.gameobject.MinesweeperObject;
import timmax.tilegameenginejfx.*;

import static javafx.scene.paint.Color.*;

public class MinesweeperViewMainArea extends ViewMainArea {
    Game game;
    private static final String MINE = "💣"; // "\uD83D\uDCA3";
    private static final String FLAG = "🚩"; // "\uD83D\uDEA9";

    private static final Color CELL_COLOR_FOR_MINE = RED;

    public MinesweeperViewMainArea( Game game) {
        this.game = game;
    }

    @Override
    public void updateOneTile( int x, int y) {
        MinesweeperObject minesweeperObject = ((MinesweeperModel)model).getMinesweeperObject( x, y);
        if ( minesweeperObject.isOpen( )) {
            if ( minesweeperObject.isMine( )) {
                game.setCellValueEx( x, y, CELL_COLOR_FOR_MINE, MINE);
            } else {
                game.setCellNumber( x, y, minesweeperObject.getCountOfMineNeighbors( ));
                game.setCellColor( x, y, GREEN); // ToDo constant
            }
        } else {
            if ( minesweeperObject.isFlag( )) {
                game.setCellValue( x, y, FLAG); // ToDo constant
                game.setCellColor( x, y, YELLOW); // ToDo constant
            } else {
                game.setCellValue( x, y, "");
                game.setCellColor( x, y, ORANGE); // ToDo constant
            }
        }
    }
}