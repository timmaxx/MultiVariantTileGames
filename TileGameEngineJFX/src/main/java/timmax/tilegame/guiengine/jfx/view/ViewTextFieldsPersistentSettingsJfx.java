package timmax.tilegame.guiengine.jfx.view;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;
import timmax.tilegame.transport.TransportOfClient;

public class ViewTextFieldsPersistentSettingsJfx<ClientId> extends ViewTextFieldsJfx<ClientId> {
    public ViewTextFieldsPersistentSettingsJfx(
            TransportOfClient<ClientId> transportOfClient,
            String viewName) {
        super(transportOfClient
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
