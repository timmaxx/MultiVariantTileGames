package timmax.minesweeper.view;

import javafx.scene.paint.Color;
import timmax.basetilemodel.*;
import timmax.minesweeper.model.MinesweeperModel;
import timmax.tilegameenginejfx.Game;

import static javafx.scene.paint.Color.AQUA;
import static javafx.scene.paint.Color.WHITE;
import static timmax.basetilemodel.GameStatus.*;

public class MinesweeperViewGameOverMessage implements View {
    private static final int MESSAGE_DIALOG_TEXT_SIZE = 30;
    private static final Color MESSAGE_DIALOG_CELL_COLOR = AQUA;
    private static final Color MESSAGE_DIALOG_TEXT_COLOR = WHITE;
    private static final String MESSAGE_DIALOG_VICTORY_MESSAGE = "Victory!";
    private static final String MESSAGE_DIALOG_DEFEAT_MESSAGE = "Defeat!";
    MinesweeperModel minesweeperModel;
    Game game;

    public MinesweeperViewGameOverMessage(Game game) {
        this.game = game;
    }

    @Override
    public void update() {
        GameStatus gameStatus = minesweeperModel.getGameStatus();
        if ( gameStatus == GAME) {
            return;
        }
        String dialogMessage = "";
        if ( gameStatus == VICTORY) {
            dialogMessage = MESSAGE_DIALOG_VICTORY_MESSAGE;
        } else if ( gameStatus == DEFEAT) {
            dialogMessage = MESSAGE_DIALOG_DEFEAT_MESSAGE;
        }
        game.showMessageDialog( MESSAGE_DIALOG_CELL_COLOR
                , dialogMessage
                , MESSAGE_DIALOG_TEXT_COLOR
                , MESSAGE_DIALOG_TEXT_SIZE);
    }

    @Override
    public void setModel(BaseModel baseModel) {
        minesweeperModel = (MinesweeperModel) baseModel;
        minesweeperModel.addViewListener( this);
    }
}