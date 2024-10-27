package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.XYOffsetOne;

// For boxes and player.
public interface Movable<XYOFFSETONE extends XYOffsetOne> {
    void move(XYOFFSETONE xyOffset);
}
