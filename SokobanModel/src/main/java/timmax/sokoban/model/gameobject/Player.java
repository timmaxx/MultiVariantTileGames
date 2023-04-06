package timmax.sokoban.model.gameobject;

public class Player extends CollisionObject implements Movable {

    public Player( int x, int y) {
        super( x, y);
    }

    @Override
    public void move( Direction direction) {
        switch ( direction) {
            case UP:    y--; break;
            case DOWN:  y++; break;
            case LEFT:  x--; break;
            case RIGHT: x++; break;
        }
        return;
    }
}