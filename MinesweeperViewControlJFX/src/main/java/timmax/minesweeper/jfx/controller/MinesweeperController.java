package timmax.minesweeper.jfx.controller;

import timmax.minesweeper.model.MinesweeperModel;

public class MinesweeperController {
    private final MinesweeperModel minesweeperModel;

    public MinesweeperController( MinesweeperModel minesweeperModel) {
        this.minesweeperModel = minesweeperModel;
    }

    public void onMouseLeftClick( int x, int y) {
        minesweeperModel.open( x, y);
    }

    public void onMouseRightClick( int x, int y) {
        minesweeperModel.inverseFlag( x, y);
    }
}