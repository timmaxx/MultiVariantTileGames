package timmax.sokoban.model.gameevent;

import timmax.basetilemodel.gameevent.GameEvent;

public class GameEventSokobanVariableParamsCountOfSteps extends GameEvent {
    private final int countOfSteps;

    public GameEventSokobanVariableParamsCountOfSteps( int countOfSteps) {
        this.countOfSteps = countOfSteps;
    }

    public int getCountOfSteps( ) {
        return countOfSteps;
    }
}