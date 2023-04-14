package timmax.minesweeper.model;

import timmax.minesweeper.model.gameobject.MinesweeperGameObjects;
import timmax.minesweeper.model.gameobject.MinesweeperObject;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LevelGenerator {
    private static final Random random = new Random( );

    public MinesweeperGameObjects getLevel(int width, int height, int restOfMineInstallationInPercents) {
        Set< MinesweeperObject> mines = new HashSet<>();
        MinesweeperObject[ ][ ] minesweeperObjects = new MinesweeperObject[ height][ width];
        int countMinesOnField = 0;

        do {
            int x = random.nextInt( width);
            int y = random.nextInt( height);
            MinesweeperObject minesweeperObject = new MinesweeperObject( x, y, true);
            if ( mines.add( minesweeperObject)) {
                countMinesOnField++;
                minesweeperObjects[ y][ x] = minesweeperObject;
            }
        } while ( countMinesOnField < height * width * restOfMineInstallationInPercents / 100 );

        /*
        MinesweeperObject minesweeperObject;
        minesweeperObject = new MinesweeperObject( 0, 9, true); mines.add( minesweeperObject); minesweeperObjects[ 9][ 0] = minesweeperObject; countMinesOnField++;
        minesweeperObject = new MinesweeperObject( 9, 0, true); mines.add( minesweeperObject); minesweeperObjects[ 0][ 9] = minesweeperObject; countMinesOnField++;
        minesweeperObject = new MinesweeperObject( 9, 9, true); mines.add( minesweeperObject); minesweeperObjects[ 9][ 9] = minesweeperObject; countMinesOnField++;
*/
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (minesweeperObjects[ y][ x] == null) {
                    minesweeperObjects[ y][ x] = new MinesweeperObject(x, y, false);
                }
            }
        }
        return new MinesweeperGameObjects( width, height, minesweeperObjects, countMinesOnField);
    }
}