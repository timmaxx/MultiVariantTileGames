package timmax.tilegame.basemodel;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import java.util.LinkedList;
import java.util.Queue;

public class GameQueueForOneView {
    private TransportModelFromModelToViews transportModelFromModelToViews;
    private Queue< GameEvent> queue = new LinkedList< >( );


    public GameQueueForOneView( TransportModelFromModelToViews transportModelFromModelToViews) {
        this.transportModelFromModelToViews = transportModelFromModelToViews;
    }

    public boolean add( GameEvent gameEvent) {
        boolean result = queue.add( gameEvent);
        if ( !result) {
            return false;
        }
        transportModelFromModelToViews.send( gameEvent);
        return true;
    }

    public GameEvent remove( ) {
        return queue.remove( );
    }
}