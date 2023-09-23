package timmax.tilegameenginejfx;

import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.*;
import java.util.NoSuchElementException;

abstract public class ViewMainFieldJfx extends ViewJfx {
    protected GameStackPane[ ][ ] cells;
    protected int cellSize;

    private final boolean showGrid = true;
    private final boolean showCoordinates = false;


    public ViewMainFieldJfx( BaseModel baseModel) {
        super( baseModel);
    }

    @Override
    public void update( ) {
        GameEvent gameEvent;
        while ( true) {
            try {
                gameEvent = removeFromGameQueueForOneView( );
            } catch ( NoSuchElementException nsee) {
                break;
            }

            if ( gameEvent instanceof GameEventNewGame) {
                initMainField( ( GameEventNewGame) gameEvent);
            } else if ( gameEvent instanceof GameEventOneTile) {
                drawCellDuringGame( ( ( GameEventOneTile) gameEvent));
            }
        }
    }

    private void initMainField( GameEventNewGame gameEventNewGame) {
        int width = gameEventNewGame.getWidth( );
        int height = gameEventNewGame.getHeight( );
        cellSize = Math.min( Game.APP_WIDTH / width, Game.APP_HEIGHT / height);

        // primaryStage.hide( );
/*
        root.setPrefSize(
                width * cellSize + GameBorderImage.getPaddingSide( ) + GameBorderImage.getPaddingSide( ),
                height * cellSize + GameBorderImage.getPaddingTop( ) + GameBorderImage.getPaddingDown( )
        );
*/
        cells = new GameStackPane[ height][ width];
        for( int y = 0; y < height; ++y) {
            for( int x = 0; x < width; ++x) {
                cells[ y][ x] = new GameStackPane( x, y, cellSize, showGrid, showCoordinates);
                initOnMouseClickEventHandlerOnCell( cells[ y][ x]);
                drawCellDuringInitMainField( cells[ y][ x]);
                getChildren( ).add( cells[ y][ x]);
            }
        }
        initOnKeyEventHandlerOnScene( );
        // primaryStage.show( );
    }

    protected void initOnKeyEventHandlerOnScene( ) {
    }

    protected void initOnMouseClickEventHandlerOnCell( GameStackPane cell) {
    }

    protected void drawCellDuringInitMainField( GameStackPane cell) {
    }

    protected void drawCellDuringGame( GameEventOneTile gameEventOneTile) {
    }

    protected GameStackPane getCellByGameEventOneTile( GameEventOneTile gameEventOneTile) {
        int x = gameEventOneTile.getX( );
        int y = gameEventOneTile.getY( );
        return cells[ y][ x];
    }
}