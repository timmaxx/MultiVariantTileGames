package timmax.sokoban.model.gameobject;

import timmax.basetilemodel.Direction;
import timmax.basetilemodel.XY;

public abstract class CollisionMovableObject extends CollisionObject implements Movable {
    public CollisionMovableObject( int x, int y) {
        super( x, y);
    }

    public void move( Direction direction) {
        XY xy = add( direction);
        x = xy.getX( );
        y = xy.getY( );
    }
}