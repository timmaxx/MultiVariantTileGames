package timmax.tilegame.basemodel;

import java.util.HashMap;
import java.util.Map;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.GameCommandQueueOfModel;
import timmax.tilegame.transport.GameEventQueue;

// Базовая модель
public abstract class BaseModel {
    private final static int MIN_WIDTH = 2;
    private final static int MAX_WIDTH = 100;
    private final static int MIN_HEIGHT = 2;
    private final static int MAX_HEIGHT = 100;

    // Карта представление - очередь. В очереди записываем события. Для представлений вызываем update.
    protected final Map< View, GameEventQueue> mapOfViewGameQueue;
    private GameStatus gameStatus;

    private final GameCommandQueueOfModel gameCommandQueueOfModel;


    public BaseModel( ) {
        mapOfViewGameQueue = new HashMap< >( );
        gameCommandQueueOfModel = new GameCommandQueueOfModel( this);
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
        for ( GameEventQueue queue: mapOfViewGameQueue.values( )) {
            queue.add( gameEvent);
        }
    }

    public void notifyViews( ) {
        for ( View view: mapOfViewGameQueue.keySet( )) {
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


    public GameEventQueue addViewListener( View view) {
        GameEventQueue gameEventQueue = new GameEventQueue( );
        mapOfViewGameQueue.put( view, gameEventQueue);
        return gameEventQueue;
    }

    protected GameStatus getGameStatus( ) {
        return gameStatus;
    }

    protected void setGameStatus( GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    abstract protected void restart( );

    abstract protected void nextLevel( );

    abstract protected void prevLevel( );

    abstract protected void win( );

    protected boolean verifyGameStatusNotGameAndMayBeCreateNewGame( ) {
        if ( getGameStatus( ) != GameStatus.GAME) {
            createNewGame( );
            return true;
        }
        return false;
    }

    public void addCommandIntoQueue( GameCommand gameCommand) {
        gameCommandQueueOfModel.add( gameCommand);
    }
}