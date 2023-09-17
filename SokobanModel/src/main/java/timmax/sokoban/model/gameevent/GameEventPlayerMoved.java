package timmax.sokoban.model.gameevent;

import timmax.basetilemodel.gameevent.GameEvent;

public class GameEventPlayerMoved extends GameEvent {
    private final int oldX;
    private final int oldY;
    private final int newX;
    private final int newY;

    public GameEventPlayerMoved( int oldX, int oldY, int newX, int newY) {
        this.oldX = oldX;
        this.oldY = oldY;
        this.newX = newX;
        this.newY = newY;
    }

    public int getOldX( ) {
        return oldX;
    }

    public int getOldY( ) {
        return oldY;
    }

    public int getNewX( ) {
        return newX;
    }

    public int getNewY( ) {
        return newY;
    }
}