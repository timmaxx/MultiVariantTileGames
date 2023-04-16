package timmax.minesweeper.view;

import javafx.scene.paint.Color;
import timmax.basetilemodel.*;
import timmax.minesweeper.model.*;
import timmax.tilegameenginejfx.*;

import static javafx.scene.paint.Color.*;

public class MinesweeperViewMainArea extends ViewMainArea {
    Game game;
    private static final String MINE = "💣"; // "\uD83D\uDCA3";
    private static final String FLAG = "🚩"; // "\uD83D\uDEA9";

    private static final Color CELL_COLOR_FOR_MINE = RED;

    public MinesweeperViewMainArea( BaseModel baseModel, Game game) {
        super( baseModel);
        this.game = game;
        game.setScreenSize( baseModel.getWidth(), baseModel.getHeight( ));
    }

    @Override
    public void updateOneTile( int x, int y) {
        MinesweeperModel minesweeperModel = ( MinesweeperModel)baseModel;
        if (minesweeperModel.getMinesweeperObjectIsOpen( x, y)) {
            if (minesweeperModel.getMinesweeperObjectIsMine( x, y)) {
                game.setCellValueEx( x, y, CELL_COLOR_FOR_MINE, MINE);
            } else {
                game.setCellNumber( x, y, minesweeperModel.getCountOfMineNeighbors( x, y));
                game.setCellColor( x, y, GREEN); // ToDo constant
            }
        } else {
            if ( minesweeperModel.getMinesweeperObjectIsFlag( x, y)) {
                game.setCellValue( x, y, FLAG); // ToDo constant
                game.setCellColor( x, y, YELLOW); // ToDo constant
            } else {
                game.setCellValue( x, y, "");
                game.setCellColor( x, y, ORANGE); // ToDo constant
            }
        }
    }
}