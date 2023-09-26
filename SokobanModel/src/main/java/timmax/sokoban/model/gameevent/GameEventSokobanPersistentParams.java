package timmax.sokoban.model.gameevent;

import timmax.basetilemodel.gameevent.GameEvent;

public class GameEventSokobanPersistentParams extends GameEvent {
    private final int countOfBoxesAndHomes;

    public GameEventSokobanPersistentParams( int countOfBoxesAndHomes) {
        this.countOfBoxesAndHomes = countOfBoxesAndHomes;
    }

    public int getCountOfBoxesAndHomes( ) {
        return countOfBoxesAndHomes;
    }
}