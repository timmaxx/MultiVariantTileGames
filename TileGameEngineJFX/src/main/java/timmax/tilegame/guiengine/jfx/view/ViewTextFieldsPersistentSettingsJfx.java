package timmax.tilegame.guiengine.jfx.view;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

public class ViewTextFieldsPersistentSettingsJfx extends ViewTextFieldsJfx {
    public ViewTextFieldsPersistentSettingsJfx(IModelOfClient iModelOfClient, String viewName) {
        super(iModelOfClient
                , GameEventNewGame.class
                // ToDo: Разобраться и удалить ведущий '\n' в commonLabel.
                , "\nPersistent settings:\n"
                , viewName);
    }

    @Override
    protected String createStringFromGameEvent(GameEvent gameEvent) {
        GameEventNewGame ge = ((GameEventNewGame) gameEvent);
        return " Width = " + ge.getWidth() + ". " +
                " Height = " + ge.getHeight() + ". " +
                " All tiles = " + ge.getWidth() * ((GameEventNewGame) gameEvent).getHeight() + ". ";
    }
}
