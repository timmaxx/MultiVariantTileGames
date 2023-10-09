package timmax.tilegame.guiengine.jfx.view;

import javafx.scene.text.Text;
import java.util.NoSuchElementException;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gameevent.GameEvent;

abstract public class ViewTextFieldsJfx extends ViewJfx {
    String commonLabel;
    private final Text messageText;
    Class< ? extends GameEvent> clazz;


    public ViewTextFieldsJfx( BaseModel baseModel, Class< ? extends GameEvent> clazz, String commonLabel) {
        super( baseModel, null); // ToDo: убрать отсюда null. См. ToDo от 02.10.2023.

        this.clazz = clazz;
        this.commonLabel = commonLabel;
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

            if ( gameEvent.getClass( ) != clazz) {
                continue;
            }

            messageText.setText( commonLabel + createStringFromGameEvent( gameEvent));
        }
    }

    abstract protected String createStringFromGameEvent( GameEvent gameEvent);
}