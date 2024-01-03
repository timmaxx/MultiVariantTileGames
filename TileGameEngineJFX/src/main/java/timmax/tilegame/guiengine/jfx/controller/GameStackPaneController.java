package timmax.tilegame.guiengine.jfx.controller;

import javafx.scene.input.MouseButton;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.EventOfClient92GameEvent;
import timmax.tilegame.transport.TransportOfClient;

// Содержит контролер по принятию событий от мыши над GameStackPane
public class GameStackPaneController extends BaseController {
    public GameStackPaneController(TransportOfClient transportOfClient) {
        super(transportOfClient);
    }

    public final void onMouseClick(MouseButton mouseButton, int x, int y) {
        System.out.println("onMouseClick. mouseButton = " + mouseButton + ", x = " + x + ", y = " + y);
        GameCommand gameCommand = new GameCommandMouseClick(x, y, mouseButton);
        EventOfClient eventOfClient = new EventOfClient92GameEvent(gameCommand);
        transportOfClient.send(eventOfClient);
    }

    // Отсюда логика по работе с кнопками мыши уходит.
    // ToDo: Удалить комментарии о ней (как и классы наследники и использующий их код) уже после того,
    //       как в серверной части будет сделан обработчик кликов мыши.
/*
    // Это старый вариант реализации для Minesweeper:
    @Override
    public void onMousePrimaryClick(int x, int y) {
        sendCommand(new GameCommandMinesweeperOpen(x, y));
    }

    @Override
    public void onMouseSecondaryClick(int x, int y) {
        sendCommand(new GameCommandMinesweeperInverseFlag(x, y));
    }
*/
}
