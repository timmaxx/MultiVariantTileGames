package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.XYOffset;

// For boxes and player.
public interface IMovable {
    void move(XYOffset xyOffset);
}
