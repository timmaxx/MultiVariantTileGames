package timmax.tilegameenginejfx;

import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.*;
import java.util.NoSuchElementException;

abstract public class ViewMainFieldJfx extends ViewJfx {
    private final boolean showGrid = true;
    private final boolean showCoordinates = false;

    protected GameStackPane[ ][ ] cells;
    protected int cellSize;


    public ViewMainFieldJfx( BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        super( baseModel, gameStackPaneController);
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
        getChildren( ).removeAll( getChildren( ));

        int width = gameEventNewGame.getWidth( );
        int height = gameEventNewGame.getHeight( );
        cellSize = Math.min( Game.APP_WIDTH / width, Game.APP_HEIGHT / height);

        cells = new GameStackPane[ height][ width];
        for ( int y = 0; y < height; y++) {
            for ( int x = 0; x < width; x++) {
                GameStackPane cell = new GameStackPane( x, y, cellSize, showGrid, showCoordinates);
                cells[ y][ x] = cell;
                initOnMouseClickEventHandlerOnCell( cell);
                drawCellDuringInitMainField( cell);
                getChildren( ).add( cell);
            }
        }
        // Не сильно красивое решение, нужное для того, чтобы установить ширину окна приложения
        // исходя из ширины главного игрового поля.
        // ToDo: Правильным было-бы:
        //  после того, как все Node привяжутся к корню, взять ширину и высоту скомпонованных в дерево узлов и сделать
        //  соответствующую ширину и высоту окна приложения.
        //  Т.е. делать это нужно не в этом классе, а, например, в Game в start() после
        //  root.getChildren( ).addAll( nodeList);
        //  А может ещё проще - через компоновку.
        getParent( ).getScene( ).getWindow( ).setWidth( cellSize * width + 17);
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

    public void initOnMouseClickEventHandlerOnCell( GameStackPane cell) {
        if ( gameStackPaneController == null) {
            return;
        }
        cell.setOnMouseClicked( event -> {
            int x = ( ( GameStackPane)event.getSource( )).getX( );
            int y = ( ( GameStackPane)event.getSource( )).getY( );
            switch ( event.getButton( )) {
                case PRIMARY -> gameStackPaneController.onMousePrimaryClick( x, y);
                case SECONDARY -> gameStackPaneController.onMouseSecondaryClick( x, y);
            }
        });
    }
}