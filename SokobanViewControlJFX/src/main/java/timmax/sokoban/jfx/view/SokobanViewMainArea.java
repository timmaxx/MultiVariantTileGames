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
    Game game;
    private static final Color COLOR_OF_WALL = RED;
    private static final Color COLOR_OF_HOME = WHITE;
    private static final Color COLOR_OF_EMPTY = BLACK;

    private static final String PLAYER = "😀"; // "\uF9CD"; // "&";
    private static final Color COLOR_OF_PLAYER = GREEN;
    private static final String BOX = "█"; // "❐"; // "▉"; // "[]";
    private static final Color COLOR_OF_BOX = BLUE;

    public SokobanViewMainArea( BaseModel baseModel, Game game) {
        super( baseModel);
        this.game = game;
        game.setScreenSize( baseModel.getWidth( ), baseModel.getHeight( ));
    }

    @Override
    public void updateOneTile( int x, int y) {
        SokobanModel sokobanModel = ( SokobanModel)baseModel;
        List<Tile> listOfTile = sokobanModel.getListOfXY( x, y);
        game.setCellColor( x, y, COLOR_OF_EMPTY);
        game.setCellValue( x, y, "");
        for ( Tile tile : listOfTile) {
            if ( tile instanceof Wall) {
                game.setCellColor( x, y, COLOR_OF_WALL);
            } else if ( tile instanceof Home) {
                game.setCellColor( x, y, COLOR_OF_HOME);
            }

            if ( tile instanceof Player) {
                game.setCellValue( x, y, PLAYER);
                game.setCellTextColor( x, y, COLOR_OF_PLAYER);
            } else if ( tile instanceof Box) {
                game.setCellValue( x, y, BOX);
                game.setCellTextColor( x, y, COLOR_OF_BOX);
            }
        }
    }
}