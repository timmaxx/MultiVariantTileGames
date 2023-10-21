package timmax.tilegame.game.sokoban.model.gameevent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import timmax.tilegame.basemodel.gameevent.GameEvent;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public class GameEventSokobanVariableParamsCountOfBoxesInHouses extends GameEvent {
    // ToDo: Разобраться и удалить ведущий '\n'
    public final static String COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_BOXES_IN_HOMES = "\nVariable settings - Count of boxes in houses = ";

    private final int countOfBoxesInHouses;


    @JsonCreator( mode = PROPERTIES)
    public GameEventSokobanVariableParamsCountOfBoxesInHouses(
            @JsonProperty( "countOfSteps") int countOfBoxesInHouses) {
        this.countOfBoxesInHouses = countOfBoxesInHouses;
    }

    public int getCountOfBoxesInHouses( ) {
        return countOfBoxesInHouses;
    }
}