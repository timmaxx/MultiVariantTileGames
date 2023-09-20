package timmax.minesweeper.model.gameobject;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LevelGenerator {
    private static final Random random = new Random( );

    public AllMinesweeperObjects getLevel( int width, int height, int restOfMineInstallationInPercents) {
        Set< MinesweeperTile> mines = new HashSet< >( );
        MinesweeperTile[ ][ ] minesweeperTiles = new MinesweeperTile[ height][ width];
        int countMinesOnField = 0;

        // System.out.println( "Mines:");
        do {
            int x = random.nextInt( width);
            int y = random.nextInt( height);
            MinesweeperTile minesweeperTile = new MinesweeperTile( x, y, true);
            if ( mines.add( minesweeperTile)) {
                countMinesOnField++;
                minesweeperTiles[ y][ x] = minesweeperTile;
                // System.out.println( "( " + x + ", " + y + ")");
            }
        } while ( countMinesOnField < height * width * restOfMineInstallationInPercents / 100 );

        /*
        MinesweeperObject minesweeperObject;
        minesweeperObject = new MinesweeperObject( 0, 9, true); mines.add( minesweeperObject); minesweeperObjects[ 9][ 0] = minesweeperObject; countMinesOnField++;
        minesweeperObject = new MinesweeperObject( 9, 0, true); mines.add( minesweeperObject); minesweeperObjects[ 0][ 9] = minesweeperObject; countMinesOnField++;
        minesweeperObject = new MinesweeperObject( 9, 9, true); mines.add( minesweeperObject); minesweeperObjects[ 9][ 9] = minesweeperObject; countMinesOnField++;
        */

        for ( int y = 0; y < height; y++) {
            for ( int x = 0; x < width; x++) {
                if ( minesweeperTiles[ y][ x] == null) {
                    minesweeperTiles[ y][ x] = new MinesweeperTile( x, y, false);
                }
            }
        }

        return new AllMinesweeperObjects( minesweeperTiles, countMinesOnField);
    }
}