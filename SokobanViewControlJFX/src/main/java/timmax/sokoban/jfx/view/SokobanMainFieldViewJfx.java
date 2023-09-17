package timmax.sokoban.jfx.view;

import timmax.basetilemodel.*;
import timmax.basetilemodel.gameevent.*;
import timmax.basetilemodel.tile.Tile;
import timmax.sokoban.model.SokobanModel;
import timmax.sokoban.model.gameevent.*;
import timmax.sokoban.model.gameobject.*;

import javafx.scene.paint.Color;
import timmax.tilegameenginejfx.*;

import java.util.List;
import java.util.NoSuchElementException;

import static javafx.scene.paint.Color.*;

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

    @Override
    public void update( ) {
        GameEvent gameEvent;
        while ( true) {
            try {
                // Единственное место, где обращение к модели можно оставить.
                // Или даже не к модели, а к очереди, которая внутри модели, привязана к этому представлению.
                gameEvent = baseModel.getNextGameEventForView( this);
            } catch ( NoSuchElementException nsee) {
                break;
            }

            if ( gameEvent instanceof GameEventNewGame) {
                // ToDo: Свойства модели нужно брать не напрямую из модели, а из события!
                cellSize = Math.min( Game.APP_WIDTH / baseModel.getWidth( ), Game.APP_HEIGHT / baseModel.getHeight( ));

                primaryStage.hide( );
                root.setPrefSize(
                        baseModel.getWidth( ) * cellSize + GameBorderImage.getPaddingSide( ) + GameBorderImage.getPaddingSide( ),
                        baseModel.getHeight( ) * cellSize + GameBorderImage.getPaddingTop( ) + GameBorderImage.getPaddingDown( )
                );

                cells = new GameStackPane[ baseModel.getHeight( )][ baseModel.getWidth( )];

                for( int y = 0; y < baseModel.getHeight( ); ++y) {
                    cells[ y] = new GameStackPane[ baseModel.getWidth( )];
                    for( int x = 0; x < baseModel.getWidth( ); ++x) {
                        cells[ y][ x] = new GameStackPane( x, y, cellSize, true, false);
                        drawTile( x, y);
                        root.getChildren( ).add( cells[ y][ x]);
                    }
                }
                primaryStage.show( );
            } else if ( gameEvent instanceof GameEventPlayerMoved) {
                int oldX = ( ( GameEventPlayerMoved) gameEvent).getOldX( );
                int oldY = ( ( GameEventPlayerMoved) gameEvent).getOldY( );
                int newX = ( ( GameEventPlayerMoved) gameEvent).getNewX( );
                int newY = ( ( GameEventPlayerMoved) gameEvent).getNewY( );
                if ( gameEvent instanceof GameEventPlayerWithBoxMoved) {
                    int newBoxX = ( ( GameEventPlayerWithBoxMoved) gameEvent).getNewBoxX( );
                    int newBoxY = ( ( GameEventPlayerWithBoxMoved) gameEvent).getNewBoxY( );
                    drawTile( newBoxX, newBoxY);
                }
                drawTile( newX, newY);
                drawTile( oldX, oldY);
            }
        }
    }

    private void drawTile( int x, int y) {
        // ToDo: Свойства модели нужно брать не напрямую из модели, а из события!
        List< Tile> listOfTile = ( ( SokobanModel)( baseModel)).getListOfXY( x, y);
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