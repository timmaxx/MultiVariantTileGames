package timmax.sokoban.view;

import timmax.basetilemodel.*;
import timmax.sokoban.SokobanGame;
import timmax.sokoban.model.SokobanModel;
import timmax.sokoban.model.gameobject.*;
import javafx.scene.paint.Color;

import static javafx.scene.paint.Color.*;

public class ViewMainArea implements View {

    private static final Color COLOR_OF_BOX = GRAY;
    private static final Color COLOR_OF_WALL = RED;
    private static final Color COLOR_OF_HOME = WHITE;
    private static final Color COLOR_OF_PLAYER = GREEN;
    private static final Color COLOR_OF_EMPTY = BLACK;

    private final SokobanGame game;
    private SokobanModel sokobanModel;

    private static final int MESSAGE_DIALOG_TEXT_SIZE = 30;
    private static final Color MESSAGE_DIALOG_CELL_COLOR = AQUA;
    private static final Color MESSAGE_DIALOG_TEXT_COLOR = WHITE;
    private static final String MESSAGE_DIALOG_VICTORY_MESSAGE = "Victory!";
    private static final String MESSAGE_DIALOG_DEFEAT_MESSAGE = "Defeat!";

    public ViewMainArea( SokobanGame game) {
        this.game = game;
    }

    @Override
    public void setModel( BaseModel baseModel) {
        this.sokobanModel = ( SokobanModel)baseModel;
        baseModel.addViewListener( this);
    }

    @Override
    public void updateAllTiles( ) {
        // ToDo: Хорошо было-бы сделать циклы в классе-родителе. А из него вызывать updateOneTile().
        for (int y = 0; y < sokobanModel.getHeight( ); y++) {
            for (int x = 0; x < sokobanModel.getWidth( ); x++) {
                updateOneTile( x, y);
            }
        }

        if ( sokobanModel.getGameStatus() == GameStatus.GAME) {
            return;
        }
        String dialogMessage = "";
        if ( sokobanModel.getGameStatus() == GameStatus.VICTORY) {
            dialogMessage = MESSAGE_DIALOG_VICTORY_MESSAGE;
        } else if ( sokobanModel.getGameStatus() == GameStatus.DEFEAT) {
            dialogMessage = MESSAGE_DIALOG_DEFEAT_MESSAGE;
        }
        game.showMessageDialog( MESSAGE_DIALOG_CELL_COLOR
                , dialogMessage
                , MESSAGE_DIALOG_TEXT_COLOR
                , MESSAGE_DIALOG_TEXT_SIZE);

    }

    @Override
    public void updateOneTile( int x, int y) {
        Tile tile = sokobanModel.getTileByXY( x, y);
        if ( tile == null) {
            return;
        }
        if ( tile.isBox( )) {
            game.setCellColor( x, y, COLOR_OF_BOX);
        } else if ( tile.isWall( )) {
            game.setCellColor( x, y, COLOR_OF_WALL);
        } else if ( tile.isHome( )) {
            game.setCellColor( x, y, COLOR_OF_HOME);
        } else if ( tile.isPlayer( )) {
            game.setCellColor( x, y, COLOR_OF_PLAYER);
        } else {
            game.setCellColor( x, y, COLOR_OF_EMPTY);
        }
    }
}