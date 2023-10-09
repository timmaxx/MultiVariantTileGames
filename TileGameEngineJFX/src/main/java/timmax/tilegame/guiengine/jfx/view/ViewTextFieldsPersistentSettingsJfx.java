package timmax.tilegame.guiengine.jfx.view;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;

public class ViewTextFieldsPersistentSettingsJfx extends ViewTextFieldsJfx {
    public ViewTextFieldsPersistentSettingsJfx( BaseModel baseModel) {
        super( baseModel
                , GameEventNewGame.class
                , "\nPersistent settings:\n"); // ToDo: Разобраться и удалить ведущий '\n' в commonLabel.
    }

    @Override
    protected String createStringFromGameEvent( GameEvent gameEvent) {
        GameEventNewGame ge = ( ( GameEventNewGame) gameEvent);
        return  " Width = " + ge.getWidth( ) + ". " +
                " Height = " + ge.getHeight( ) + ". " +
                " All tiles = " + ge.getWidth( ) * ( ( GameEventNewGame) gameEvent).getHeight( ) + ". ";
    }
}