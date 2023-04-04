package timmax.minesweeper.model;

import timmax.basetilemodel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Model extends BaseModel< Tile> {

    private int countMinesOnField;

    private int countFlags;

    private int countClosedTiles;

    //private int score;

    private static final Random random = new Random( );

    private GameStatus gameStatus;

    public Model( int width, int height, int restOfMineInstallationInPercents) {
        super( Tile.class, width, height);

        countClosedTiles = width * height;
        gameStatus = GameStatus.GAME;
        //score = 0;

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


    public GameStatus getGameStatus() {
        return gameStatus;
    }

    private void countMineNeighbors( ) {
        for ( int y = 0; y < getHeight( ); y++) {
            for ( int x = 0; x < getWidth( ); x++) {
                Tile tile = getTileByXY( x, y);
                for ( Tile neighborTile : getTileNeighbors( x, y)) {
                    if ( neighborTile.isMineForModel( )) {
                        tile.incCountMineNeighbors( );
                    }
                }
            }
        }
    }

    private List< Tile> getTileNeighbors( int x, int y) {
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

    public void openTile( int x, int y) {
        openTileRecursive( x, y);
        notifyViews( );
    }

    private void openTileRecursive( int x, int y) {
        Tile tile = getTileByXY( x, y);
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
                    Tile neighborTile = getTileByXY( xy.getX( ), xy.getY( ));
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

    public void markTile( int x, int y) {
        Tile tile = getTileByXY( x, y);
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

    private void gameOver( ) {
        gameStatus = GameStatus.DEFEAT;
    }

    private void win( ) {
        gameStatus = GameStatus.VICTORY;
    }
}