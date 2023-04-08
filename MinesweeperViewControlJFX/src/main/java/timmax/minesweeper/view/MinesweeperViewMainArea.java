package timmax.minesweeper.view;

import javafx.scene.paint.Color;
import timmax.basetilemodel.ViewMainArea;
import timmax.minesweeper.model.*;
import timmax.tilegameenginejfx.*;

import static javafx.scene.paint.Color.*;

public class MinesweeperViewMainArea extends ViewMainArea< MinesweeperTile> {
    Game game;
    private static final String MINE = "💣"; // "\uD83D\uDCA3";
    private static final String FLAG = "🚩"; // "\uD83D\uDEA9";
/*
    private static final int MESSAGE_DIALOG_TEXT_SIZE = 30;
    private static final Color MESSAGE_DIALOG_CELL_COLOR = AQUA;
    private static final Color MESSAGE_DIALOG_TEXT_COLOR = WHITE;
    private static final String MESSAGE_DIALOG_VICTORY_MESSAGE = "Victory!";
    private static final String MESSAGE_DIALOG_DEFEAT_MESSAGE = "Defeat!";
*/
    private static final Color CELL_COLOR_FOR_MINE = RED;

    public MinesweeperViewMainArea( Game game) {
        this.game = game;
    }

    @Override
    public void updateOneTile( int x, int y) {
        MinesweeperTile tile = model.getTileByXY( x, y);
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

    /*  // Нижеописанные действия нужно перенести вообще в другую View
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
    */
}