package timmax.sokoban.model.gameobject;

import timmax.basetilemodel.Direction;

// For boxes and player.
public interface Movable {
    void move(Direction direction);
}