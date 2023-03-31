package timmax.minesweeper.controller;

import timmax.minesweeper.model.Model;

public class Controller {
    private final Model model;

    public Controller( Model model) {
        this.model = model;
    }

    public void onMouseLeftClick( int x, int y) {
        model.openTile( x, y);
    }

    public void onMouseRightClick( int x, int y) {
        model.markTile( x, y);
    }
}