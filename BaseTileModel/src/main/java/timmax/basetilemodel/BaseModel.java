package timmax.basetilemodel;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public abstract class BaseModel< T> implements ObservableModel {
    private ArrayList< View> arrayListOfViews;
    private int width;
    private int height;
    private T[ ][ ] tiles;
    protected GameStatus gameStatus;

    private void createNewGame( ) {
        arrayListOfViews = new ArrayList< >( );
        gameStatus = GameStatus.GAME;
    }

    protected void createNewGame( Class type, int width, int height) {
        validateWidthHeight( width, height);
        createNewGame( );
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

    private static void validateWidthHeight( int width, int height) {
        if ( width > 2 && width < 101 && height > 2 && height < 101) {
            return;
        }
        throw new RuntimeException(
                "It must be width > 2 && width < 101 && height > 2 && height < 101. But width = " + width + ", height = " + height + ".");
    }

    protected void createNewGame( T[ ][ ] tiles) {
        createNewGame( );
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
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}