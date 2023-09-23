package timmax.sokoban.jfx.view;

import javafx.scene.input.KeyCode;
import timmax.basetilemodel.*;
import timmax.basetilemodel.gameevent.*;
import timmax.sokoban.model.SokobanModel;
import timmax.sokoban.model.gameevent.*;
import timmax.sokoban.model.gameobject.*;
import javafx.scene.paint.Color;
import timmax.tilegameenginejfx.*;

import static javafx.scene.paint.Color.*;
import static timmax.basetilemodel.tile.Direction.*;
import static timmax.sokoban.model.gameobject.WhoMovableInTile.*;
import static timmax.sokoban.model.gameobject.WhoPersistentInTile.*;

public class SokobanMainFieldViewJfx extends ViewMainFieldJfxController {
    private static final Color WALL_CELL_COLOR = RED;
    private static final Color HOME_CELL_COLOR = WHITE;
    private static final Color EMPTY_CELL_COLOR = BLACK;

    private static final String PLAYER = "😀"; // "\uF9CD"; // "&";
    private static final Color PLAYER_TEXT_COLOR = GREEN;

    private static final String BOX = "█"; // "❐"; // "▉"; // "[]";
    private static final Color BOX_TEXT_COLOR = BLUE;


    public SokobanMainFieldViewJfx( BaseModel baseModel, Game game) {
        super( baseModel, game);
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

    @Override
    public void initOnKeyEventHandlerOnScene( ) {
        getScene( ).setOnKeyPressed( event -> {
            onKeyPress( event.getCode( ));
            // System.out.println( event);
        });
    }

    public void onKeyPress( KeyCode keyCode) {
        if ( getSokobanModel( ).getGameStatus( ) != GameStatus.GAME) {
            game.initialize( );
            return;
        }

        if        ( keyCode.equals( KeyCode.Q))          { getSokobanModel( ).moveUndo( );
        } else if ( keyCode.equals( KeyCode.P))          { getSokobanModel( ).moveRedo( );
        } else if ( keyCode.equals( KeyCode.LEFT))       { getSokobanModel( ).move( LEFT);
        } else if ( keyCode.equals( KeyCode.RIGHT))      { getSokobanModel( ).move( RIGHT);
        } else if ( keyCode.equals( KeyCode.UP))         { getSokobanModel( ).move( UP);
        } else if ( keyCode.equals( KeyCode.DOWN))       { getSokobanModel( ).move( DOWN);
        } else if ( keyCode.equals( KeyCode.BACK_SPACE)) { getSokobanModel( ).prevLevel( );
        } else if ( keyCode.equals( KeyCode.SPACE))      { getSokobanModel( ).nextLevel( );
        } else if ( keyCode.equals( KeyCode.ESCAPE))     { getSokobanModel( ).restart( );
        }
    }

    private SokobanModel getSokobanModel( ) {
        return ( SokobanModel)baseModel;
    }
}