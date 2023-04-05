package timmax.basetilemodel;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public abstract class BaseModel< T> implements ObservableModel {
    private final int width;
    private final int height;
    private final T[ ][ ] tiles;

    private final ArrayList< View> arrayListOfViews;

    public BaseModel( Class type, int width, int height) {
        arrayListOfViews = new ArrayList< >( );
        this.width = width;
        this.height = height;
        tiles = ( T[ ][ ]) java.lang.reflect.Array.newInstance( type, height, width);
        for ( int y = 0; y < height; y++) {
            for ( int x = 0; x < width; x++) {
                try {
                    tiles[ y][ x] = ( T) type.getDeclaredConstructor( ).newInstance( ); // т.к. нельзя сделать = new T( );
                } catch (   InstantiationException
                          | NoSuchMethodException
                          | InvocationTargetException
                          | IllegalAccessException e) {
                    throw new RuntimeException( e);
                }
            }
        }
    }

    public BaseModel( T[ ][ ] tiles) {
        arrayListOfViews = new ArrayList< >( );
        this.width = tiles[ 0].length;
        this.height = tiles.length;
        this.tiles = tiles;
    }

    public int getWidth( ) {
        return width;
    }

    public int getHeight( ) {
        return height;
    }

    public T getTileByXY( int x, int y) {
        return tiles[ y][ x];
    }

    @Override
    public void addViewListener( View view) {
        arrayListOfViews.add( view);
    }

    @Override
    public void notifyViews( ) {
        for ( View view: arrayListOfViews) {
            view.updateAllTiles( );
        }
        toDoAfterNotifyViews( );
    }

    public void toDoAfterNotifyViews( ) {
    }
}