package timmax.tilegame.basemodel.protocol.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.GameMatchStatus;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameevent.GameEventGameOver;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.gameobject.OneTileGameObjectsPlacement;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchExtendedDto;

import java.util.Map;
import java.util.Set;

import static timmax.tilegame.basemodel.GameMatchStatus.*;

//  Done:   Сейчас можно создать экземпляр класса, не передав ему параметры матча
//          (Ш х В, расстановка объектов, какого игрока ход). И поэтому, потом вызывается сеттер для параметров матча и
//          потом он стартует.
//  ToDo:   Следует инициализировать матч только при создании экземпляра. Так будет целостнее картина:
//          - конструктор должен проверить целостность параметров матча.
//          - сеттер параметров матча станет не нужным.
//  ToDo:   Ввести отдельную группу классов для хранения расстановки объектов матча:
//          - вижу, что это можно организовать как минимум двумя вариантами:
//              - каждый элемент несёт информацию только о типе объекта и координатах
//                (тогда записей будет столько сколько и количество объектов),
//              - вся карта в сплошной последовательности плиток и для каждой плитки наличие в ней объектоа(ов) без
//                координат
//                (тогда записей будет ровно Ш х В).
//          - расстановка всех элементов должна удовлетворять правилам типа игры.
//              - ограничение на количество объектов определённого типа на одной плитке:
//                  Например для Сокобан не может быть, что-бы:
//                  - на одной плитке был-бы и дом и стена,
//                  - на одной плитке был-бы и игрок и ящик.
//                  Например для Шахмат не может быть, что-бы:
//                  - на одной плитке было-бы более одного объекта.
//              - ограничение на количество объектов определённого типа на всей доске:
//                  Например для Шахмат не может быть, что-бы:
//                  - хотя-бы у одной из сторон не было-бы короля или королей было-бы несколько,
//              - ограничения на взаимное расположение объектов:
//                  Например для Шахмат не может быть, что-бы:
//                  - короли обоих противников были-бы одновременно под боем.
//  ToDo:   Ввести отдельную группу классов для хранения последовательности всех ходов матча:
//          - каждый ход должен соответствовать правилам типа игры
//              Например для Сокобан:
//                  - нельзя переместить игрока более чем на одну плитку по горизонтали или вертикали.
//              Например для Шахмат:
//                  - нельзя переместить слона как коня или перепрыгнув ч/з фигуру.
//          - ход может быть принят, если он не приведёт к неправильной расстановке (примеры см. в описании предыдущего класса).
//          После каждого хода нужно проверять не достигнут-ли конец партии. А если достигнут, то зафиксировать
//          конец партии и успешность (победа/поражение) каждого игрока и не принимать следующие ходы.
//  ToDo:   Ввести переменные в которых хранить:
//          - первичную расстановку объектов,
//          - последовательность всех ходов матча,
//          - текущая (т.е. после последнего хода) расстановка объектов (не обязательно, но может понадобиться для быстрого просмотра).
//  ToDo:   Дополнить функционалом:
//          - по хранению перечня игровых контроллеров (от которых можно принимать сигналы управления игрой),
//          -- при игре с более чем одним игроком, контроллеры нужно учитывать по отдельному участнику.
//  ToDo:   Удалить параметр <ClientId> из класса.
//          Если RemoteClientStateAutomaton<ClientId> вынести из класса, то это и решится!
//          Сейчас этот параметр используется, что-бы помнить ид клиента (а потом и нескольких клиентов), но если
//          ClientId здесь не запоминать, ведь он и так должен быть в какой-то мапе (возможно двусторонней)
//          соответствовать какому-то RemoteClientStateAutomaton.
//          Тогда, в т.ч. уйдёт предупреждение в строке:
//          allMinesweeperObjects = levelGenerator.getLevel(width, height, percentsOfMines);
//          в классе GameMatchOfMinesweeper.
public abstract class GameMatch<ClientId> implements IGameMatch {
    protected static final Logger logger = LoggerFactory.getLogger(GameMatch.class);

    //  Warning:(34, 21) Raw use of parameterized class 'GameType'
    protected final GameType gameType;

    protected /*final*/ OneTileGameObjectsPlacement oneTileGameObjectsPlacement;

    //  ToDo:   Сейчас здесь одна переменная типа RemoteClientStateAutomaton. И для одного игрока вполне норм.
    //          Но для для двух (а возможно и более игроков) или если какой-то участник игры, не являющийся игроком
    //          (например наблюдатель) будет работать в отдельном клиенте, придётся создавать какую-то коллекцию,
    //          в которой и будет описание игроков или других участников.
    //  ToDo:   Ссылку на автомат состояний, да ещё и параметризацию его по типу идентификации клиента (на 28.08.2024 -
    //          это WebSocket), видимо неправильно хранить здесь.
    //          Сейчас переменная remoteClientStateAutomaton используется для того, что-бы отправить определённому
    //          клиенту игровое событие.
    //          А вот правильно было-бы хранить множество со ссылками на игроков
    //          (с одним элементом - для игр с одним игроком, с двумя элементами - для игр с двумя игроками).
    protected final RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton;

    private Map<String, Integer> paramsOfModelValueMap;
    private GameMatchStatus status;

    //  ToDo:   Перечень параметров согласовывать также и в
    //          - GameType,
    //          - RemoteClientState06GameMatchSetSelected :: void selectGameType(GameType gameType, Set<IGameMatch> gameMatchXSet).
    //            Там создаётся конструктор через рекурсию. Но после рефакторинга, создание конструктора должно уйти в
    //            GameType.
    public GameMatch(
            GameType gameType,
            RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton) {
        this.gameType = gameType;
        this.status = NOT_STARTED;
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
        //  Warning:(70, 9) Unchecked call to 'sendGameEventToAllViews(GameEvent, RemoteClientStateAutomaton<ClientId>)' as a member of raw type 'timmax.tilegame.basemodel.protocol.server.GameType'
        gameType.sendGameEventToAllViews(gameEvent, remoteClientStateAutomaton);
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
        if (getStatus() != GameMatchStatus.GAME) {
            // start();
            return true;
        }
        return false;
    }

    protected void setStatusIsGame() {
        if (status != PAUSE && status != GAME) {
            throw new RuntimeException("You cannot set game satus to GAME! (gameMatchStatus = " + status + ")");
        }
        status = GAME;
    }

    public GameMatchExtendedDto newGameMatchExtendedDto(Set<GameEventOneTile> gameEventOneTileSet) {
        return new GameMatchExtendedDto(
                getId(),
                getStatus(),
                paramsOfModelValueMap,
                gameEventOneTileSet
        );
    }

    public GameMatchDto getGameMatchDto() {
        return new GameMatchDto(getId(), getStatus(), paramsOfModelValueMap);
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
        status = GameMatchStatus.GAME;
    }

    // interface IGameMatchX
    // ToDo: start() (т.е. без параметров) должен вызывать start(...)
    @Override
    public GameMatchExtendedDto start(GameMatchExtendedDto gameMatchExtendedDto) {
        // ToDo: Отправить клиенту:
        //       1. Размеры главной выборки матча и умолчательные характеристики для построение пустого поля
        //          (но возможно, это в более раннем событии следует передать) для построения пустой выборки главного поля.
        //       2. Объекты матча статические (например для Сокобана: стены или дома).
        //       3. Объекты матча динамические. Например:
        //          1. Для Сокобана: игрок, ящики.
        //          2. Для Сапёра: флаги и количество мин на открытых плитках.

        throwExceptionIfIsPlaying();
        this.paramsOfModelValueMap = gameMatchExtendedDto.getParamsOfModelValueMap();
        status = GameMatchStatus.GAME;
        return gameMatchExtendedDto;
    }

    @Override
    public int getFromParamsOfModelValueMap(String paramName) {
        return paramsOfModelValueMap.get(paramName);
    }

    // interface IGameMatchXDto
    @Override
    public String getId() {
        return super.toString();
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
}
