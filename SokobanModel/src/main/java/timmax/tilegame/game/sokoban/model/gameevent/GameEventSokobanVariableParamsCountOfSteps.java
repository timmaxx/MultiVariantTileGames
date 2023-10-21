package timmax.tilegame.game.sokoban.model.gameevent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import timmax.tilegame.basemodel.gameevent.GameEvent;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public class GameEventSokobanVariableParamsCountOfSteps extends GameEvent {
    // ToDo: Разобраться и удалить ведущий '\n'
    public final static String COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_STEPS = "\nVariable settings - Count of steps = ";

    private final int countOfSteps;


    @JsonCreator( mode = PROPERTIES)
    public GameEventSokobanVariableParamsCountOfSteps(
            @JsonProperty( "countOfSteps") int countOfSteps) {
        this.countOfSteps = countOfSteps;
    }

    public int getCountOfSteps( ) {
        return countOfSteps;
    }
}