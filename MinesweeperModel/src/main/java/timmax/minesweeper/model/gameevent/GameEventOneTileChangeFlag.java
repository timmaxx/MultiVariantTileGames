package timmax.minesweeper.model.gameevent;

import timmax.basetilemodel.gameevent.GameEventOneTile;

public class GameEventOneTileChangeFlag extends GameEventOneTile {
    private final boolean isFlag;

    public GameEventOneTileChangeFlag( int x, int y, boolean isFlag) {
        super( x, y);
        this.isFlag = isFlag;
    }

    public boolean isFlag( ) {
        return isFlag;
    }
}