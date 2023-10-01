package timmax.sokoban.model.gameevent;

import timmax.basetilemodel.gameevent.GameEvent;

public class GameEventSokobanVariableParamsCountOfBoxesInHouses extends GameEvent {
    private final int countOfBoxesInHouses;

    public GameEventSokobanVariableParamsCountOfBoxesInHouses( int countOfBoxesInHouses) {
        this.countOfBoxesInHouses = countOfBoxesInHouses;
    }

    public int getCountOfBoxesInHouses( ) {
        return countOfBoxesInHouses;
    }
}