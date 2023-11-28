package timmax.tilegame.game.minesweeper.jfx.controller;

import timmax.tilegame.transport.TransportOfController;

import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;

import timmax.tilegame.game.minesweeper.model.gamecommand.GameCommandMinesweeperInverseFlag;
import timmax.tilegame.game.minesweeper.model.gamecommand.GameCommandMinesweeperOpen;

public class MinesweeperGameStackPaneController extends GameStackPaneController {
    public MinesweeperGameStackPaneController(TransportOfController transportOfController) {
        super(transportOfController);
    }

    @Override
    public void onMousePrimaryClick(int x, int y) {
        sendCommand(new GameCommandMinesweeperOpen(x, y));
    }

    @Override
    public void onMouseSecondaryClick(int x, int y) {
        sendCommand(new GameCommandMinesweeperInverseFlag(x, y));
    }
}