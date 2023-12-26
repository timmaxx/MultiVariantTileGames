package timmax.tilegame.basemodel;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfServer;

// Базовая модель
public abstract class ServerBaseModel<T> implements BaseModel {
    private final static int MIN_WIDTH = 2;
    private final static int MAX_WIDTH = 100;
    private final static int MIN_HEIGHT = 2;
    private final static int MAX_HEIGHT = 100;

    private GameStatus gameStatus;
    protected TransportOfServer<T> transportOfServer;


    public ServerBaseModel(TransportOfServer<T> transportOfServer) {
        this.transportOfServer = transportOfServer;
    }

    @Override
    public void addView(View view) {
    }

    protected void createNewGame(int width, int height) {
        validateWidthHeight(width, height);
        gameStatus = GameStatus.GAME;
        sendGameEvent(new GameEventNewGame(width, height));
    }

    public void sendGameEvent(GameEvent gameEvent) {
        transportOfServer.sendGameEvent(gameEvent);
    }

    private static void validateWidthHeight(int width, int height) {
        if (width >= MIN_WIDTH && width <= MAX_WIDTH && height >= MIN_HEIGHT && height <= MAX_HEIGHT) {
            return;
        }
        throw new RuntimeException(
                "It must be width >= " + MIN_WIDTH + " && width <= " + MAX_WIDTH +
                        " && height >= " + MIN_HEIGHT + " && height <= " + MAX_HEIGHT +
                        ". But width = " + width + ", height = " + height + ".");
    }

    protected GameStatus getGameStatus() {
        return gameStatus;
    }

    protected void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    protected boolean verifyGameStatusNotGameAndMayBeCreateNewGame() {
        if (getGameStatus() != GameStatus.GAME) {
            createNewGame();
            return true;
        }
        return false;
    }
}
