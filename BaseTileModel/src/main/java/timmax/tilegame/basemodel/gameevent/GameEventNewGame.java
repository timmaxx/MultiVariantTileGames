package timmax.tilegame.basemodel.gameevent;

public class GameEventNewGame extends GameEvent {
    private final int width;
    private final int height;


    public GameEventNewGame( int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth( ) {
        return width;
    }

    public int getHeight( ) {
        return height;
    }
}