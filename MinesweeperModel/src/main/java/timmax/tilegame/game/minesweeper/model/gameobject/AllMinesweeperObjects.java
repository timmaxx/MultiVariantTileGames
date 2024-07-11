package timmax.tilegame.game.minesweeper.model.gameobject;

import java.util.HashSet;
import java.util.Set;

import timmax.tilegame.basemodel.GameStatus;
import timmax.tilegame.basemodel.gameevent.GameEventGameOver;
import timmax.tilegame.basemodel.protocol.server.GameMatch;

import timmax.tilegame.game.minesweeper.model.gameevent.*;

import static timmax.tilegame.basemodel.GameStatus.*;

// Все объекты игры Сапёр
public class AllMinesweeperObjects<T> {
    private final int countOfMines; // Количество мин
    private final TileOfMinesweeper[][] tileOfMinesweepers; // Все плитки поля

    private int countOfFlags; // Количество оставшихся флагов, допустимых к использованию
    private int countOfClosedTiles; // Количество оставшихся закрытых плиток
    GameMatch<T> gameMatch;

    AllMinesweeperObjects(TileOfMinesweeper[][] tileOfMinesweepers, int countOfMines) {
        this.tileOfMinesweepers = tileOfMinesweepers;
        this.countOfMines = countOfMines;

        countOfClosedTiles = getWidth() * getHeight();
        countOfFlags = countOfMines;
    }

    public void setModel(GameMatch<T> gameMatch) {
        this.gameMatch = gameMatch;
    }

    public int getCountOfMines() {
        return countOfMines;
    }

    // Попробовать инвертировать флаг
    public void tryInverseFlag(TileOfMinesweeper tileOfMinesweeper) {
        // Если плитка уже открыта или (флагов больше нет и на плитке нет флага)
        if (tileOfMinesweeper.isOpen()
                || (countOfFlags == 0 && !tileOfMinesweeper.isFlag())) {
            // Не будет инверсии
            return;
        }
        tileOfMinesweeper.inverseFlag(); // Инвертируем флаг
        gameMatch.sendGameEventToAllViews(new GameEventOneTileMinesweeperChangeFlag(tileOfMinesweeper.getX(), tileOfMinesweeper.getY(), tileOfMinesweeper.isFlag()));
        if (tileOfMinesweeper.isFlag()) {
            countOfFlags--;
        } else {
            countOfFlags++;
        }
        gameMatch.sendGameEventToAllViews(new GameEventMinesweeperVariableParamsFlag(
                countOfMines - countOfFlags,
                countOfFlags
        ));
    }

    // Открыть плитку и узнать, продолжена-ли будет игра или закончена (выигрышем или проигрышем).
    public GameStatus open(TileOfMinesweeper tileOfMinesweeper) {
        if (!tileOfMinesweeper.isOpen() && tileOfMinesweeper.isFlag()) {
            return GAME;
        }
        GameStatus gameStatus = openRecursive(tileOfMinesweeper);
        gameMatch.sendGameEventToAllViews(new GameEventMinesweeperVariableParamsOpenClose(
                getWidth() * getHeight() - countOfClosedTiles,
                countOfClosedTiles
        ));
        return gameStatus;
    }

    public TileOfMinesweeper getTileByXY(int x, int y) {
        return tileOfMinesweepers[y][x];
    }


    // Private --------------------------------------------------------------------------------------------------------

    // Открыть заданную плитку, и возможно, рекурсивно ещё несколько.
    private GameStatus openRecursive(TileOfMinesweeper tileOfMinesweeper) {
        if (tileOfMinesweeper.isOpen() || tileOfMinesweeper.isFlag()) {
            return GAME;
        }
        tileOfMinesweeper.open();
        countOfClosedTiles--; // Количество закрытых плиток сделаем меньше
        // Если в открытой плитке мина
        if (tileOfMinesweeper.isMine()) {
            // Завершение игры поражением
            gameMatch.sendGameEventToAllViews(new GameEventOneTileMinesweeperOpenMine(tileOfMinesweeper.getX(), tileOfMinesweeper.getY()));
            gameMatch.sendGameEventToAllViews(new GameEventGameOver(DEFEAT));
            return DEFEAT;
        } else {
            defineNeighbors(tileOfMinesweeper);
            gameMatch.sendGameEventToAllViews(new GameEventOneTileMinesweeperOpenNoMine(tileOfMinesweeper.getX(), tileOfMinesweeper.getY(), tileOfMinesweeper.getCountOfMineNeighbors()));
            // Если в соседних плитках нет мин
            if (tileOfMinesweeper.getCountOfMineNeighbors() == 0) {
                // Пройдёмся по соседним плиткам
                for (TileOfMinesweeper tileOfMinesweeperNeighbor : tileOfMinesweeper.getNeighborTiles()) {
                    // Если соседняя плитка ещё не была открыта и там нет мины
                    if (!tileOfMinesweeperNeighbor.isOpen() && !tileOfMinesweeperNeighbor.isMineInPackageMode()) {
                        // Откроем соседнюю плитку
                        openRecursive(tileOfMinesweeperNeighbor);
                    }
                }
            }
        }

        // Если количество не открытых плиток равно количеству мин
        if (countOfClosedTiles == countOfMines && !tileOfMinesweeper.isMine()) {
            // Игра окончена победой
            gameMatch.win();
            return VICTORY;
        }
        // Продолжаем игру
        return GAME;
    }

    // Для плитки найти все соседние
    private void defineNeighbors(TileOfMinesweeper tileOfMinesweeper) {
        if (tileOfMinesweeper.getNeighborTiles() != null) {
            return;
        }
        int x = tileOfMinesweeper.getX();
        int y = tileOfMinesweeper.getY();
        Set<TileOfMinesweeper> neighbors = new HashSet<>();
        tileOfMinesweeper.setNeighborTiles(neighbors);
        for (int yy = y - 1; yy <= y + 1; yy++) {
            for (int xx = x - 1; xx <= x + 1; xx++) {
                if ((yy < 0 || yy >= getHeight())
                        || (xx < 0 || xx >= getWidth())
                        || (xx == x && yy == y)) {
                    continue;
                }
                neighbors.add(getTileByXY(xx, yy));
            }
        }
    }

    // Ширина игрового поля
    private int getWidth() {
        return tileOfMinesweepers[0].length;
    }

    // Высота игрового поля
    private int getHeight() {
        return tileOfMinesweepers.length;
    }
}
