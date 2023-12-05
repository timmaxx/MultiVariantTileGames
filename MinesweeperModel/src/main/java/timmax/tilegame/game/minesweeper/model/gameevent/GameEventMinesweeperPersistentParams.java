package timmax.tilegame.game.minesweeper.model.gameevent;

import timmax.tilegame.basemodel.gameevent.GameEventROTextFields;

public class GameEventMinesweeperPersistentParams extends GameEventROTextFields {
    // ToDo: Разобраться и удалить ведущий '\n';
    public final static String COMMON_LABEL_OF_PERSISTENT_PARAMS = "\nPersistent settings for Minesweeper\n";
    public final static String COUNT_OF_MINES = " Count of all mines in the field = ";

    private final int countOfMines;


    public GameEventMinesweeperPersistentParams(int countOfMines) {
        this.countOfMines = countOfMines;
    }

    public int getCountOfMines() {
        return countOfMines;
    }
}