package timmax.minesweeper.model.gameobject;

import timmax.basetilemodel.GameStatus;

import java.util.HashSet;
import java.util.Set;

import static timmax.basetilemodel.GameStatus.*;

// Все объекты игры Сапёр
public class AllMinesweeperObjects {
    private final int width; // Ширина поля
    private final int height; // Высота поля
    private final int countOfMines; // Количество мин

    private int countOfFlags; // Количество оставшихся флагов, допустимых к использованию

    private int countOfClosedTiles; // Количество оставшихся закрытых плиток

    private final MinesweeperTile[ ][ ] minesweeperTiles; // Все плитки поля

    public AllMinesweeperObjects( int width, int height
            , MinesweeperTile[ ][ ] minesweeperTiles
            , int countOfMines) {
        this.width = width;
        this.height = height;
        this.minesweeperTiles = minesweeperTiles;
        this.countOfMines = countOfMines;

        countOfClosedTiles = width * height;
        countOfFlags = countOfMines;
    }

    // Инвертировать флаг
    public void inverseFlag(MinesweeperTile minesweeperTile) {
        // Если плитка уже открыта или (флагов больше нет и нет флага)
        if (        minesweeperTile.isOpen( )
                ||  ( countOfFlags == 0 && !minesweeperTile.isFlag( ))) {
            // Не будет инверсии
            return;
        }
        minesweeperTile.inverseFlag( ); // Инвертируем флаг
        if ( minesweeperTile.isFlag( )) {
            countOfFlags++;
        } else {
            countOfFlags--;
        }
    }


    // Открыть плитку и узнать, продолжена-ли будет игра или закончена (выигрышем или проигрышем).
    public GameStatus open( MinesweeperTile minesweeperTile) {
        if ( !minesweeperTile.isOpen( ) && minesweeperTile.isFlag( )) {
            return GAME;
        }
        return openRecursive(minesweeperTile);
    }


    public MinesweeperTile getTileByXY(int x, int y) {
        return minesweeperTiles[ y][ x];
    }


    // Private --------------------------------------------------------------------------------------------------------

    // Открыть заданную плитку, и возможно, рекурсивно ещё несколько.
    private GameStatus openRecursive( MinesweeperTile minesweeperTile) {
        minesweeperTile.open( );
        countOfClosedTiles--; // Количество закрытых плиток сделаем меньше
        // Если в открытой плитке мина
        if ( minesweeperTile.isMine( )) {
            // Завершение игры поражением
            return DEFEAT;
        } else {
            defineNeighbors( minesweeperTile);
            // Если в соседних плитках нет мин
            if ( minesweeperTile.getCountOfMineNeighbors( ) == 0) {
                // Пройдёмся по соседним плиткам
                for ( MinesweeperTile minesweeperTileNeighbor : minesweeperTile.getNeighborTiles( )) {
                    // Если соседняя плитка ещё не была открыта и там нет мины
                    if ( !minesweeperTileNeighbor.isOpen( ) && !minesweeperTileNeighbor.isMineInPackageMode( )) {
                        // Откроем соседнюю плитку
                        openRecursive(minesweeperTileNeighbor);
                    }
                }
            }
        }

        // Если количество не открытых плиток равно количеству мин
        if ( countOfClosedTiles == countOfMines && !minesweeperTile.isMine( )) {
            // Игра окончена победой
            return VICTORY;
        }
        // Продолжаем игру
        return GAME;
    }


    // Для плитки найти все соседние
    private void defineNeighbors( MinesweeperTile minesweeperTile) {
        if ( minesweeperTile.getNeighborTiles( ) != null ) {
            return;
        }
        int x = minesweeperTile.getX();
        int y = minesweeperTile.getY();
        Set< MinesweeperTile> neighbors = new HashSet< >( );
        minesweeperTile.setNeighborTiles( neighbors);
        for ( int yy = y - 1; yy <= y + 1; yy++) {
            for ( int xx = x - 1; xx <= x + 1; xx++) {
                if (        ( yy < 0 || yy >= minesweeperTiles.length)
                        ||  ( xx < 0 || xx >= minesweeperTiles[ y].length)
                        ||  ( xx == x && yy == y)) {
                    continue;
                }
                // neighbors.add( minesweeperTiles[ yy][ xx]);
                neighbors.add( getTileByXY( xx, yy));
            }
        }
    }
}