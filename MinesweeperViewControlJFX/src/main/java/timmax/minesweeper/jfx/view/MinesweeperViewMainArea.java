package timmax.minesweeper.jfx.view;

import javafx.scene.paint.Color;
import timmax.basetilemodel.*;
import timmax.minesweeper.model.*;
import timmax.tilegameenginejfx.*;

import static javafx.scene.paint.Color.*;

public class MinesweeperViewMainArea extends ViewMainArea {
    private static final Color UNOPENED_CELL_COLOR = ORANGE;
    private static final Color OPENED_CELL_COLOR = GREEN;

    private static final String FLAG = "🚩"; // "\uD83D\uDEA9";
    private static final Color FLAG_CELL_COLOR = YELLOW;

    private static final String MINE = "💣"; // "\uD83D\uDCA3";
    private static final Color MINE_CELL_COLOR = RED;

    Game game;


    public MinesweeperViewMainArea( BaseModel baseModel, Game game) {
        super( baseModel);
        this.game = game;
        game.setScreenSize( baseModel.getWidth( ), baseModel.getHeight( ));
    }

    @Override
    public void updateOneTile( int x, int y) {
        MinesweeperModel minesweeperModel = ( MinesweeperModel)baseModel;
        if ( minesweeperModel.getMinesweeperTileIsOpen( x, y)) {
            if ( minesweeperModel.getMinesweeperTileIsMine( x, y)) {
                game.setCellValueEx( x, y, MINE_CELL_COLOR, MINE);
            } else {
                game.setCellNumber( x, y, minesweeperModel.getCountOfMineNeighbors( x, y));
                game.setCellColor( x, y, OPENED_CELL_COLOR);
            }
        } else {
            if ( minesweeperModel.getMinesweeperTileIsFlag( x, y)) {
                game.setCellValue( x, y, FLAG);
                game.setCellColor( x, y, FLAG_CELL_COLOR);
            } else {
                game.setCellValue( x, y, "");
                game.setCellColor( x, y, UNOPENED_CELL_COLOR);
            }
        }
    }
}