package timmax.sokoban.model.gameevent;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public class GameEventSokobanVariableParamsCountOfSteps extends GameEvent {
    // ToDo: Разобраться и удалить ведущий '\n'
    public final static String COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_STEPS = "\nVariable settings - Count of steps = ";

    private final int countOfSteps;


    public GameEventSokobanVariableParamsCountOfSteps( int countOfSteps) {
        this.countOfSteps = countOfSteps;
    }

    public int getCountOfSteps( ) {
        return countOfSteps;
    }
}