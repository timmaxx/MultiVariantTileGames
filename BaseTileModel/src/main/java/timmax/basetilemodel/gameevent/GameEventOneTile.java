package timmax.basetilemodel.gameevent;

public abstract class GameEventOneTile extends GameEvent {
    private final int x;
    private final int y;


    public GameEventOneTile( int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX( ) {
        return x;
    }

    public int getY( ) {
        return y;
    }
}