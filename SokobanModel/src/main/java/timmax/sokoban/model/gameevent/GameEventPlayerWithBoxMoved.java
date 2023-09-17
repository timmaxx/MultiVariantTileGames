package timmax.sokoban.model.gameevent;

public class GameEventPlayerWithBoxMoved extends GameEventPlayerMoved {
    private final int newBoxX;
    private final int newBoxY;

    public GameEventPlayerWithBoxMoved( int oldX, int oldY, int newX, int newY, int newBoxX, int newBoxY) {
        super( oldX, oldY, newX, newY);
        this.newBoxX = newBoxX;
        this.newBoxY = newBoxY;
    }

    public int getNewBoxX( ) {
        return newBoxX;
    }

    public int getNewBoxY( ) {
        return newBoxY;
    }
}