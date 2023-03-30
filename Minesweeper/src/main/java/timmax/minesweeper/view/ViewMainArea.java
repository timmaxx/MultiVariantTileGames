package timmax.minesweeper.view;

import timmax.minesweeper.MinesweeperGame;
import timmax.minesweeper.model.Model;
import timmax.minesweeper.model.Tile;

import static javafx.scene.paint.Color.*;

public class ViewMainArea implements View {
    private final MinesweeperGame minesweeperGame;
    private Model model;

    private static final String MINE = "\uD83D\uDCA3";

    private static final String FLAG = "\uD83D\uDEA9";

    public ViewMainArea( MinesweeperGame minesweeperGame) {
        this.minesweeperGame = minesweeperGame;
    }

    @Override
    public void update( ) {
        Tile[ ][ ] tiles = model.getTiles( );
        for ( int y = 0; y < minesweeperGame.getCells( ).length; y++) {
            for ( int x = 0; x < minesweeperGame.getCells( )[ y].length; x++) {
                if ( tiles[ y][ x].isOpen( )) {
                    // System.out.println("update( " + x + ", " + y + ")");
                    if ( tiles[ y][ x].isMine( )) {
                        minesweeperGame.setCellValueEx( x, y, RED, MINE);
                    } else {
                        minesweeperGame.setCellNumber( x, y, tiles[ y][ x].getCountMineNeighbors( ));
                        minesweeperGame.setCellColor( x, y, GREEN);
                    }
                } else {
                    if ( tiles[ y][ x].isFlag( )) {
                        minesweeperGame.setCellValue( x, y, FLAG);
                        minesweeperGame.setCellColor( x, y, YELLOW);
                    } else {
                        minesweeperGame.setCellValue( x, y, "");
                        minesweeperGame.setCellColor( x, y, ORANGE);
                    }
                }
            }
        }
    }

    @Override
    public void setModel( Model model) {
        this.model = model;
        model.addViewListener( this);
    }
}