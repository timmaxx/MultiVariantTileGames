package timmax.sokoban.view;

import timmax.basetilemodel.*;
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
        List< XY> listOfXY = sokobanModel.getListOfXY( x, y);
        game.setCellColor( x, y, COLOR_OF_EMPTY);
        game.setCellValue( x, y, "");
        for ( XY xy: listOfXY) {
            if ( xy instanceof Wall) {
                game.setCellColor( x, y, COLOR_OF_WALL);
            } else if ( xy instanceof Home) {
                game.setCellColor( x, y, COLOR_OF_HOME);
            }

            if ( xy instanceof Player) {
                game.setCellValue( x, y, PLAYER);
                game.setCellTextColor( x, y, COLOR_OF_PLAYER);
            } else if ( xy instanceof Box) {
                game.setCellValue( x, y, BOX);
                game.setCellTextColor( x, y, COLOR_OF_BOX);
            }
        }
    }
}