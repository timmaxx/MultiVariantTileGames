package timmax.tilegame.basemodel.protocol.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.GameMatchStatus;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventGameOver;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;
import java.util.Set;

import static timmax.tilegame.basemodel.GameMatchStatus.*;

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
public abstract class GameMatch<ClientId> implements IGameMatch, Externalizable {
    protected static final Logger logger = LoggerFactory.getLogger(GameMatch.class);

    protected GameType gameType;
    private String id;

    // ToDo: Сейчас здесь одна переменная типа RemoteClientStateAutomaton. И для одного игрока вполне норм.
    //       Но для для двух (а возможно и более игроков) или если какой-то участник игры, не являющийся игроком будет
    //       работать в отдельном клиенте, придётся создавать какую-то коллекцию, в которой и будет описание игроков
    //       или других участников.
    protected RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton;

    private Map<String, Integer> paramsOfModelValueMap;
    private GameMatchStatus status;
    private Set<GameEventOneTile> gameEventOneTileSet;

    public GameMatch() {
    }

    //  ToDo:   Перечень параметров согласовывать также и в
    //          - GameType,
    //          - RemoteClientState06GameMatchSetSelected :: void selectGameType(GameType gameType, Set<IGameMatch> gameMatchSet).
    //            Там создаётся конструктор через рекурсию. Но после рефакторинга, создание конструктора должно уйти в
    //            GameType.
    public GameMatch(
            GameType gameType,
            RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton) {
        this.gameType = gameType;
        this.id = super.toString();
        setStatus(NOT_STARTED);
        this.remoteClientStateAutomaton = remoteClientStateAutomaton;
    }

    protected final void setStatus(GameMatchStatus status) {
        this.status = status;
    }

    protected void throwExceptionIfIsPlaying() {
        if (getStatus() == GameMatchStatus.GAME) {
            throw new RuntimeException("Wrong situation: getStatus() == GameMatchStatus.GAME");
        }
    }

    // Посылает игровое событие всем выборкам.
    public void sendGameEventToAllViews(GameEvent gameEvent) {
        gameType.sendGameEventToAllViews(gameEvent, remoteClientStateAutomaton);
    }

    protected final boolean verifyGameStatusNotGameAndMayBeCreateNewGame() {
        if (getStatus() == NOT_STARTED) {
            setStatusIsGame();
        }
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
        if (getStatus() != GameMatchStatus.GAME) {
            // start();
            return true;
        }
        return false;
    }

    protected void setStatusIsGame() {
        if (status != NOT_STARTED && status != PAUSE && status != GAME) {
            throw new RuntimeException("You cannot set game satus to GAME! (gameMatchStatus = " + status + ")");
        }
        setStatus(GAME);
    }

    //  ToDo:   Вместо предоставления доступа через геттер, лучше чтобы внутри класса была деятельность по обработке
    //          множества gameEventOneTileSet.
    public Set<GameEventOneTile> getGameEventOneTileSet() {
        return gameEventOneTileSet;
    }

    public void setGameEventOneTileSet(Set<GameEventOneTile> gameEventOneTileSet) {
        this.gameEventOneTileSet = gameEventOneTileSet;
    }

    // interface IGameMatch
    @Override
    public void win() {
        setStatus(GameMatchStatus.VICTORY);
        sendGameEventToAllViews(new GameEventGameOver(VICTORY));
    }

    @Override
    public void restart() {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        setStatus(FORCE_RESTART_OR_CHANGE_LEVEL);
        sendGameEventToAllViews(new GameEventGameOver(FORCE_RESTART_OR_CHANGE_LEVEL));
    }

    @Override
    public void setParamsOfModelValueMap(Map<String, Integer> paramsOfModelValueMap) {
        this.paramsOfModelValueMap = paramsOfModelValueMap;
    }

    // ToDo: start() (т.е. без параметров) должен вызывать start(...)
    @Override
    public GameMatch start(GameMatch gameMatch) {
        throwExceptionIfIsPlaying();
        this.paramsOfModelValueMap = gameMatch.getParamsOfModelValueMap();
        setStatus(GAME);
        return gameMatch;
    }

    @Override
    public int getFromParamsOfModelValueMap(String paramName) {
        return paramsOfModelValueMap.get(paramName);
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

    // interface IGameMatchX
    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getWidth() {
        return paramsOfModelValueMap.get(PARAM_NAME_WIDTH);
    }

    @Override
    public int getHeight() {
        return paramsOfModelValueMap.get(PARAM_NAME_HEIGHT);
    }

    @Override
    public GameMatchStatus getStatus() {
        return status;
    }

    @Override
    public Map<String, Integer> getParamsOfModelValueMap() {
        return paramsOfModelValueMap;
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameType);
        out.writeObject(id);
        out.writeObject(paramsOfModelValueMap);
        out.writeObject(status);
        out.writeObject(gameEventOneTileSet);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameType = (GameType) in.readObject();
        id = (String) in.readObject();
        paramsOfModelValueMap = (Map<String, Integer>) in.readObject();
        setStatus((GameMatchStatus) in.readObject());
        gameEventOneTileSet = (Set<GameEventOneTile>) in.readObject();
    }

    // class Object
    @Override
    public String toString() {
        return "GameMatch{" +
                "gameType.getGameTypeName()=" + gameType.getGameTypeName() +
                ", id=" + id +
                // ", remoteClientStateAutomaton=" + remoteClientStateAutomaton +
                ", paramsOfModelValueMap=" + paramsOfModelValueMap +
                ", status=" + status +
                ", gameEventOneTileSet=" + gameEventOneTileSet +
                '}';
    }
}
