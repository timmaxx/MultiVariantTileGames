package timmax.tilegame.basemodel.protocol.server;

import javafx.scene.paint.Color;

import timmax.tilegame.basemodel.GameStatus;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventGameOver;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;
import timmax.tilegame.transport.TransportOfServer;

import static timmax.tilegame.basemodel.GameStatus.FORCE_RESTART_OR_CHANGE_LEVEL;
import static timmax.tilegame.basemodel.GameStatus.VICTORY;

// Абстрактная модель. Она уже может:
// - хранить перечень удалённых выборок (которым нужно отправлять сообщения об игровых событиях),
// - отправлять сообщения об игровых событиях (с помощью ссылки на абстрактный транспорт).
// Todo: Дополнить функционалом:
// - по хранению перечня игровых контроллеров (от которых можно принимать сигналы управления игрой),
// -- при игре с более чем одним игроком, контроллеры нужно учитывать по отдельному участнику.
public abstract class ModelOfServer<ClientId> implements IModelOfServer<ClientId> {
    private final static int MIN_WIDTH = 1; // 2;
    private final static int MAX_WIDTH = 100;
    private final static int MIN_HEIGHT = 1; // 2;
    private final static int MAX_HEIGHT = 100;

    private final MapOfClientID_SetOfViewName<ClientId> mapOfClientID_SetOfViewName;

    private GameStatus gameStatus;

    public ModelOfServer(TransportOfServer<ClientId> transportOfServer) {
        mapOfClientID_SetOfViewName = new MapOfClientID_SetOfViewName<>(transportOfServer);
    }

    protected final GameStatus getGameStatus() {
        return gameStatus;
    }

    protected final void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    // Overiden methods from interface IModelOfServer:
    @Override
    public final void addRemoteView(RemoteView<ClientId> remoteView) {
        mapOfClientID_SetOfViewName.addRemoteView(remoteView);
    }

    @Override
    public void win() {
        setGameStatus(GameStatus.VICTORY);
        sendGameEvent(new GameEventGameOver(VICTORY));
    }

    @Override
    public void restart() {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        setGameStatus(FORCE_RESTART_OR_CHANGE_LEVEL);
        sendGameEvent(new GameEventGameOver(FORCE_RESTART_OR_CHANGE_LEVEL));
    }

    // Own methods of the class:
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
        mapOfClientID_SetOfViewName.sendGameEvent(gameEvent);
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

    protected final boolean verifyGameStatusNotGameAndMayBeCreateNewGame() {
        /*
        // Для Сапёра:
        //   newGame - генерация поля с тем-же количеством мин, но вновь применяя ГПСЧ.
        //   restart - все ячейки закрываются и играть с тем-же полем
        //             (вот только нужно-ли это именно для Самёра, ведь одна мина уже станет известна игроку?).
        if (getGameStatus() == FORCE_RESTART_OR_CHANGE_LEVEL) {
            restart(); // Если так, то будет зацикливание.
            return true;
        }
        */
        // Для Сокобан restart и newGame - видимо работает одинаково.
        if (getGameStatus() != GameStatus.GAME) {
            createNewGame();
            return true;
        }
        return false;
    }
}
