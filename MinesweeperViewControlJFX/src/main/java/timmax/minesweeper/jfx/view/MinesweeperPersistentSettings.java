package timmax.minesweeper.jfx.view;

import java.util.NoSuchElementException;
import javafx.scene.text.Text;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.*;
import timmax.minesweeper.model.gameevent.GameEventMinesweeperPersistentParams;
import timmax.tilegameenginejfx.*;

public class MinesweeperPersistentSettings extends ViewJfx {
    private final Text messageText;

    public MinesweeperPersistentSettings( BaseModel baseModel, GameStackPaneController gameStackPaneController) {
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

            if ( gameEvent instanceof GameEventNewGame) {
                // ToDo: Разобраться и удалить ведущий '\n'
                messageText.setText(
                        "\nPersistent settings:\n" +
                        " Width = " + ( ( GameEventNewGame) gameEvent).getWidth( ) + ". " +
                        " Height = " + ( ( GameEventNewGame) gameEvent).getHeight( ) + ". " +
                        " All tiles = " + ( ( GameEventNewGame) gameEvent).getWidth( ) * ( ( GameEventNewGame) gameEvent).getHeight( ) + ". "
                );
            } else if ( gameEvent instanceof GameEventMinesweeperPersistentParams) {
                messageText.setText(
                        messageText.getText( ) + "\n" +
                        " Count of all mines in the field = " + ( ( GameEventMinesweeperPersistentParams) gameEvent).getCountOfMines( ) + "."
                );
            }
        }
    }
}