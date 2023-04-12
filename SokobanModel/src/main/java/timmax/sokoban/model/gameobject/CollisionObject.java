package timmax.sokoban.model.gameobject;

import timmax.basetilemodel.Direction;
import timmax.basetilemodel.XY;

// For Walls, Boxes and player
abstract public class CollisionObject extends XY {

    public CollisionObject( int x, int y) {
        super( x, y);
        // System.out.println( this.tile);
        // ((SokobanTile)tile).
    }

    public boolean isCollision( CollisionObject collisionObject, Direction direction) {
        // return add( direction).equals( collisionObject);
        // ToDo: Два раза вызывается add( direction) - нехорошо.
        // Нужно координаты сравнивать.
        return add( direction).getX() == collisionObject.x && add( direction).getY() == collisionObject.y;
    }
}