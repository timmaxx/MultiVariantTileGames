package timmax.minesweeper.model;

import timmax.basetilemodel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MinesweeperModel extends BaseModel< MinesweeperTile> {
    private int countMinesOnField;

    private int countFlags;

    private int countClosedTiles;

    //private int score;

    private static final Random random = new Random( );

    public void createNewGame( int width, int height, int restOfMineInstallationInPercents) {
        super.createNewGame( MinesweeperTile.class, width, height);
        countClosedTiles = width * height;

        //score = 0;

        countMinesOnField = 0;
        do {
            int x = random.nextInt( width);
            int y = random.nextInt( height);
            if ( !getTileByXY( x, y).isMineForModel( )) {
                getTileByXY( x, y).setMine( );
                countMinesOnField++;
            }

        } while ( countMinesOnField < height * width * restOfMineInstallationInPercents / 100 );

        countFlags = countMinesOnField;
        countMineNeighbors( );
    }

    public void markTile( int x, int y) {
        MinesweeperTile tile = getTileByXY( x, y);
        if (        tile.isOpen( )
                ||  ( countFlags == 0 && !tile.isFlag( ))
                ||  gameStatus != GameStatus.GAME) {
            return;
        }
        if ( tile.isFlag( )) {
            tile.setFlag( false);
            countFlags++;
        } else {
            tile.setFlag( true);
            countFlags--;
        }
        notifyViews( );
    }

    public void openTile( int x, int y) {
        openTileRecursive( x, y);
        notifyViews( );
    }

    private void openTileRecursive( int x, int y) {
        MinesweeperTile tile = getTileByXY( x, y);
        if ( tile.isOpen( ) || tile.isFlag( ) || gameStatus != GameStatus.GAME) {
            return;
        }
        tile.open( );
        countClosedTiles--;
        if ( tile.isMine( )) {
            gameOver( );
        } else {
            // score += 5;
            // game.setScore( score);
            if ( tile.getCountMineNeighbors( ) == 0) {
                for ( XY xy : getXYNeighbors( x, y)) {
                    MinesweeperTile neighborTile = getTileByXY( xy.getX( ), xy.getY( ));
                    if ( !neighborTile.isOpen( ) && !neighborTile.isMineForModel( )) {
                        openTile( xy.getX( ), xy.getY( ));
                    }
                }
            }
        }

        if ( countClosedTiles == countMinesOnField && !tile.isMine( )) {
            win( );
        }
    }

    private void gameOver( ) {
        gameStatus = GameStatus.DEFEAT;
    }

    private void win( ) {
        gameStatus = GameStatus.VICTORY;
    }

    private void countMineNeighbors( ) {
        for ( int y = 0; y < getHeight( ); y++) {
            for ( int x = 0; x < getWidth( ); x++) {
                MinesweeperTile tile = getTileByXY( x, y);
                for ( MinesweeperTile neighborTile : getTileNeighbors( x, y)) {
                    if ( neighborTile.isMineForModel( )) {
                        tile.incCountMineNeighbors( );
                    }
                }
            }
        }
    }

    private List< MinesweeperTile> getTileNeighbors( int x, int y) {
        return getXYNeighbors( x, y)
                .stream( )
                .map( xy -> getTileByXY( xy.getX( ), xy.getY( )))
                .collect( Collectors.toList( ));
    }

    private List< XY> getXYNeighbors( int x, int y) {
        List< XY> result = new ArrayList< >( );
        for ( int yy = y - 1; yy <= y + 1; yy++) {
            for ( int xx = x - 1; xx <= x + 1; xx++) {
                if (    ( yy < 0 || yy >= getHeight( ))
                        ||  ( xx < 0 || xx >= getWidth( ))
                        ||  ( xx == x && yy == y)) {
                    continue;
                }
                result.add( new XY( xx, yy));
            }
        }
        return result;
    }
}