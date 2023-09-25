package timmax.minesweeper.jfx.view;

import java.util.NoSuchElementException;
import javafx.scene.text.Text;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.GameEvent;
import timmax.minesweeper.model.gameevent.GameEventMinesweeperVariableParams;
import timmax.tilegameenginejfx.GameStackPaneController;
import timmax.tilegameenginejfx.ViewJfx;

public class MinesweeperVariableSettings extends ViewJfx {
    private final Text messageText;

    public MinesweeperVariableSettings( BaseModel baseModel, GameStackPaneController gameStackPaneController) {
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

            if ( gameEvent instanceof GameEventMinesweeperVariableParams) {
                // ToDo: Разобраться и удалить ведущий '\n'
                messageText.setText(
                        "\nVariable settings:\n" +
                        " Tiles were opened = " + ((GameEventMinesweeperVariableParams) gameEvent).getTilesWereOpened( ) + ". " +
                        " Tiles are still close = " + ((GameEventMinesweeperVariableParams) gameEvent).getTilesAreStillClose( ) + ". " +
                        " Flags were used = " + ((GameEventMinesweeperVariableParams) gameEvent).getFlagsWereUsed( ) + ". " +
                        " Flags are still available for using = " + ((GameEventMinesweeperVariableParams) gameEvent).getFlagsAreStillAvailableForUsing( ) + ". "
                );
            }
        }
    }
}