package timmax.tilegame.game.minesweeper.model.gameobject;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LevelGenerator {
    private static final Random random = new Random();

    public AllMinesweeperObjects getLevel(int width, int height, int restOfMineInstallationInPercents) {
        Set<TileOfMinesweeper> mines = new HashSet<>();
        TileOfMinesweeper[][] tileOfMinesweepers = new TileOfMinesweeper[height][width];
        int countMinesOnField = 0;

        do {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            TileOfMinesweeper tileOfMinesweeper = new TileOfMinesweeper(x, y, true);
            if (mines.add(tileOfMinesweeper)) {
                countMinesOnField++;
                tileOfMinesweepers[y][x] = tileOfMinesweeper;
            }
        } while (countMinesOnField < height * width * restOfMineInstallationInPercents / 100);

        /*
        MinesweeperObject minesweeperObject;
        minesweeperObject = new MinesweeperObject( 0, 9, true); mines.add( minesweeperObject); minesweeperObjects[ 9][ 0] = minesweeperObject; countMinesOnField++;
        minesweeperObject = new MinesweeperObject( 9, 0, true); mines.add( minesweeperObject); minesweeperObjects[ 0][ 9] = minesweeperObject; countMinesOnField++;
        minesweeperObject = new MinesweeperObject( 9, 9, true); mines.add( minesweeperObject); minesweeperObjects[ 9][ 9] = minesweeperObject; countMinesOnField++;
        */

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (tileOfMinesweepers[y][x] == null) {
                    tileOfMinesweepers[y][x] = new TileOfMinesweeper(x, y, false);
                }
            }
        }

        return new AllMinesweeperObjects(tileOfMinesweepers, countMinesOnField);
    }
}
