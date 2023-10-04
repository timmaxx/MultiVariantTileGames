package timmax.minesweeper.model.gameevent;

import timmax.basetilemodel.gameevent.GameEventROTextFields;

public class GameEventMinesweeperVariableParamsOpenClose extends GameEventROTextFields {
    // Предполагалось, что commonLabel должна быть объявлена в GameEventROTextFields,
    // а здесь её переопределить, но для static переменных это не работает.
    // Но commonLabel и не должно быть не static!
    // Текущее решение не красивое. Т.к. в каждом потомке GameEventROTextFields заново объявлено commonLabel.
    // ToDo: Нужно найти более правильное архитектурное решение.
    public static final String commonLabel = "\nVariable settings - open and close tiles:\n"; // ToDo: Разобраться и удалить ведущий '\n' в commonLabel.

    private final int tilesWereOpened;
    private final int tilesAreStillClose;


    public GameEventMinesweeperVariableParamsOpenClose( int tilesWereOpened, int tilesAreStillClose) {
        this.tilesWereOpened = tilesWereOpened;
        this.tilesAreStillClose = tilesAreStillClose;
    }

    public int getTilesWereOpened( ) {
        return tilesWereOpened;
    }

    public int getTilesAreStillClose( ) {
        return tilesAreStillClose;
    }

}