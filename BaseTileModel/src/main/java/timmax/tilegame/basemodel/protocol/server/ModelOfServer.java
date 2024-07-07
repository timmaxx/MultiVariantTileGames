package timmax.tilegame.basemodel.protocol.server;

import java.util.Set;

import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.GameStatus;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventGameOver;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.EventOfServer92GameEvent;

import static timmax.tilegame.basemodel.GameStatus.FORCE_RESTART_OR_CHANGE_LEVEL;
import static timmax.tilegame.basemodel.GameStatus.VICTORY;

// Абстрактная модель. Она уже может:
// - хранить перечень удалённых выборок (которым нужно отправлять сообщения об игровых событиях),
// - отправлять сообщения об игровых событиях (с помощью ссылки на абстрактный транспорт).
// ToDo: Дополнить функционалом:
//       - по хранению перечня игровых контроллеров (от которых можно принимать сигналы управления игрой),
//       -- при игре с более чем одним игроком, контроллеры нужно учитывать по отдельному участнику.
// ToDo: Переименовать в GameMatch (и интерфейс тоже).
public abstract class ModelOfServer<ClientId> implements IModelOfServer {
    protected static final Logger logger = LoggerFactory.getLogger(ModelOfServer.class);

    public static final String PARAM_NAME_WIDTH = "Width";
    public static final String PARAM_NAME_HEIGHT = "Height";

    protected final GameType gameType;

    // ToDo: Сейчас здесь одна переменная типа RemoteClientStateAutomaton и ClientId. И для одного игрока вполне норм.
    //       Но для для двух (а возможно и более игроков) или если какой-то участник игры, не являющийся игроком будет
    //       работать в отдельном клиенте, придётся создавать какую-то коллекцию, в которой и будет описание игроков
    //       или других участников.
    protected final RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton;
    protected final ClientId clientId;

    private GameStatus gameStatus;

    public ModelOfServer(
            GameType gameType,
            RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton,
            ClientId clientId) {
        this.gameType = gameType;
        this.remoteClientStateAutomaton = remoteClientStateAutomaton;
        this.clientId = clientId;
    }

    protected final GameStatus getGameStatus() {
        return gameStatus;
    }

    protected final void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    protected void createNewGame(int width, int height) {
        gameStatus = GameStatus.GAME;
        GameEventNewGame gameEventNewGame = new GameEventNewGame(width, height);
        sendGameEvent(gameEventNewGame);
    }

    protected void createNewGame(int width, int height, Color defaultCellColor, Color defaultTextColor, String defaultCellValue) {
        gameStatus = GameStatus.GAME;
        GameEventNewGame gameEventNewGame = new GameEventNewGame(width, height, defaultCellColor, defaultTextColor, defaultCellValue);
        sendGameEvent(gameEventNewGame);
    }

    public void sendGameEvent(GameEvent gameEvent) {
        // ToDo: Пробовал сразу написать так:
        //       for (String viewName : remoteClientState.getSetOfViewName())
        //       Но такой вариант даже не компилировался.
        //       Разобраться!
        Set<String> setOfViewName = remoteClientStateAutomaton.getSetOfViewName();
        for (String viewName : setOfViewName) {
            EventOfServer eventOfServer = new EventOfServer92GameEvent(viewName, gameEvent);
            remoteClientStateAutomaton.getTransportOfServer().sendEventOfServer(clientId, eventOfServer);
        }
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

    // interface IModelOfServer:
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
}
