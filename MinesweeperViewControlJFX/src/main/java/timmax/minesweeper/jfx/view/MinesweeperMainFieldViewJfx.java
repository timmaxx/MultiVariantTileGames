package timmax.minesweeper.jfx.view;

import javafx.scene.paint.Color;
import timmax.basetilemodel.*;
import timmax.basetilemodel.gameevent.*;
import timmax.minesweeper.model.gameevent.*;
import timmax.tilegameenginejfx.*;

import static javafx.scene.paint.Color.*;

public class MinesweeperMainFieldViewJfx extends ViewMainFieldJfx {
    private static final Color UNOPENED_CELL_COLOR = ORANGE;
    private static final Color OPENED_CELL_COLOR = GREEN;

    private static final String FLAG = "🚩"; // "\uD83D\uDEA9";
    private static final Color FLAG_CELL_COLOR = YELLOW;

    private static final String MINE = "💣"; // "\uD83D\uDCA3";
    private static final Color MINE_CELL_COLOR = RED;


    public MinesweeperMainFieldViewJfx( BaseModel baseModel) {
        super( baseModel);
    }

    @Override
    protected void drawCellDuringInitMainField( GameStackPane cell) {
        cell.setCellValue( "", cellSize);
        cell.setCellColor( UNOPENED_CELL_COLOR);
    }

    @Override
    protected void drawCellDuringGame( GameEventOneTile gameEventOneTile) {
        GameStackPane cell = getCellByGameEventOneTile( gameEventOneTile);

        if ( gameEventOneTile instanceof GameEventOneTileOpenMine) {
            cell.setCellValueEx( MINE_CELL_COLOR, MINE, cellSize);
        } else if ( gameEventOneTile instanceof GameEventOneTileOpenNoMine) {
            int countOfMineNeighbors = ( (GameEventOneTileOpenNoMine) gameEventOneTile).getCountOfMineNeighbors( );
            cell.setCellNumber( countOfMineNeighbors, cellSize);
            cell.setCellColor( OPENED_CELL_COLOR);
        } else if ( gameEventOneTile instanceof GameEventOneTileChangeFlag) {
            String flag;
            Color tile_cell_color;
            if ( ( ( GameEventOneTileChangeFlag) gameEventOneTile).isFlag( )) {
                flag = FLAG;
                tile_cell_color = FLAG_CELL_COLOR;
            } else {
                flag = "";
                tile_cell_color = UNOPENED_CELL_COLOR;
            }
            cell.setCellValue( flag, cellSize);
            cell.setCellColor( tile_cell_color);
        }
    }
}