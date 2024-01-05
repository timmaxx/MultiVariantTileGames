package timmax.tilegame.basemodel.protocol.server;

import javafx.scene.paint.Color;

import timmax.tilegame.basemodel.GameStatus;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;
import timmax.tilegame.transport.TransportOfServer;

// Абстрактная модель. Она уже может:
// - хранить перечень удалённых выборок (которым нужно отправлять сообщения об игровых событиях),
// - отправлять сообщения об игровых событиях (с помощью ссылки на абстрактный транспорт).
// Todo: Дополнить функционалом:
// - по хранению перечня игровых контроллеров (от которых можно принимать сигналы управления игрой),
// -- при игре с более чем одним игроком, контроллеры нужно учитывать по отдельному участнику.
public abstract class ModelOfServer<T> implements IModelOfServer<T> {
    private final static int MIN_WIDTH = 1; // 2;
    private final static int MAX_WIDTH = 100;
    private final static int MIN_HEIGHT = 1; // 2;
    private final static int MAX_HEIGHT = 100;

    private final ListOfRemoteView<T> listOfRemoteViews;

    private GameStatus gameStatus;

    public ModelOfServer(TransportOfServer<T> transportOfServer) {
        listOfRemoteViews = new ListOfRemoteView<>(transportOfServer);
    }

    protected void createNewGame(int width, int height) {
        validateWidthHeight(width, height);
        gameStatus = GameStatus.GAME;
        GameEventNewGame gameEventNewGame = new GameEventNewGame(width, height);
        sendGameEvent(gameEventNewGame);
    }

    protected void createNewGame(int width, int height, Color defaultCellColor, Color defaultTextColor, String defaultCellValue) {
        validateWidthHeight(width, height);
        gameStatus = GameStatus.GAME;
        GameEventNewGame gameEventNewGame = new GameEventNewGame(width, height, defaultCellColor, defaultTextColor, defaultCellValue);
        sendGameEvent(gameEventNewGame);
    }

    public void sendGameEvent(GameEvent gameEvent) {
        listOfRemoteViews.sendGameEvent(gameEvent);
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

    protected final GameStatus getGameStatus() {
        return gameStatus;
    }

    protected final void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    protected final boolean verifyGameStatusNotGameAndMayBeCreateNewGame() {
        if (getGameStatus() != GameStatus.GAME) {
            createNewGame();
            return true;
        }
        return false;
    }

    @Override
    public final void addRemoteView(RemoteView<T> remoteView) {
        listOfRemoteViews.add(remoteView);
    }
}
