package timmax.tilegame.guiengine.jfx.view;

import javafx.application.Platform;
import javafx.scene.text.Text;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server_client.IGameMatchX;
import timmax.tilegame.transport.ISenderOfEventOfClient;

abstract public class ViewTextFieldsJfx extends ViewJfx {
    String commonLabel;
    private final Text messageText;
    Class<? extends GameEvent> clazz;

    public ViewTextFieldsJfx(
            ISenderOfEventOfClient senderOfEventOfClient,
            Class<? extends GameEvent> clazz,
            String commonLabel,
            String viewName,
            GameType<IGameMatchX> gameType) {
        // ToDo: убрать отсюда null. См. ToDo от 02.10.2023.
        super(senderOfEventOfClient, null, viewName, gameType);

        this.clazz = clazz;
        this.commonLabel = commonLabel;
        messageText = new Text();
        getChildren().add(messageText);
    }

    @Override
    public void update(GameEvent gameEvent) {
        if (gameEvent.getClass() != clazz) {
            return;
        }
        Platform.runLater(() -> {
            // do your GUI stuff here
            messageText.setText(commonLabel + createStringFromGameEvent(gameEvent));
        });

    }

    abstract protected String createStringFromGameEvent(GameEvent gameEvent);
}
