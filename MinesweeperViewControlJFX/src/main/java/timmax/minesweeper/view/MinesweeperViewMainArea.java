package timmax.minesweeper.view;

import javafx.scene.paint.Color;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.GameStatus;
import timmax.minesweeper.model.*;
import timmax.tilegameenginejfx.Game;
import timmax.basetilemodel.View;

import static javafx.scene.paint.Color.*;

public class MinesweeperViewMainArea implements View {
    private final Game game;
    private MinesweeperModel minesweeperModel;

    private static final String MINE = "💣"; // "\uD83D\uDCA3";
    private static final String FLAG = "🚩"; // "\uD83D\uDEA9";

    private static final int MESSAGE_DIALOG_TEXT_SIZE = 30;
    private static final Color MESSAGE_DIALOG_CELL_COLOR = AQUA;
    private static final Color MESSAGE_DIALOG_TEXT_COLOR = WHITE;
    private static final String MESSAGE_DIALOG_VICTORY_MESSAGE = "Victory!";
    private static final String MESSAGE_DIALOG_DEFEAT_MESSAGE = "Defeat!";

    private static final Color CELL_COLOR_FOR_MINE = RED;

    public MinesweeperViewMainArea( Game game) {
        this.game = game;
    }

    @Override
    public void setModel( BaseModel model) {
        this.minesweeperModel = ( MinesweeperModel) model;
        model.addViewListener( this);
    }

    @Override
    public void updateOneTile( int x, int y) {
        MinesweeperTile tile = minesweeperModel.getTileByXY( x, y);
        if ( tile.isOpen( )) {
            if ( tile.isMine( )) {
                game.setCellValueEx( x, y, CELL_COLOR_FOR_MINE, MINE);
            } else {
                game.setCellNumber( x, y, tile.getCountMineNeighbors( ));
                game.setCellColor( x, y, GREEN); // ToDo constant
            }
        } else {
            if ( tile.isFlag( )) {
                game.setCellValue( x, y, FLAG); // ToDo constant
                game.setCellColor( x, y, YELLOW); // ToDo constant
            } else {
                game.setCellValue( x, y, "");
                game.setCellColor( x, y, ORANGE); // ToDo constant
            }
        }
    }

    @Override
    public void updateAllTiles( ) {
        // ToDo: Хорошо было-бы сделать циклы в классе-родителе. А из него вызывать updateOneTile().
        for ( int y = 0; y < minesweeperModel.getHeight( ); y++) {
            for ( int x = 0; x < minesweeperModel.getWidth( ); x++) {
                updateOneTile( x, y);
            }
        }

        if ( minesweeperModel.getGameStatus() == GameStatus.GAME) {
            return;
        }
        String dialogMessage = "";
        if ( minesweeperModel.getGameStatus( ) == GameStatus.VICTORY) {
            dialogMessage = MESSAGE_DIALOG_VICTORY_MESSAGE;
        } else if ( minesweeperModel.getGameStatus( ) == GameStatus.DEFEAT) {
            dialogMessage = MESSAGE_DIALOG_DEFEAT_MESSAGE;
        }
        game.showMessageDialog( MESSAGE_DIALOG_CELL_COLOR
                , dialogMessage
                , MESSAGE_DIALOG_TEXT_COLOR
                , MESSAGE_DIALOG_TEXT_SIZE);
    }
}