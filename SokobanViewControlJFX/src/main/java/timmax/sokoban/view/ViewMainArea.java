package timmax.sokoban.view;

import timmax.basetilemodel.*;
import timmax.sokoban.SokobanGame;
import timmax.sokoban.model.Model;
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
    private Model model;

    public ViewMainArea( SokobanGame game) {
        this.game = game;
    }

    @Override
    public void setModel( BaseModel baseModel) {
        this.model = ( Model)baseModel;
        baseModel.addViewListener( this);
    }

    @Override
    public void updateAllTiles( ) {
        // ToDo: Хорошо было-бы сделать циклы в классе-родителе. А из него вызывать updateOneTile().
        for ( int y = 0; y < model.getHeight( ); y++) {
            for ( int x = 0; x < model.getWidth( ); x++) {
                updateOneTile( x, y);
            }
        }
/*
        if ( model.getGameStatus() == GameStatus.GAME) {
            return;
        }
        String dialogMessage = "";
        if ( model.getGameStatus() == GameStatus.VICTORY) {
            dialogMessage = MESSAGE_DIALOG_VICTORY_MESSAGE;
        } else if ( model.getGameStatus() == GameStatus.DEFEAT) {
            dialogMessage = MESSAGE_DIALOG_DEFEAT_MESSAGE;
        }
        game.showMessageDialog( MESSAGE_DIALOG_CELL_COLOR
                , dialogMessage
                , MESSAGE_DIALOG_TEXT_COLOR
                , MESSAGE_DIALOG_TEXT_SIZE);
*/
    }

    @Override
    public void updateOneTile( int x, int y) {
        Tile tile = model.getTileByXY( x, y);
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