package timmax.sokoban.jfx.view;

import javafx.scene.text.Text;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.GameEvent;
import timmax.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfBoxesInHouses;
import timmax.tilegameenginejfx.GameStackPaneController;
import timmax.tilegameenginejfx.ViewJfx;

import java.util.NoSuchElementException;

public class SokobanVariableSettingsCountOfBoxesInHouses extends ViewJfx {
    private final Text messageText;

    public SokobanVariableSettingsCountOfBoxesInHouses( BaseModel baseModel, GameStackPaneController gameStackPaneController) {
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

            if ( gameEvent instanceof GameEventSokobanVariableParamsCountOfBoxesInHouses) {
                // ToDo: Разобраться и удалить ведущий '\n'
                messageText.setText(
                        "\nVariable settings - Count of boxes in houses = " + ( ( GameEventSokobanVariableParamsCountOfBoxesInHouses) gameEvent).getCountOfBoxesInHouses( ) + ". "
                );
            }
        }
    }
}