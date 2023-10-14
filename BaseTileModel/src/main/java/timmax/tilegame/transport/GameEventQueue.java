package timmax.tilegame.transport;

import java.util.LinkedList;
import java.util.Queue;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public class GameEventQueue {
    Queue< GameEvent> eventQueue = new LinkedList< >( );

    public boolean add( GameEvent gameEvent) {
        return eventQueue.add( gameEvent);
    }

    public GameEvent remove( ) {
        return eventQueue.remove( );
    }
}

/*
import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.baseview.View;

public class GameEventQueue {
    Queue< GameEvent> eventQueue;
    // BaseModel baseModel;
    // View view;


    public GameEventQueue( BaseModel baseModel, View view) {
        // this.baseModel = baseModel;
        // this.view = view;
        eventQueue = new LinkedList< >( );
    }

    public boolean add( GameEvent gameEvent) {
        boolean result = eventQueue.add( gameEvent);
        if ( !result) {
            return false;
        }
        transport( );
        return true;
    }


    private void transport( ) {
        while ( size( ) != 0) {
            GameEvent gameEvent = remove( );
            // whatToDoWithEvent( gameEvent);
        }
    }

    public int size( ) {
        return eventQueue.size( );
    }

    public GameEvent remove( ) {
        GameEvent gameEvent = eventQueue.remove( );
        return gameEvent;
    }

    // protected abstract void whatToDoWithEvent( GameEvent gameEvent);
}
*/