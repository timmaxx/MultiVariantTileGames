package timmax.tilegame.guiengine.jfx.view;

import javafx.application.Platform;
import javafx.scene.text.Text;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

abstract public class ViewTextFieldsJfx extends ViewJfx {
    String commonLabel;
    private final Text messageText;
    Class<? extends GameEvent> clazz;

    public ViewTextFieldsJfx(IModelOfClient iModelOfClient, Class<? extends GameEvent> clazz, String commonLabel) {
        super(iModelOfClient, null); // ToDo: убрать отсюда null. См. ToDo от 02.10.2023.

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
