package timmax.tilegame.basemodel;

import java.util.LinkedList;
import java.util.Queue;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public class GameEventQueueForOneView {
    Queue< GameEvent> queue = new LinkedList< >( );

    public boolean add( GameEvent gameEvent) {
        return queue.add( gameEvent);
    }

    public GameEvent remove( ) {
        return queue.remove( );
    }
}