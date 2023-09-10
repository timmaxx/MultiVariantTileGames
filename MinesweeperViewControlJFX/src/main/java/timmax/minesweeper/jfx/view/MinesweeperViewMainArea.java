package timmax.minesweeper.jfx.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import timmax.basetilemodel.*;
import timmax.minesweeper.model.*;
import timmax.tilegameenginejfx.*;

import static javafx.scene.paint.Color.*;

public class MinesweeperViewMainArea extends ViewMainAreaJfx {
    private static final Color UNOPENED_CELL_COLOR = ORANGE;
    private static final Color OPENED_CELL_COLOR = GREEN;

    private static final String FLAG = "🚩"; // "\uD83D\uDEA9";
    private static final Color FLAG_CELL_COLOR = YELLOW;

    private static final String MINE = "💣"; // "\uD83D\uDCA3";
    private static final Color MINE_CELL_COLOR = RED;


    public MinesweeperViewMainArea( BaseModel baseModel, Pane root) {
        super( baseModel, root);
    }

    @Override
    public void updateOneTile( int x, int y) {
        MinesweeperModel minesweeperModel = ( MinesweeperModel)baseModel;
        if ( minesweeperModel.getMinesweeperTileIsOpen( x, y)) {
            if ( minesweeperModel.getMinesweeperTileIsMine( x, y)) {
                cells[ y][ x].setCellValueEx( MINE_CELL_COLOR, MINE, cellSize);
            } else {
                cells[ y][ x].setCellNumber( minesweeperModel.getCountOfMineNeighbors( x, y), cellSize);
                cells[ y][ x].setCellColor( OPENED_CELL_COLOR);
            }
        } else {
            if ( minesweeperModel.getMinesweeperTileIsFlag( x, y)) {
                cells[ y][ x].setCellValue( FLAG, cellSize);
                cells[ y][ x].setCellColor( FLAG_CELL_COLOR);
            } else {
                cells[ y][ x].setCellValue( "", cellSize);
                cells[ y][ x].setCellColor( UNOPENED_CELL_COLOR);
            }
        }
    }
}