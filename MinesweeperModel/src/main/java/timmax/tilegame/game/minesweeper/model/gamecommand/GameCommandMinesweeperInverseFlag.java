package timmax.tilegame.game.minesweeper.model.gamecommand;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gamecommand.GameCommandOneTile;

import timmax.tilegame.game.minesweeper.model.MinesweeperModel;

public class GameCommandMinesweeperInverseFlag extends GameCommandOneTile {
    public GameCommandMinesweeperInverseFlag(
            int x,
            int y) {
        super(x, y);
    }

    @Override
    public void execute(BaseModel baseModel) {
        ((MinesweeperModel) baseModel).inverseFlag(getX(), getY());
    }
}