package timmax.tilegame.basemodel.protocol.server;

import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.GameStatus;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventGameOver;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;

import java.util.Map;

import static timmax.tilegame.basemodel.GameStatus.FORCE_RESTART_OR_CHANGE_LEVEL;
import static timmax.tilegame.basemodel.GameStatus.VICTORY;

// Абстрактная модель. Она уже может:
// - хранить перечень удалённых выборок (которым нужно отправлять сообщения об игровых событиях),
// - отправлять сообщения об игровых событиях (с помощью ссылки на абстрактный транспорт).
// ToDo: Дополнить функционалом:
//       - по хранению перечня игровых контроллеров (от которых можно принимать сигналы управления игрой),
//       -- при игре с более чем одним игроком, контроллеры нужно учитывать по отдельному участнику.
public abstract class GameMatch<ClientId> implements IGameMatch {
    protected static final Logger logger = LoggerFactory.getLogger(GameMatch.class);

    public static final String PARAM_NAME_WIDTH = "Width";
    public static final String PARAM_NAME_HEIGHT = "Height";

    protected final GameType gameType;

    // ToDo: Сейчас здесь одна переменная типа RemoteClientStateAutomaton и ClientId. И для одного игрока вполне норм.
    //       Но для для двух (а возможно и более игроков) или если какой-то участник игры, не являющийся игроком будет
    //       работать в отдельном клиенте, придётся создавать какую-то коллекцию, в которой и будет описание игроков
    //       или других участников.
    protected final RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton;
    protected final ClientId clientId;

    private Map<String, Integer> paramsOfModelValueMap;
    private GameStatus gameStatus;

    public GameMatch(
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

    // Посылает событие всем выборкам.
    public void sendGameEvent(GameEvent gameEvent) {
        remoteClientStateAutomaton.sendGameEventToAllViews(clientId, gameEvent);
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

    public void setParamsOfModelValueMap(Map<String, Integer> mapOfParamsOfModelValue) {
        this.paramsOfModelValueMap = mapOfParamsOfModelValue;
    }

    // ToDo: Создать метод в классе GameMatch, который сразу будет как paramsOfModelValueMap.get(PARAM_NAME_WIDTH).
    //       И тогда удалить getParamsOfModelValueMap().
    public Map<String, Integer> getParamsOfModelValueMap() {
        return paramsOfModelValueMap;
    }

    // interface IGameMatch:
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
