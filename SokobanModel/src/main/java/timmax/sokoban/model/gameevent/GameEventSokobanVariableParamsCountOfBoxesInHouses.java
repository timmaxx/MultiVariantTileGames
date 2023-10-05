package timmax.sokoban.model.gameevent;

import timmax.basetilemodel.gameevent.GameEvent;

public class GameEventSokobanVariableParamsCountOfBoxesInHouses extends GameEvent {
    // ToDo: Разобраться и удалить ведущий '\n'
    public final static String COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_BOXES_IN_HOMES = "\nVariable settings - Count of boxes in houses = ";

    private final int countOfBoxesInHouses;


    public GameEventSokobanVariableParamsCountOfBoxesInHouses( int countOfBoxesInHouses) {
        this.countOfBoxesInHouses = countOfBoxesInHouses;
    }

    public int getCountOfBoxesInHouses( ) {
        return countOfBoxesInHouses;
    }
}