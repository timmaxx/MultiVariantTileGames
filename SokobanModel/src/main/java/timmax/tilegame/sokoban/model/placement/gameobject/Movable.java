package timmax.tilegame.sokoban.model.placement.gameobject;

import timmax.tilegame.sokoban.model.placement.primitives.SokobanXYOffset;

// For boxes and player.
public interface Movable {
    void move(SokobanXYOffset xyOffset);
}
