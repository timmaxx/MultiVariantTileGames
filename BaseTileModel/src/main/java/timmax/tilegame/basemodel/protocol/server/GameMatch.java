package timmax.tilegame.basemodel.protocol.server;

import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.GameStatus;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventGameOver;
import timmax.tilegame.basemodel.gameevent.GameEventNewGame;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.EventOfServer92GameEvent;
import timmax.tilegame.baseview.View;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    private final Set<String> viewNameSet;

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
        this.viewNameSet = new HashSet<>();

        // ToDo: Сейчас foreach работает и с ключём и со значением (аналогично как в классе LocalClientState),
        //       Но здесь достаточно только с ключём.
        for (Map.Entry<String, Class<? extends View>> entry : gameType.getMapOfViewNameViewClass().entrySet()) {
            viewNameSet.add(entry.getKey());
        }
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
        sendGameEventToAllViews(gameEventNewGame);
    }

    protected void createNewGame(int width, int height, Color defaultCellColor, Color defaultTextColor, String defaultCellValue) {
        gameStatus = GameStatus.GAME;
        GameEventNewGame gameEventNewGame = new GameEventNewGame(width, height, defaultCellColor, defaultTextColor, defaultCellValue);
        sendGameEventToAllViews(gameEventNewGame);
    }

    // Посылает игровое событие всем выборкам.
    public void sendGameEventToAllViews(GameEvent gameEvent) {
        // ToDo: Пробовал сразу написать так:
        //       for (String viewName : remoteClientState.getSetOfViewName())
        //       Но такой вариант даже не компилировался.
        //       Разобраться!
        for (String viewName : viewNameSet) {
            EventOfServer eventOfServer = new EventOfServer92GameEvent(viewName, gameEvent);
            remoteClientStateAutomaton.sendEventOfServer(clientId, eventOfServer);
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

    // ToDo: Сеттер вероятно совсем не нужен, т.к. пусть лучше конструктор инициализирует.
    public void setParamsOfModelValueMap(Map<String, Integer> mapOfParamsOfModelValue) {
        this.paramsOfModelValueMap = mapOfParamsOfModelValue;
    }

    public int paramsOfModelValueMapGet(String paramName) {
        return paramsOfModelValueMap.get(paramName);
    }

    // interface IGameMatch:
    @Override
    public void win() {
        setGameStatus(GameStatus.VICTORY);
        sendGameEventToAllViews(new GameEventGameOver(VICTORY));
    }

    @Override
    public void restart() {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        setGameStatus(FORCE_RESTART_OR_CHANGE_LEVEL);
        sendGameEventToAllViews(new GameEventGameOver(FORCE_RESTART_OR_CHANGE_LEVEL));
    }
}
