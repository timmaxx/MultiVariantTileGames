package timmax.tilegame.game.sokoban.jfx.view;

import javafx.scene.paint.Color;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

import timmax.tilegame.guiengine.jfx.GameStackPane;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;
import timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx;

import timmax.tilegame.game.sokoban.model.gameevent.GameEventOneTileSokobanChangeable;
import timmax.tilegame.game.sokoban.model.gameobject.WhoMovableInTile;
import timmax.tilegame.game.sokoban.model.gameobject.WhoPersistentInTile;

import static javafx.scene.paint.Color.*;
import static timmax.tilegame.game.sokoban.model.gameobject.WhoMovableInTile.IS_BOX;
import static timmax.tilegame.game.sokoban.model.gameobject.WhoMovableInTile.IS_PLAYER;
import static timmax.tilegame.game.sokoban.model.gameobject.WhoPersistentInTile.IS_HOME;
import static timmax.tilegame.game.sokoban.model.gameobject.WhoPersistentInTile.IS_WALL;

public class SokobanMainFieldViewJfx extends ViewMainFieldJfx {
    private static final Color WALL_CELL_COLOR = RED;
    private static final Color HOME_CELL_COLOR = WHITE;
    private static final Color EMPTY_CELL_COLOR = BLACK;

    private static final String PLAYER = "😀"; // "\uF9CD"; // "&";
    private static final Color PLAYER_TEXT_COLOR = GREEN;

    private static final String BOX = "█"; // "❐"; // "▉"; // "[]";
    private static final Color BOX_TEXT_COLOR = BLUE;


    public SokobanMainFieldViewJfx( BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        super( baseModel, gameStackPaneController);
    }

    @Override
    protected void drawCellDuringGame( GameEventOneTile gameEventOneTile) {
        GameStackPane cell = getCellByGameEventOneTile( gameEventOneTile);

        GameEventOneTileSokobanChangeable gameEventOneTileSokobanChangeable = ( GameEventOneTileSokobanChangeable)gameEventOneTile;

        WhoPersistentInTile whoPersistentInTile = gameEventOneTileSokobanChangeable.getWhoPersistentInTile( );
        if ( whoPersistentInTile == IS_WALL) {
            cell.setCellColor( WALL_CELL_COLOR);
        } else if ( whoPersistentInTile == IS_HOME) {
            cell.setCellColor( HOME_CELL_COLOR);
        } else { // IS_EMPTY
            cell.setCellColor( EMPTY_CELL_COLOR);
        }

        WhoMovableInTile whoMovableInTile = gameEventOneTileSokobanChangeable.getWhoMovableInTile( );
        if ( whoMovableInTile == IS_PLAYER) {
            cell.setCellValue( PLAYER, cellSize);
            cell.setCellTextColor( PLAYER_TEXT_COLOR);
        } else if ( whoMovableInTile == IS_BOX) {
            cell.setCellValue( BOX, cellSize);
            cell.setCellTextColor( BOX_TEXT_COLOR);
        } else { // IS_NOBODY
            cell.setCellValue( "", cellSize);
        }
    }
}