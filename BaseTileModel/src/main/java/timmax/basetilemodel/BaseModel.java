package timmax.basetilemodel;

import timmax.basetilemodel.gameevent.GameEvent;
import timmax.basetilemodel.gameevent.GameEventNewGame;

import java.util.HashMap;
import java.util.Map;

// Базовая модель
public abstract class BaseModel implements ObservableModel {
    private final static int MIN_WIDTH = 2;
    private final static int MAX_WIDTH = 100;
    private final static int MIN_HEIGHT = 2;
    private final static int MAX_HEIGHT = 100;

    // Карта представление - очередь. В очереди записываем события. Для представлений вызываем update.
    private final Map< View, GameQueueForOneView> mapOfViewGameQueueForOneView;

    private int width;
    private int height;
    protected GameStatus gameStatus;


    public BaseModel( ) {
        mapOfViewGameQueueForOneView = new HashMap< >( );
    }

    abstract public void createNewGame( );

    protected void createNewGame( int width, int height) {
        validateWidthHeight( width, height);
        gameStatus = GameStatus.GAME;
        this.width = width;
        this.height = height;

        for ( GameQueueForOneView queue: mapOfViewGameQueueForOneView.values( )) {
            queue.add( new GameEventNewGame( width, height));
        }
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
        mapOfViewGameQueueForOneView.put( view, new GameQueueForOneView( ));
    }

    // Известить все присоединённые представления, чтобы они могли обновить себя.
    @Override
    public void notifyViews( ) {
        for ( View view: mapOfViewGameQueueForOneView.keySet( )) {
            view.update( );
        }
    }

    public GameStatus getGameStatus( ) {
        return gameStatus;
    }

    public GameEvent getNextGameEventForView(View view) {
        return ( GameEvent) mapOfViewGameQueueForOneView.get( view).queue.peek( );
    }
}