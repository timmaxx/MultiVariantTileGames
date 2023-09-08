package timmax.sokoban.jfx.view;

import timmax.basetilemodel.*;
import timmax.basetilemodel.tile.Tile;
import timmax.sokoban.model.SokobanModel;
import timmax.sokoban.model.gameobject.*;

import javafx.scene.paint.Color;
import timmax.tilegameenginejfx.*;

import java.util.List;

import static javafx.scene.paint.Color.*;

public class SokobanViewMainArea extends ViewMainArea {
    private static final Color WALL_CELL_COLOR = RED;
    private static final Color HOME_CELL_COLOR = WHITE;
    private static final Color EMPTY_CELL_COLOR = BLACK;

    private static final String PLAYER = "😀"; // "\uF9CD"; // "&";
    private static final Color PLAYER_TEXT_COLOR = GREEN;

    private static final String BOX = "█"; // "❐"; // "▉"; // "[]";
    private static final Color BOX_TEXT_COLOR = BLUE;

    Game game;


    public SokobanViewMainArea( BaseModel baseModel, Game game) {
        super( baseModel);
        this.game = game;
        game.setScreenSize( baseModel.getWidth( ), baseModel.getHeight( ));
    }

    @Override
    public void updateOneTile( int x, int y) {
        SokobanModel sokobanModel = ( SokobanModel)baseModel;
        List< Tile> listOfTile = sokobanModel.getListOfXY( x, y);
        game.setCellColor( x, y, EMPTY_CELL_COLOR);
        game.setCellValue( x, y, "");
        for ( Tile tile : listOfTile) {
            if ( tile instanceof Wall) {
                game.setCellColor( x, y, WALL_CELL_COLOR);
            } else if ( tile instanceof Home) {
                game.setCellColor( x, y, HOME_CELL_COLOR);
            }

            if ( tile instanceof Player) {
                game.setCellValue( x, y, PLAYER);
                game.setCellTextColor( x, y, PLAYER_TEXT_COLOR);
            } else if ( tile instanceof Box) {
                game.setCellValue( x, y, BOX);
                game.setCellTextColor( x, y, BOX_TEXT_COLOR);
            }
        }
    }
}