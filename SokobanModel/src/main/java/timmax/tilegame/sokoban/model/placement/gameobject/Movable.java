package timmax.tilegame.sokoban.model.placement.gameobject;

import timmax.tilegame.sokoban.model.placement.primitives.SokobanXYOffset;

// For boxes and player.
public interface Movable<XYOFFSETONE extends SokobanXYOffset> {
    void move(XYOFFSETONE xyOffset);
}
