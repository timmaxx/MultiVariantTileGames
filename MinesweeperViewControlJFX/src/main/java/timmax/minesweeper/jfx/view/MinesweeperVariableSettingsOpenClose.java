package timmax.minesweeper.jfx.view;

import javafx.scene.text.Text;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.GameEvent;
import timmax.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsOpenClose;
import timmax.tilegameenginejfx.GameStackPaneController;
import timmax.tilegameenginejfx.ViewJfx;

import java.util.NoSuchElementException;

public class MinesweeperVariableSettingsOpenClose extends ViewJfx {
    private final Text messageText;

    public MinesweeperVariableSettingsOpenClose( BaseModel baseModel, GameStackPaneController gameStackPaneController) {
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

            if ( gameEvent instanceof GameEventMinesweeperVariableParamsOpenClose) {
                // ToDo: Разобраться и удалить ведущий '\n'
                messageText.setText(
                        "\nVariable settings - open and close tiles:\n" +
                        " Tiles were opened = " + ( ( GameEventMinesweeperVariableParamsOpenClose) gameEvent).getTilesWereOpened( ) + ". " +
                        " Tiles are still close = " + ( ( GameEventMinesweeperVariableParamsOpenClose) gameEvent).getTilesAreStillClose( ) + ". "
                );
            }
        }
    }
}