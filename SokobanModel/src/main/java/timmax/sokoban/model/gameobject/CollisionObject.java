package timmax.sokoban.model.gameobject;

import timmax.tilegame.basemodel.tile.Direction;
import timmax.tilegame.basemodel.tile.Tile;

// For Walls, Boxes and player
abstract public class CollisionObject extends Tile {

    public CollisionObject( int x, int y) {
        super( x, y);
    }

    public boolean isCollision( CollisionObject collisionObject, Direction direction) {
        Tile tile = add( direction);
        return tile.getX( ) == collisionObject.x && tile.getY( ) == collisionObject.y;
    }
}