package timmax.sokoban.jfx.view;

import javafx.scene.text.Text;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.*;
import timmax.sokoban.model.gameevent.GameEventSokobanPersistentParams;
import timmax.tilegameenginejfx.*;

import java.util.NoSuchElementException;

public class SokobanPersistentSettings extends ViewJfx {
    private final Text messageText;

    public SokobanPersistentSettings( BaseModel baseModel, GameStackPaneController gameStackPaneController) {
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
            } else if ( gameEvent instanceof GameEventSokobanPersistentParams) {
                messageText.setText(
                        messageText.getText( ) + "\n" +
                        " Count of all boxes and homes = " + ( (GameEventSokobanPersistentParams) gameEvent).getCountOfBoxesAndHomes( ) + "."
                );
            }
        }
    }
}