package timmax.tilegame.transport;

import java.util.LinkedList;
import java.util.Queue;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public class GameEventQueue {
    Queue<GameEvent> eventQueue = new LinkedList<>();

    public boolean add(GameEvent gameEvent) {
        return eventQueue.add(gameEvent);
    }

    public GameEvent remove() {
        return eventQueue.remove();
    }
}