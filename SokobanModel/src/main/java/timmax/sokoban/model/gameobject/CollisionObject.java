package timmax.sokoban.model.gameobject;

abstract public class CollisionObject extends GameObject {

    public CollisionObject( int x, int y) {
        super( x, y);
    }

    public boolean isCollision( GameObject gameObject, Direction direction) {
        int dx = 0;
        int dy = 0;
        if ( direction == null) {
            return true;
        }
        switch ( direction) {
            case UP:    dy--; break;
            case DOWN:  dy++; break;
            case LEFT:  dx--; break;
            case RIGHT: dx++; break;
            default: return true;
        }
        if ( getX( ) + dx == gameObject.getX( ) && getY( ) + dy == gameObject.getY( )) {
            return true;
        }
        return false;
    }
}