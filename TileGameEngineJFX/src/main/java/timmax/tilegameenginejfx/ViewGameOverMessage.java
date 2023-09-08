package timmax.tilegameenginejfx;

import javafx.scene.paint.Color;
import timmax.basetilemodel.*;

import static javafx.scene.paint.Color.AQUA;
import static javafx.scene.paint.Color.WHITE;
import static timmax.basetilemodel.GameStatus.*;

public class ViewGameOverMessage extends View {
    private static final int GAME_OVER_MESSAGE_TEXT_SIZE = 30;
    private static final Color GAME_OVER_MESSAGE_CELL_COLOR = AQUA;
    private static final Color GAME_OVER_MESSAGE_TEXT_COLOR = WHITE;
    private static final String GAME_OVER_MESSAGE_VICTORY_MESSAGE = "Victory!";
    private static final String GAME_OVER_MESSAGE_DEFEAT_MESSAGE = "Defeat!";

    Game game;


    public ViewGameOverMessage( BaseModel baseModel, Game game) {
        super( baseModel);
        this.game = game;
    }

    @Override
    public void update( ) {
        GameStatus gameStatus = baseModel.getGameStatus( );
        if ( gameStatus == GAME) {
            return;
        }
        String message = "";
        if ( gameStatus == VICTORY) {
            message = GAME_OVER_MESSAGE_VICTORY_MESSAGE;
        } else if ( gameStatus == DEFEAT) {
            message = GAME_OVER_MESSAGE_DEFEAT_MESSAGE;
        }
        game.showGameOverMessage( GAME_OVER_MESSAGE_CELL_COLOR
                , message
                , GAME_OVER_MESSAGE_TEXT_COLOR
                , GAME_OVER_MESSAGE_TEXT_SIZE
        );
    }
}