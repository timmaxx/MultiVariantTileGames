package timmax.tilegame.game.sokoban.jfx.controller;

import javafx.scene.input.MouseButton;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.EventOfClient92GameEvent;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;
import timmax.tilegame.transport.TransportOfClient;

public class SokobanGameStackPaneController extends GameStackPaneController {
    public SokobanGameStackPaneController(TransportOfClient transportOfClient) {
        super(transportOfClient);
    }

    @Override
    public void onMousePrimaryClick(int x, int y) {
        System.out.println("onMousePrimaryClick. x = " + x + ", y = " + y);
        // ToDo: отправить серверу команду
        GameCommand gameCommand = new GameCommandMouseClick(x, y, MouseButton.PRIMARY);
        EventOfClient eventOfClient = new EventOfClient92GameEvent(gameCommand);
        transportOfClient.send(eventOfClient);
    }

    @Override
    public void onMouseSecondaryClick(int x, int y) {
        System.out.println("onMouseSecondaryClick. x = " + x + ", y = " + y);
    }
}
