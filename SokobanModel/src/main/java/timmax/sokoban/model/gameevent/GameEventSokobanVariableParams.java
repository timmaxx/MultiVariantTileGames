package timmax.sokoban.model.gameevent;

import timmax.basetilemodel.gameevent.GameEvent;

public class GameEventSokobanVariableParams extends GameEvent {
    private final int countOfSteps;
    private final int countOfBoxInHouses;

    public GameEventSokobanVariableParams( int countOfSteps, int countOfBoxInHouses) {
        this.countOfSteps = countOfSteps;
        this.countOfBoxInHouses = countOfBoxInHouses;
    }

    public int getCountOfSteps( ) {
        return countOfSteps;
    }

    public int getCountOfBoxInHouses( ) {
        return countOfBoxInHouses;
    }
}