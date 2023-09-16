package timmax.basetilemodel;

import timmax.basetilemodel.gameevent.GameEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class GameQueueForOneView implements Queue< GameEvent> {
    Queue< GameEvent> queue = new LinkedList< >( );

    @Override
    public int size( ) {
        return queue.size( );
    }

    @Override
    public boolean isEmpty( ) {
        return queue.isEmpty( );
    }

    @Override
    public boolean contains( Object o) {
        return queue.contains( o);
    }

    @Override
    public Iterator< GameEvent> iterator( ) {
        return queue.iterator( );
    }

    @Override
    public Object[ ] toArray( ) {
        return queue.toArray( new Object[ 0]);
    }

    @Override
    public Object[ ] toArray( Object[ ] a) {
        return queue.toArray( a);
    }

    @Override
    public boolean remove( Object o) {
        return queue.remove( o);
    }

    @Override
    public boolean addAll( Collection c) {
        return queue.addAll( c);
    }

    @Override
    public void clear( ) {
        queue.clear( );
    }

    @Override
    public boolean retainAll( Collection c) {
        return queue.retainAll( c);
    }

    @Override
    public boolean removeAll( Collection c) {
        return queue.removeAll( c);
    }

    @Override
    public boolean containsAll( Collection c) {
        return queue.containsAll( c);
    }

    @Override
    public boolean add( GameEvent gameEvent) {
        return queue.add( gameEvent);
    }

    @Override
    public boolean offer( GameEvent gameEvent) {
        return queue.offer( gameEvent);
    }

    @Override
    public GameEvent remove( ) {
        return queue.remove( );
    }

    @Override
    public GameEvent poll( ) {
        return queue.poll( );
    }

    @Override
    public GameEvent element( ) {
        return queue.element( );
    }

    @Override
    public GameEvent peek( ) {
        return queue.peek( );
    }
}