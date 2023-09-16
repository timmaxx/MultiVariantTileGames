package timmax.basetilemodel.gameevent;

import timmax.basetilemodel.GameStatus;

public class GameEventGameOver extends GameEvent {
    GameStatus gameStatus;

    public GameEventGameOver( GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    /*
    public GameStatus getGameStatus( ) {
        return gameStatus;
    }
    */
}