package timmax.minesweeper.jfx.view;

import javafx.scene.text.Text;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.GameEvent;
import timmax.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsFlag;
import timmax.tilegameenginejfx.GameStackPaneController;
import timmax.tilegameenginejfx.ViewJfx;

import java.util.NoSuchElementException;

public class MinesweeperVariableSettingsFlag extends ViewJfx {
    private final Text messageText;

    public MinesweeperVariableSettingsFlag( BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        super( baseModel, gameStackPaneController);
        messageText = new Text( );
        getChildren( ).add( messageText);
    }

    @Override
    public void update( ) {
        GameEvent gameEvent;
        while ( true) {
            try {
                gameEvent = removeFromGameQueueForOneView( );
            } catch ( NoSuchElementException nsee) {
                break;
            }

            if ( gameEvent instanceof GameEventMinesweeperVariableParamsFlag) {
                // ToDo: Разобраться и удалить ведущий '\n'
                messageText.setText(
                        "\nVariable settings - flags:\n" +
                        " Flags were used = " + ( ( GameEventMinesweeperVariableParamsFlag) gameEvent).getFlagsWereUsed( ) + ". " +
                        " Flags are still available for using = " + ( ( GameEventMinesweeperVariableParamsFlag) gameEvent).getFlagsAreStillAvailableForUsing( ) + ". "
                );
            }
        }
    }
}