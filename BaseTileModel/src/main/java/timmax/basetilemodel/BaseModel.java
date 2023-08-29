package timmax.basetilemodel;

import java.util.ArrayList;

// Базовая модель
public abstract class BaseModel implements ObservableModel {
    private final static int MIN_WIDTH = 2;
    private final static int MAX_WIDTH = 100;
    private final static int MIN_HEIGHT = 2;
    private final static int MAX_HEIGHT = 100;
    private ArrayList< View> listOfViews; // Список представлений, которые нужно обновлять при изменении в модели.
    private int width;
    private int height;
    protected GameStatus gameStatus;

    private void createNewGame( ) {
        listOfViews = new ArrayList< >( );
        gameStatus = GameStatus.GAME;
    }

    protected void createNewGame( int width, int height) {
        validateWidthHeight( width, height);
        createNewGame( );
        this.width = width;
        this.height = height;
    }

    private static void validateWidthHeight( int width, int height) {
        if ( width >= MIN_WIDTH && width <= MAX_WIDTH && height >= MIN_HEIGHT && height <= MAX_HEIGHT) {
            return;
        }
        throw new RuntimeException(
                "It must be width >= " + MIN_WIDTH + " && width <= " + MAX_WIDTH +
                        " && height >= " + MIN_HEIGHT + " && height <= " + MAX_HEIGHT +
                        ". But width = " + width + ", height = " + height + ".");
    }

    public int getWidth( ) {
        return width;
    }

    public int getHeight( ) {
        return height;
    }

    // Реализация добавления представления в модель
    @Override
    public void addViewListener( View view) {
        listOfViews.add( view);
    }

    // Известить все присоединённые представления, чтобы они могли обновить себя.
    @Override
    public void notifyViews( ) {
        for ( View view: listOfViews) {
            view.update( );
        }
    }

    public GameStatus getGameStatus( ) {
        return gameStatus;
    }
}