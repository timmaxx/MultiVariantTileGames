package timmax.tilegame.game.minesweeper.model.gameevent;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

public class GameEventOneTileChangeFlag extends GameEventOneTile {
    private final boolean isFlag;


    public GameEventOneTileChangeFlag(
            int x,
            int y,
            boolean isFlag) // Странно, не isFlag, а flag
    {
        super(x, y);
        this.isFlag = isFlag;
    }

    public boolean isFlag() {
        return isFlag;
    }
}