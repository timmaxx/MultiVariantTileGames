package timmax.tilegame.guiengine.jfx.view;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.transport.ISenderOfEventOfClient;

public class ViewTextFieldsPersistentSettingsJfx extends ViewTextFieldsJfx {
    public ViewTextFieldsPersistentSettingsJfx(
            ISenderOfEventOfClient senderOfEventOfClient,
            String viewName,
            GameType gameType) {
        super(senderOfEventOfClient
                , GameEventNewGame.class
                //  ToDo:   Разобраться и удалить ведущий '\n' в commonLabel.
                , "\nPersistent settings:\n"
                , viewName
                , gameType);
    }

    @Override
    protected String createStringFromGameEvent(GameEvent gameEvent) {
        GameEventNewGame ge = ((GameEventNewGame) gameEvent);
        return " ge.getWidthHeightSizes() = " + ge.getWidthHeightSizes();
    }
}
