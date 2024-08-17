package timmax.tilegame.basemodel.protocol.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.GameStatus;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventGameOver;

import java.util.Map;

import static timmax.tilegame.basemodel.GameStatus.FORCE_RESTART_OR_CHANGE_LEVEL;
import static timmax.tilegame.basemodel.GameStatus.VICTORY;

// Абстрактная модель. Она уже может:
// - хранить перечень удалённых выборок (которым нужно отправлять сообщения об игровых событиях),
// - отправлять сообщения об игровых событиях (с помощью ссылки на абстрактный транспорт).
// ToDo: Дополнить функционалом:
//       - по хранению перечня игровых контроллеров (от которых можно принимать сигналы управления игрой),
//       -- при игре с более чем одним игроком, контроллеры нужно учитывать по отдельному участнику.
// ToDo: Удалить параметр <ClientId> из класса.
//       Сейчас этот параметр используется, что-бы помнить ид клиента (а потом и нескольких клиентов), но если
//       ClientId здесь не запоминать, ведь он и так должен быть в какой-то мапе (возможно двусторонней) соответствовать
//       какому-то RemoteClientStateAutomaton.
//       Тогда, в т.ч. уйдёт предупреждение в строке:
//       allMinesweeperObjects = levelGenerator.getLevel(width, height, percentsOfMines);
//       в классе GameMatchOfMinesweeper.
public abstract class GameMatch<ClientId> implements IGameMatch {
    protected static final Logger logger = LoggerFactory.getLogger(GameMatch.class);

    public static final String PARAM_NAME_WIDTH = "Width";
    public static final String PARAM_NAME_HEIGHT = "Height";

    protected final GameType gameType;

    // ToDo: Сейчас здесь одна переменная типа RemoteClientStateAutomaton и ClientId. И для одного игрока вполне норм.
    //       Но для для двух (а возможно и более игроков) или если какой-то участник игры, не являющийся игроком будет
    //       работать в отдельном клиенте, придётся создавать какую-то коллекцию, в которой и будет описание игроков
    //       или других участников.
    protected final RemoteClientStateAutomaton remoteClientStateAutomaton;
    protected final ClientId clientId;

    protected int width;
    protected int height;
    private Map<String, Integer> paramsOfModelValueMap;
    private GameStatus gameStatus;
    private boolean isPlaying;

    public GameMatch(
            GameType gameType,
            RemoteClientStateAutomaton remoteClientStateAutomaton,
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

    protected void verifyGameMatchIsPlaying() {
        if (isPlaying) {
            throw new RuntimeException("You cannot create new game because the match is in state 'gameMatchIsPlaing'!");
        }
    }

    // Посылает игровое событие всем выборкам.
    public void sendGameEventToAllViews(GameEvent gameEvent) {
        gameType.sendGameEventToAllViews(gameEvent, remoteClientStateAutomaton, clientId);
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
            start();
            return true;
        }
        return false;
    }

    protected void setGameMatchIsPlayingTrue() {
        isPlaying = true;
    }

    // interface IGameMatch
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

    @Override
    public void setParamsOfModelValueMap(int width, int height, Map<String, Integer> mapOfParamsOfModelValue) {
        this.width = width;
        this.height = height;
        this.paramsOfModelValueMap = mapOfParamsOfModelValue;
    }

    @Override
    public int paramsOfModelValueMapGet(String paramName) {
        return paramsOfModelValueMap.get(paramName);
    }

    // interface IGameMatchX
    @Override
    public void start(int width, int height, Map<String, Integer> paramsOfModelValueMap) {
        verifyGameMatchIsPlaying();
        gameStatus = GameStatus.GAME;
        isPlaying = false;
    }

    @Override
    public void resume() {
        // ToDo: Отправить клиенту:
        //       1. Размеры главной выборки матча и умолчательные характеристики для построение пустого поля
        //          (но возможно, это в более раннем событии следует передать) для построения пустой выборки главного поля.
        //       2. Объекты матча статические (например для Сокобана: стены или дома).
        //       3. Объекты матча динамические. Например:
        //          1. Для Сокобана: игрок, ящики.
        //          2. Для Сапёра: флаги и количество мин на открытых плитках.
        System.out.println("GameMatch :: void resumeGameMatch()");
        System.out.println("Не реализован...");
    }

    // interface IGameMatchXDto
    @Override
    public String getId() {
        return super.toString();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public Map<String, Integer> getParamsOfModelValueMap() {
        return paramsOfModelValueMap;
    }
}
