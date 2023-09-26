package timmax.sokoban.jfx.view;

import javafx.scene.text.Text;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.GameEvent;
import timmax.sokoban.model.gameevent.GameEventSokobanVariableParams;
import timmax.tilegameenginejfx.*;

import java.util.NoSuchElementException;

public class SokobanVariableSettings extends ViewJfx {
    private final Text messageText;

    public SokobanVariableSettings(BaseModel baseModel, GameStackPaneController gameStackPaneController) {
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

            if ( gameEvent instanceof GameEventSokobanVariableParams) {
                // ToDo: Разобраться и удалить ведущий '\n'
                messageText.setText(
                        "\nVariable settings:\n" +
                        " Count of steps = " + ((GameEventSokobanVariableParams) gameEvent).getCountOfSteps( ) + ". " +
                        " Count of boxes in houses = " + ((GameEventSokobanVariableParams) gameEvent).getCountOfBoxInHouses( ) + ". "
                );
            }
        }
    }
}