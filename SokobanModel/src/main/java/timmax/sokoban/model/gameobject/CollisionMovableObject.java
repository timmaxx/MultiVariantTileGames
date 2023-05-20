package timmax.sokoban.model.gameobject;

import timmax.basetilemodel.tile.*;

public abstract class CollisionMovableObject extends CollisionObject implements Movable {
    public CollisionMovableObject( int x, int y) {
        super( x, y);
    }

    public void move( Direction direction) {
        Tile tile = add( direction);
        x = tile.getX( );
        y = tile.getY( );
    }
}