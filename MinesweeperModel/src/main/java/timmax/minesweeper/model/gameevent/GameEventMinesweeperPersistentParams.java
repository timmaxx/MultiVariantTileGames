package timmax.minesweeper.model.gameevent;

import timmax.basetilemodel.gameevent.GameEventROTextFields;

public class GameEventMinesweeperPersistentParams extends GameEventROTextFields {
    // Предполагалось, что commonLabel должна быть объявлена в GameEventROTextFields,
    // а здесь её переопределить, но для static переменных это не работает.
    // Но commonLabel и не должно быть не static!
    // Текущее решение не красивое. Т.к. в каждом потомке GameEventROTextFields заново объявлено commonLabel.
    // ToDo: Нужно найти более правильное архитектурное решение.
    public static final String commonLabel = "\nPersistent settings for Minesweeper\n"; // ToDo: Разобраться и удалить ведущий '\n' в commonLabel.

    private final int countOfMines;


    public GameEventMinesweeperPersistentParams( int countOfMines) {
        this.countOfMines = countOfMines;
    }

    public int getCountOfMines( ) {
        return countOfMines;
    }
}