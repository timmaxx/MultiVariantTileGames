package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.tile.Direction;
import timmax.tilegame.basemodel.tile.Tile;

public abstract class CollisionMovableObject extends CollisionObject implements Movable {
    public CollisionMovableObject(int x, int y) {
        super(x, y);
    }

    public void move(Direction direction) {
        Tile tile = add(direction);
        x = tile.getX();
        y = tile.getY();
    }
}
