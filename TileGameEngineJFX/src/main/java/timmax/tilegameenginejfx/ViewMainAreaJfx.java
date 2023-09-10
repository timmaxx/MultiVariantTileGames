package timmax.tilegameenginejfx;

import javafx.scene.layout.Pane;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.ViewMainArea;

public abstract class ViewMainAreaJfx extends ViewMainArea {
    protected GameStackPane[ ][ ] cells;
    protected int cellSize;
    boolean showGrid;
    boolean showCoordinates;


    public ViewMainAreaJfx( BaseModel baseModel, Pane root) {
        super( baseModel);

        cellSize = Math.min( Game.APP_WIDTH / baseModel.getWidth( ), Game.APP_HEIGHT / baseModel.getHeight( ));
        showGrid = true;
        showCoordinates = false;

        cells = new GameStackPane[ baseModel.getHeight( )][ baseModel.getWidth( )];
        for( int y = 0; y < baseModel.getHeight( ); ++y) {
            for( int x = 0; x < baseModel.getWidth( ); ++x) {
                cells[ y][ x] = new GameStackPane( x, y, cellSize, showGrid, showCoordinates);
                root.getChildren( ).add( cells[ y][ x]);
            }
        }
    }
}