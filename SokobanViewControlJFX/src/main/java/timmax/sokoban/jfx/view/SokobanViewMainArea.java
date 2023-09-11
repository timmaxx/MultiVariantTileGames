package timmax.sokoban.jfx.view;

import timmax.basetilemodel.*;
import timmax.basetilemodel.tile.Tile;
import timmax.sokoban.model.SokobanModel;
import timmax.sokoban.model.gameobject.*;

import javafx.scene.paint.Color;
import timmax.tilegameenginejfx.*;

import java.util.List;

import static javafx.scene.paint.Color.*;

public class SokobanViewMainArea extends ViewMainAreaJfx {
    private static final Color WALL_CELL_COLOR = RED;
    private static final Color HOME_CELL_COLOR = WHITE;
    private static final Color EMPTY_CELL_COLOR = BLACK;

    private static final String PLAYER = "😀"; // "\uF9CD"; // "&";
    private static final Color PLAYER_TEXT_COLOR = GREEN;

    private static final String BOX = "█"; // "❐"; // "▉"; // "[]";
    private static final Color BOX_TEXT_COLOR = BLUE;


    public SokobanViewMainArea( BaseModel baseModel) {
        super( baseModel);
    }

    @Override
    public void updateOneTile( int x, int y) {
        SokobanModel sokobanModel = ( SokobanModel)baseModel;
        List< Tile> listOfTile = sokobanModel.getListOfXY( x, y);
        cells[ y][ x].setCellColor( EMPTY_CELL_COLOR);
        cells[ y][ x].setCellValue( "", cellSize);
        for ( Tile tile : listOfTile) {
            if ( tile instanceof Wall) {
                cells[ y][ x].setCellColor( WALL_CELL_COLOR);
            } else if ( tile instanceof Home) {
                cells[ y][ x].setCellColor( HOME_CELL_COLOR);
            }

            if ( tile instanceof Player) {
                cells[ y][ x].setCellValue( PLAYER, cellSize);
                cells[ y][ x].setCellTextColor( PLAYER_TEXT_COLOR);
            } else if ( tile instanceof Box) {
                cells[ y][ x].setCellValue( BOX, cellSize);
                cells[ y][ x].setCellTextColor( BOX_TEXT_COLOR);
            }
        }
    }
}