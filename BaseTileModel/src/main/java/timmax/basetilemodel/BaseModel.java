package timmax.basetilemodel;

import timmax.basetilemodel.gameevent.GameEvent;
import timmax.basetilemodel.gameevent.GameEventNewGame;
import java.util.HashMap;
import java.util.Map;

// Базовая модель
public abstract class BaseModel {
    private final static int MIN_WIDTH = 2;
    private final static int MAX_WIDTH = 100;
    private final static int MIN_HEIGHT = 2;
    private final static int MAX_HEIGHT = 100;

    // Карта представление - очередь. В очереди записываем события. Для представлений вызываем update.
    protected final Map< ViewInterface, GameQueueForOneView> mapOfViewGameQueueForOneView;
    private GameStatus gameStatus;


    public BaseModel( ) {
        mapOfViewGameQueueForOneView = new HashMap< >( );
    }

    abstract public void createNewGame( );

    protected void createNewGame( int width, int height) {
        validateWidthHeight( width, height);
        gameStatus = GameStatus.GAME;
        addGameEventIntoQueueAndNotifyViews( new GameEventNewGame( width, height));
    }

    protected void addGameEventIntoQueueAndNotifyViews( GameEvent gameEvent) {
        addGameEventIntoQueue( gameEvent);
        notifyViews( );
    }

    public void addGameEventIntoQueue( GameEvent gameEvent) {
        for ( GameQueueForOneView queue: mapOfViewGameQueueForOneView.values( )) {
            queue.add( gameEvent);
        }
    }

    public void notifyViews( ) {
        for ( ViewInterface view: mapOfViewGameQueueForOneView.keySet( )) {
            view.update( );
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

    // Реализация добавления представления в модель
    public GameQueueForOneView addViewListener( ViewInterface view) {
        GameQueueForOneView gameQueueForOneView = new GameQueueForOneView( );
        mapOfViewGameQueueForOneView.put( view, gameQueueForOneView);
        return gameQueueForOneView;
    }

    // Сейчас используется в контроллерах.
    // ToDo: исключить из использования в контроллерах. Перенести исключённую логику в модель.
    public GameStatus getGameStatus( ) {
        return gameStatus;
    }

    protected void setGameStatus( GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    // ToDo: из SokobanModel взять реализации этих методов, дополнить абстракциями и внести сюда.
    abstract protected void restart( );

    abstract protected void nextLevel( );

    abstract protected void prevLevel( );

    abstract protected void win( );
}