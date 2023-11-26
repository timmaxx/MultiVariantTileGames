package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.GameStatus;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;
import timmax.tilegame.transport.TransportOfModel;

// Абстрактная модель. Она уже может:
// - хранить перечень удалённых выборок (которым нужно отправлять сообщения об игровых событиях),
// - отправлять сообщения об игровых событиях (с помощью ссылки на абстрактный транспорт).
// Todo: Дополнить функционалом:
// - по хранению перечня игровых контроллеров (от которых можно принимать сигналы управления игрой),
// -- при игре с более чем одним игроком, контроллеры нужно учитывать по отдельному участнику.
public abstract class ModelOfServer implements IModelOfServer {
    private final static int MIN_WIDTH = 2;
    private final static int MAX_WIDTH = 100;
    private final static int MIN_HEIGHT = 2;
    private final static int MAX_HEIGHT = 100;

    private final SetOfRemoteView setOfRemoteViews;

    private GameStatus gameStatus;


    public ModelOfServer(TransportOfModel transportOfModel) {
        setOfRemoteViews = new SetOfRemoteView(transportOfModel);
    }

    protected void createNewGame(int width, int height) {
        validateWidthHeight(width, height);
        gameStatus = GameStatus.GAME;
        sendGameEvent(new GameEventNewGame(width, height));
    }

    public void sendGameEvent(GameEvent gameEvent) {
        setOfRemoteViews.sendGameEvent(gameEvent);
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
    public final void addRemoteView(RemoteView remoteView) {
        setOfRemoteViews.add(remoteView);
    }
}