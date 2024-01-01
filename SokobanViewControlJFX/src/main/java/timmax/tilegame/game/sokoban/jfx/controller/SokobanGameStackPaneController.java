package timmax.tilegame.game.sokoban.jfx.controller;

import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;
import timmax.tilegame.transport.TransportOfClient;

public class SokobanGameStackPaneController extends GameStackPaneController {
    public SokobanGameStackPaneController(TransportOfClient transportOfClient) {
        super(transportOfClient);
    }

    @Override
    public void onMousePrimaryClick(int x, int y) {
        System.out.println("x = " + x + ", y = " + y);
    }

    @Override
    public void onMouseSecondaryClick(int x, int y) {
    }
}
