package timmax.sokoban.model.gameobject;

public class Box extends CollisionObject implements Movable {

    public Box( int x, int y) {
        super( x, y);
    }

    @Override
    public void move( Direction direction) {
        switch ( direction) {
            case UP:    y--; break;
            case DOWN:  y++; break;
            case LEFT:  x--; break;
            case RIGHT: x++; break;
            default:         break;
        }
    }

    @Override
    public boolean equals( Object obj) {
        if ( obj == null) {
            return false;
        }
        if ( this == obj) {
            return true;
        }
        if ( !( obj instanceof Box)) {
            return false;
        }
        Box box = ( Box)obj;
        return x == box.x && y == box.y;
    }
}