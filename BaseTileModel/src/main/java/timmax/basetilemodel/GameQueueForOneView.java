package timmax.basetilemodel;

import timmax.basetilemodel.gameevent.GameEvent;
import java.util.LinkedList;
import java.util.Queue;

public class GameQueueForOneView {
    Queue< GameEvent> queue = new LinkedList< >( );

    public boolean add( GameEvent gameEvent) {
        return queue.add( gameEvent);
    }

    public GameEvent remove( ) {
        return queue.remove( );
    }
}