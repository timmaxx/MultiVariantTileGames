package timmax.sokoban.view;

import timmax.sokoban.model.gameobject.*;
import javafx.scene.paint.Color;
import timmax.tilegameenginejfx.*;

import static javafx.scene.paint.Color.*;

public class SokobanViewMainArea extends ViewMainArea< SokobanTile> {
    private static final Color COLOR_OF_WALL = RED;
    private static final Color COLOR_OF_HOME = WHITE;
    private static final Color COLOR_OF_EMPTY = BLACK;

    private static final String PLAYER = "😀"; // "\uF9CD"; // "&";
    private static final Color COLOR_OF_PLAYER = GREEN;
    private static final String BOX = "█"; // "❐"; // "▉"; // "[]";
    private static final Color COLOR_OF_BOX = BLUE;
/*
    private static final int MESSAGE_DIALOG_TEXT_SIZE = 30;
    private static final Color MESSAGE_DIALOG_CELL_COLOR = AQUA;
    private static final Color MESSAGE_DIALOG_TEXT_COLOR = WHITE;
    private static final String MESSAGE_DIALOG_VICTORY_MESSAGE = "Victory!";
    private static final String MESSAGE_DIALOG_DEFEAT_MESSAGE = "Defeat!";
*/
    public SokobanViewMainArea( Game game) {
        super( game);
    }

    @Override
    public void updateOneTile( int x, int y) {
        SokobanTile tile = model.getTileByXY( x, y);
        if ( tile == null) {
            return;
        }

        if ( tile.isWall( )) {
            game.setCellColor( x, y, COLOR_OF_WALL);
        } else if ( tile.isHome( )) {
            game.setCellColor( x, y, COLOR_OF_HOME);
        } else {
            game.setCellColor( x, y, COLOR_OF_EMPTY);
        }

        if ( tile.isPlayer( )) {
            game.setCellValue( x, y, PLAYER);
            game.setCellTextColor( x, y, COLOR_OF_PLAYER);
        } else if ( tile.isBox( )) {
            game.setCellValue( x, y, BOX);
            game.setCellTextColor( x, y, COLOR_OF_BOX);
        } else {
            game.setCellValue( x, y, "");
        }
    }
}