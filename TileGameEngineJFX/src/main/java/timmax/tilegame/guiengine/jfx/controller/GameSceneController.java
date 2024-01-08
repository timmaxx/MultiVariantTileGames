package timmax.tilegame.guiengine.jfx.controller;

import javafx.scene.input.KeyCode;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.EventOfClient92GameCommand;
import timmax.tilegame.transport.TransportOfClient;

// Содержит контролеры по принятию событий от клавиатуры над GameScene
public abstract class GameSceneController extends BaseController {
    public GameSceneController(TransportOfClient transportOfClient) {
        super(transportOfClient);
    }

    public void onKeyPressed(KeyCode keyCode) {
        System.out.println("class GameSceneController. method onKeyPressed");
        System.out.println("  keyCode = " + keyCode);
        GameCommand gameCommand = new GameCommandKeyPressed(keyCode);
        EventOfClient eventOfClient = new EventOfClient92GameCommand(gameCommand);
        sendEventOfClient(eventOfClient);
    }
}
