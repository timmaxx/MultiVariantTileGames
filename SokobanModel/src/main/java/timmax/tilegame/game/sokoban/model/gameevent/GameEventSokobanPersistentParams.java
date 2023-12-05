package timmax.tilegame.game.sokoban.model.gameevent;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public class GameEventSokobanPersistentParams extends GameEvent {
    // ToDo: Разобраться и удалить ведущий '\n'
    public final static String COMMON_LABEL_OF_PERSISTENT_PARAMS = "\nPersistent settings for Sokoban\n";
    public final static String COUNT_OF_BOXES_AND_HOMES = " Count of all boxes and homes = ";

    private final int countOfBoxesAndHomes;


    public GameEventSokobanPersistentParams(
            int countOfBoxesAndHomes) {
        this.countOfBoxesAndHomes = countOfBoxesAndHomes;
    }

    public int getCountOfBoxesAndHomes() {
        return countOfBoxesAndHomes;
    }
}