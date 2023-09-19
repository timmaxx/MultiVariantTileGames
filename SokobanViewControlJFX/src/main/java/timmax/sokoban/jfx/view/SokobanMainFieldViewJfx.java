package timmax.sokoban.jfx.view;

import timmax.basetilemodel.*;
import timmax.basetilemodel.gameevent.*;
import timmax.sokoban.model.gameevent.*;
import timmax.sokoban.model.gameobject.*;

import javafx.scene.paint.Color;
import timmax.tilegameenginejfx.*;

import java.util.NoSuchElementException;

import static javafx.scene.paint.Color.*;
import static timmax.sokoban.model.gameobject.WhoMovableInTile.*;
import static timmax.sokoban.model.gameobject.WhoPersistentInTile.*;

public class SokobanMainFieldViewJfx extends ViewJfx {
    private static final Color WALL_CELL_COLOR = RED;
    private static final Color HOME_CELL_COLOR = WHITE;
    private static final Color EMPTY_CELL_COLOR = BLACK;

    private static final String PLAYER = "😀"; // "\uF9CD"; // "&";
    private static final Color PLAYER_TEXT_COLOR = GREEN;

    private static final String BOX = "█"; // "❐"; // "▉"; // "[]";
    private static final Color BOX_TEXT_COLOR = BLUE;


    public SokobanMainFieldViewJfx( BaseModel baseModel) {
        super( baseModel);
    }

    // Почти идентичное содержимое этого метода с одноименным методом в MinesweeperMainFieldViewJfx.
    // ToDo:
    //  1. Перенести в родительский класс.
    //  2. И там через абстракцию разрулить неодинаковость.
    @Override
    public void update( ) {
        while ( true) {
            GameEvent gameEvent;
            try {
                // Единственное место, где обращение к модели можно оставить.
                // Или даже не к модели, а к очереди, которая внутри модели, привязана к этому представлению.
                gameEvent = baseModel.getNextGameEventForView( this);
            } catch ( NoSuchElementException nsee) {
                break;
            }

            if ( gameEvent instanceof GameEventNewGame) {
                initMainField( ( GameEventNewGame) gameEvent);
            } else if ( gameEvent instanceof GameEventOneTileSokobanChangeable) {
                drawTile( ( ( GameEventOneTileSokobanChangeable) gameEvent));
            }
        }
    }

    private void initMainField( GameEventNewGame gameEventNewGame) {
        int width = gameEventNewGame.getWidth( );
        int height = gameEventNewGame.getHeight( );
        cellSize = Math.min( Game.APP_WIDTH / width, Game.APP_HEIGHT / height);

        primaryStage.hide( );
        root.setPrefSize(
                width * cellSize + GameBorderImage.getPaddingSide( ) + GameBorderImage.getPaddingSide( ),
                height * cellSize + GameBorderImage.getPaddingTop( ) + GameBorderImage.getPaddingDown( )
        );

        cells = new GameStackPane[ height][ width];

        for( int y = 0; y < height; ++y) {
            cells[ y] = new GameStackPane[ baseModel.getWidth( )];
            for( int x = 0; x < width; ++x) {
                cells[ y][ x] = new GameStackPane( x, y, cellSize, true, false);
                {
                    // См. комментарии к одноименному методу в классе MinesweeperMainFieldViewJfx
                }
                root.getChildren( ).add( cells[ y][ x]);
            }
        }
        primaryStage.show( );
    }

    // ToDo: drawTile в родительский класс и пусть там будет абстрактной. А здесь будет реализация.
    private void drawTile( GameEventOneTileSokobanChangeable gameEventOneTileSokobanChangeable) {
        int x = gameEventOneTileSokobanChangeable.getX( );
        int y = gameEventOneTileSokobanChangeable.getY( );
        GameStackPane cell = cells[ y][ x];

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