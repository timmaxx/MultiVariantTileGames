package timmax.tilegameenginejfx;

import javafx.scene.text.Text;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.GameEvent;

// import java.util.ArrayList;
// import java.util.List;
import java.util.NoSuchElementException;

abstract public class ViewTextFieldsJfx extends ViewJfx {
    // List< TextField> textFieldList;
    String commonLabel;
    private final Text messageText;
    Class< ? extends GameEvent> clazz;

    public ViewTextFieldsJfx( BaseModel baseModel, Class< ? extends GameEvent> clazz, String commonLabel) {
        super( baseModel, null); // ToDo: убрать отсюда null. См. ToDo от 02.10.2023.

        this.clazz = clazz;
        System.out.println( "this.clazz = " + this.clazz);
        this.commonLabel = commonLabel;
        // textFieldList = new ArrayList< >( );
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

            // if ( gameEvent instanceof GameEventType get) {}
            /*
            if ( this.getClass() == GameEventType) {
            }
            */
            // if ( gameEvent.getClass( ) != this.getClass( ))
            // if ( gameEvent instanceof clazz ge)
            if ( gameEvent.getClass( ) != clazz)
            {
                continue;
            }
/*
            GameEventType ge = ( GameEventType) gameEvent;
            messageText.setText( commonLabel + createStringFromGameEvent( ge));
*/
            // ToDo: Разобраться и удалить ведущий '\n' в commonLabel.
            messageText.setText( commonLabel + createStringFromGameEvent( gameEvent));
        }
    }

    // abstract protected String createStringFromGameEvent( GameEventType gameEvent);
    abstract protected String createStringFromGameEvent( GameEvent gameEvent);
}