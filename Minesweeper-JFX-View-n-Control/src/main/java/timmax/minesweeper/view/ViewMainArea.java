package timmax.minesweeper.view;

import timmax.minesweeper.model.*;
import timmax.tilegameengine.Game;

import static javafx.scene.paint.Color.*;

public class ViewMainArea implements View {
    private final Game game;
    private Model model;

    private static final String MINE = "💣"; // "\uD83D\uDCA3";

    private static final String FLAG = "🚩"; // "\uD83D\uDEA9";

    public ViewMainArea( Game game) {
        this.game = game;
    }

    @Override
    public void update( ) {
        Tile[ ][ ] tiles = model.getTiles( );
        for ( int y = 0; y < model.getHeight(); y++) {
            for ( int x = 0; x < model.getWidth(); x++) {
                if ( tiles[ y][ x].isOpen( )) {
                    if ( tiles[ y][ x].isMine( )) {
                        game.setCellValueEx( x, y, RED, MINE);
                    } else {
                        game.setCellNumber( x, y, tiles[ y][ x].getCountMineNeighbors( ));
                        game.setCellColor( x, y, GREEN);
                    }
                } else {
                    if ( tiles[ y][ x].isFlag( )) {
                        game.setCellValue( x, y, FLAG);
                        game.setCellColor( x, y, YELLOW);
                    } else {
                        game.setCellValue( x, y, "");
                        game.setCellColor( x, y, ORANGE);
                    }
                }
            }
        }

        if ( model.getGameStatus() == GameStatus.VICTORY) {
            game.showMessageDialog( AQUA, "Win!", WHITE, 30);
        } else if ( model.getGameStatus() == GameStatus.DEFEAT) {
            game.showMessageDialog( AQUA, "Game over!", WHITE, 30);
        }
    }

    @Override
    public void setModel( Model model) {
        this.model = model;
        model.addViewListener( this);
    }
}