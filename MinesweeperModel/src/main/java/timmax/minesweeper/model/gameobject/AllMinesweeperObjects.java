package timmax.minesweeper.model.gameobject;

import java.util.HashSet;
import java.util.Set;
import timmax.tilegame.basemodel.GameStatus;
import timmax.tilegame.basemodel.gameevent.GameEventGameOver;
import timmax.minesweeper.model.MinesweeperModel;
import timmax.minesweeper.model.gameevent.*;

import static timmax.tilegame.basemodel.GameStatus.*;

// Все объекты игры Сапёр
public class AllMinesweeperObjects {
    private final int countOfMines; // Количество мин
    private final MinesweeperTile[ ][ ] minesweeperTiles; // Все плитки поля

    private int countOfFlags; // Количество оставшихся флагов, допустимых к использованию
    private int countOfClosedTiles; // Количество оставшихся закрытых плиток
    MinesweeperModel minesweeperModel;


    AllMinesweeperObjects( MinesweeperTile[ ][ ] minesweeperTiles, int countOfMines) {
        this.minesweeperTiles = minesweeperTiles;
        this.countOfMines = countOfMines;

        countOfClosedTiles = getWidth( ) * getHeight( );
        countOfFlags = countOfMines;
    }

    public void setModel( MinesweeperModel minesweeperModel) {
        this.minesweeperModel = minesweeperModel;
    }

    public int getCountOfMines( ) {
        return countOfMines;
    }

    // Инвертировать флаг
    public boolean inverseFlag( MinesweeperTile minesweeperTile) {
        // Если плитка уже открыта или (флагов больше нет и нет флага)
        if (        minesweeperTile.isOpen( )
                ||  ( countOfFlags == 0 && !minesweeperTile.isFlag( ))) {
            // Не будет инверсии
            return minesweeperTile.isFlag( );
        }
        minesweeperTile.inverseFlag( ); // Инвертируем флаг
        if ( minesweeperTile.isFlag( )) {
            countOfFlags--;
        } else {
            countOfFlags++;
        }
        minesweeperModel.addGameEventIntoQueue( new GameEventMinesweeperVariableParamsFlag(
                countOfMines - countOfFlags,
                countOfFlags
        ));
        return minesweeperTile.isFlag( );
    }

    // Открыть плитку и узнать, продолжена-ли будет игра или закончена (выигрышем или проигрышем).
    public GameStatus open( MinesweeperTile minesweeperTile) {
        if ( !minesweeperTile.isOpen( ) && minesweeperTile.isFlag( )) {
            return GAME;
        }
        GameStatus gameStatus = openRecursive( minesweeperTile);
        minesweeperModel.addGameEventIntoQueue( new GameEventMinesweeperVariableParamsOpenClose(
                getWidth( ) * getHeight( ) - countOfClosedTiles,
                countOfClosedTiles
        ));
        return gameStatus;
    }

    public MinesweeperTile getTileByXY( int x, int y) {
        return minesweeperTiles[ y][ x];
    }


    // Private --------------------------------------------------------------------------------------------------------

    // Открыть заданную плитку, и возможно, рекурсивно ещё несколько.
    private GameStatus openRecursive( MinesweeperTile minesweeperTile) {
        if ( minesweeperTile.isOpen( ) || minesweeperTile.isFlag( )) {
            return GAME;
        }
        minesweeperTile.open( );
        countOfClosedTiles--; // Количество закрытых плиток сделаем меньше
        // Если в открытой плитке мина
        if ( minesweeperTile.isMine( )) {
            // Завершение игры поражением
            minesweeperModel.addGameEventIntoQueue( new GameEventOneTileOpenMine( minesweeperTile.getX( ), minesweeperTile.getY( )));
            minesweeperModel.addGameEventIntoQueue( new GameEventGameOver( DEFEAT));
            return DEFEAT;
        } else {
            defineNeighbors( minesweeperTile);
            minesweeperModel.addGameEventIntoQueue( new GameEventOneTileOpenNoMine( minesweeperTile.getX( ), minesweeperTile.getY( ), minesweeperTile.getCountOfMineNeighbors( )));
            // Если в соседних плитках нет мин
            if ( minesweeperTile.getCountOfMineNeighbors( ) == 0) {
                // Пройдёмся по соседним плиткам
                for ( MinesweeperTile minesweeperTileNeighbor : minesweeperTile.getNeighborTiles( )) {
                    // Если соседняя плитка ещё не была открыта и там нет мины
                    if ( !minesweeperTileNeighbor.isOpen( ) && !minesweeperTileNeighbor.isMineInPackageMode( )) {
                        // Откроем соседнюю плитку
                        openRecursive( minesweeperTileNeighbor);
                    }
                }
            }
        }

        // Если количество не открытых плиток равно количеству мин
        if ( countOfClosedTiles == countOfMines && !minesweeperTile.isMine( )) {
            // Игра окончена победой
            minesweeperModel.addGameEventIntoQueue( new GameEventGameOver( VICTORY));
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
        int x = minesweeperTile.getX( );
        int y = minesweeperTile.getY( );
        Set< MinesweeperTile> neighbors = new HashSet< >( );
        minesweeperTile.setNeighborTiles( neighbors);
        for ( int yy = y - 1; yy <= y + 1; yy++) {
            for ( int xx = x - 1; xx <= x + 1; xx++) {
                if (        ( yy < 0 || yy >= getHeight( ))
                        ||  ( xx < 0 || xx >= getWidth( ))
                        ||  ( xx == x && yy == y)) {
                    continue;
                }
                neighbors.add( getTileByXY( xx, yy));
            }
        }
    }

    // Ширина игрового поля
    private int getWidth( ) {
        return minesweeperTiles[ 0].length;
    }

    // Высота игрового поля
    private int getHeight( ) {
        return minesweeperTiles.length;
    }
}