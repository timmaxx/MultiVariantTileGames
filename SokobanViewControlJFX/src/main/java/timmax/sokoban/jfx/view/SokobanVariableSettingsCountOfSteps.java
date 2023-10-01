package timmax.sokoban.jfx.view;

import javafx.scene.text.Text;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.*;
import timmax.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfSteps;
import timmax.tilegameenginejfx.GameStackPaneController;
import timmax.tilegameenginejfx.ViewJfx;

import java.util.NoSuchElementException;

public class SokobanVariableSettingsCountOfSteps extends ViewJfx {
    private final Text messageText;

    public SokobanVariableSettingsCountOfSteps(BaseModel baseModel, GameStackPaneController gameStackPaneController) {
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

            if ( gameEvent instanceof GameEventSokobanVariableParamsCountOfSteps) {
                // ToDo: Разобраться и удалить ведущий '\n'
                messageText.setText(
                        "\nVariable settings - Count of steps = " + ( ( GameEventSokobanVariableParamsCountOfSteps) gameEvent).getCountOfSteps( ) + ". "
                );
            }
        }
    }
}