package timmax.minesweeper.view;

import timmax.basetilemodel.BaseModel;
import timmax.minesweeper.model.*;
import timmax.tilegameenginejfx.Game;
import timmax.basetilemodel.View;

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
        for ( int y = 0; y < model.getHeight(); y++) {
            for ( int x = 0; x < model.getWidth(); x++) {
                Tile tile = model.getTileByXY( x, y);
                if ( tile.isOpen( )) {
                    if ( tile.isMine( )) {
                        game.setCellValueEx( x, y, RED, MINE);
                    } else {
                        game.setCellNumber( x, y, tile.getCountMineNeighbors( ));
                        game.setCellColor( x, y, GREEN);
                    }
                } else {
                    if ( tile.isFlag( )) {
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
    public void setModel( BaseModel model) {
        this.model = ( Model) model;
        model.addViewListener( this);
    }
}