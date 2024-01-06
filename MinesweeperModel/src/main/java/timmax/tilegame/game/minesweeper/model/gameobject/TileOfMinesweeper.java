package timmax.tilegame.game.minesweeper.model.gameobject;

import java.util.Set;

import timmax.tilegame.basemodel.tile.Tile;

// Плитка игры Сапёр
public class TileOfMinesweeper extends Tile {
    private final boolean isMine; // Есть-ли мина на плитке

    private boolean isOpen;       // Открыта-ли плитка
    private boolean isFlag;       // Есть-ли флаг на плитке
    private Set<TileOfMinesweeper> neighborTiles; // Соседние плитки
    private int countOfMineNeighbors = -1; // Количество заминированных плиток-соседей
    // Начальное значение -1 говорит о том, что значение заминированных плиток-соседей ещё не рассчитано.

    public TileOfMinesweeper(int x, int y, boolean isMine) {
        super(x, y);
        this.isMine = isMine;
    }

    public boolean isOpen() {
        return isOpen;
    }

    // Сколько мин в соседних плитках
    public int getCountOfMineNeighbors() {
        if (!isOpen) {
            // Нельзя спрашивать, если текущая плитка не открыта!
            throw new RuntimeException("It is a secret! (getCountMineNeighbors)");
        }
        if (countOfMineNeighbors >= 0) {
            // Если ранее первичный расчёт был, то значение запомнено
            return countOfMineNeighbors;
        }

        // Первичный расчёт количества мин в соседних плитках
        countOfMineNeighbors = 0;
        for (TileOfMinesweeper tileOfMinesweeper : getNeighborTiles()) {
            if (tileOfMinesweeper.isMine) {
                countOfMineNeighbors++;
            }
        }

        return countOfMineNeighbors;
    }

    public boolean isFlag() {
        if (isOpen()) {
            // Нельзя спрашивать про флаг, если текущая плитка уже открыта!
            throw new RuntimeException("Tile is open. For open tile flag is absent! (isFlag)");
        }

        return isFlag;
    }

    public boolean isMine() {
        if (!isOpen()) {
            // Нельзя спрашивать про наличие мины, если текущая плитка не открыта!
            throw new RuntimeException("It is a secret! (isMine)");
        }

        return isMine;
    }


    // Package --------------------------------------------------------------------------------------------------------

    // Даёт ответ на то, есть мина или нет.
    // Определена специально как package, что-бы её нельзя было вызвать из другого пакета и
    //   тем самым несанкционированно (т.е. для закрытой плитки) раскрыть информацию о наличии/отсутствии мины!
    boolean isMineInPackageMode() {
        return isMine;
    }

    // Открыть плитку
    void open() {
        isOpen = true;
    }

    // Инвертировать плитку
    void inverseFlag() {
        if (isOpen()) {
            // Инвертировать флаг можно только для закрытой плитки
            throw new RuntimeException("Tile is open. For open tile flag is absent! (inverseFlag)");
        }
        this.isFlag = !isFlag;
    }

    // Предоставить перечень соседних плиток
    Set<TileOfMinesweeper> getNeighborTiles() {
        return neighborTiles;
    }

    // Инициализировать перечень соседних плиток
    void setNeighborTiles(Set<TileOfMinesweeper> neighborTiles) {
        this.neighborTiles = neighborTiles;
    }
}
