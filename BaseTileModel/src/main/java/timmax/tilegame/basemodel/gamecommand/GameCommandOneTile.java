package timmax.tilegame.basemodel.gamecommand;

public abstract class GameCommandOneTile extends GameCommand {
    private final int x;
    private final int y;

    public GameCommandOneTile(int x, int y) {
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