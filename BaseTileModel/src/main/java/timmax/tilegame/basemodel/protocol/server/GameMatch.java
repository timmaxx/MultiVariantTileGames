package timmax.tilegame.basemodel.protocol.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.GameMatchStatus;
import timmax.tilegame.basemodel.credential.User;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.placement.placementstate.GameObjectsPlacementStateAutomaton;
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
//  ToDo:   Ввести переменные в которых хранить:
//          - последовательность всех ходов матча.
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

//  Конкретный матч определённого типа игры.
public abstract class GameMatch<ClientId> implements IGameMatch {
    protected static final Logger logger = LoggerFactory.getLogger(GameMatch.class);

    //  Warning:(34, 21) Raw use of parameterized class 'GameType'
    protected final GameType gameType;

    private /*final*/ GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton;

    private GameMatchExtendedDto gameMatchExtendedDto;

    //  ToDo:   Сейчас здесь одна переменная типа RemoteClientStateAutomaton. И для одного игрока вполне норм.
    //          Но для двух (а возможно и более игроков), придётся создавать какую-то коллекцию,
    //          в которой и будет описание игроков.
    //          Замечания по поводу "наблюдателей" (это те пользователи, которые могут только следить за партией,
    //          т.е. контроллер оттуда не должен генерировать события мыши и(или) клавиатуры, а если даже они будут
    //          поступать, то сервер должен их игнорить):
    //          Они вообще не должны храниться в матче, но их нужно будет учитывать в неком классе "Матч в реал-тайме".
    //          С другой стороны, там-же стоит хранить и ссылки на ид клиентов не только наблюдателей, но и самих игроков.
    //  ToDo:   Ссылку на автомат состояний, да ещё и параметризацию его по типу идентификации клиента (на 28.08.2024 -
    //          это WebSocket), видимо неправильно хранить здесь.
    //          Сейчас переменная remoteClientStateAutomaton используется для того, что-бы отправить определённому
    //          клиенту игровое событие.
    //          А вот правильно было-бы хранить множество со ссылками на игроков
    //          (с одним элементом - для игр с одним игроком, с двумя элементами - для игр с двумя игроками).
    protected final RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton;

    //  Список игроков матча.
    private final MatchPlayerList matchPlayerList;

    private Map<String, Integer> paramsOfModelValueMap;
    private GameMatchStatus status;

    //  ToDo:   Перечень параметров согласовывать также и в:
    //          - GameType,
    //          - RemoteClientState06GameMatchSetSelected :: void setGameType(GameType gameType, Set<IGameMatch> gameMatchXSet).
    //            Там создаётся конструктор через рекурсию. Но после рефакторинга, создание конструктора должно уйти в
    //            GameType.
    public GameMatch(
            GameType gameType,
            RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton) {
        this.gameType = gameType;
        this.matchPlayerList = new MatchPlayerList(gameType.getCountOfGamers());
        this.status = NOT_STARTED;
        this.remoteClientStateAutomaton = remoteClientStateAutomaton;
    }

    public void setPlayer(int indexOfPlayer, User player) {
        if (this.status != NOT_STARTED) {
            throw new RuntimeException("Match is not in state NOT_STARTED");
        }
        matchPlayerList.setPlayer(indexOfPlayer, player);
    }

    public MatchPlayerList getMatchPlayerList() {
        return matchPlayerList;
    }

    public RemoteClientStateAutomaton<ClientId> getRemoteClientStateAutomaton() {
        return remoteClientStateAutomaton;
    }

    protected GameObjectsPlacementStateAutomaton getGameObjectsPlacementStateAutomaton() {
        return gameObjectsPlacementStateAutomaton;
    }

    //  ToDo:   Проверить, чтобы Расстановка была верифицируемой!
    protected void setGameObjectsPlacementStateAutomaton(GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton) {
        this.gameObjectsPlacementStateAutomaton = gameObjectsPlacementStateAutomaton;
    }

    public GameType getGameType() {
        return gameType;
    }

    protected final void setStatus(GameMatchStatus status) {
        this.status = status;
    }

    public GameMatchExtendedDto getGameMatchExtendedDto() {
        return gameMatchExtendedDto;
    }

    public void setGameMatchExtendedDto(GameMatchExtendedDto gameMatchExtendedDto) {
        this.gameMatchExtendedDto = gameMatchExtendedDto;
    }

    protected void throwExceptionIfIsPlaying() {
        if (getStatus() == GameMatchStatus.GAME) {
            logger.error("Wrong situation: getStatus() == GameMatchStatus.GAME.");
            throw new RuntimeException("Wrong situation: getStatus() == GameMatchStatus.GAME.");
        }
    }

    protected void throwExceptionIfThereIsNotDefinedPlayer() {
        if (matchPlayerList.isNotFull()) {
            logger.error("Wrong situation: playerList is not full.");
            throw new RuntimeException("Wrong situation: playerList is not full.");
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
        if (getStatus() != GameMatchStatus.GAME) {
            // start();
            return true;
        }
        return false;
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
/*
        setStatus(GameMatchStatus.VICTORY);
        remoteClientStateAutomaton.sendGameEventToAllViews(
                new GameEventGameOver(VICTORY),
                //  Warning:(144, 109) Unchecked assignment: 'java.util.Map' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'. Reason: 'gameType' has raw type, so result of getViewName_ViewClassMap is erased
                gameType.getViewName_ViewClassMap()
        );
*/
    }

    @Override
    public void restart() {
/*
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        setStatus(FORCE_RESTART_OR_CHANGE_LEVEL);
        remoteClientStateAutomaton.sendGameEventToAllViews(
                new GameEventGameOver(FORCE_RESTART_OR_CHANGE_LEVEL),
                //  Warning:(157, 17) Unchecked assignment: 'java.util.Map' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'. Reason: 'gameType' has raw type, so result of getViewName_ViewClassMap is erased
                gameType.getViewName_ViewClassMap()
        );
*/
    }

    @Override
    public void setParamsOfModelValueMap(Map<String, Integer> paramsOfModelValueMap) {
        this.paramsOfModelValueMap = paramsOfModelValueMap;
        status = GameMatchStatus.GAME;
    }

    // interface IGameMatchX
    //  ToDo:   start() (т.е. без параметров) должен вызывать start(...)
    @Override
    public void start(GameMatchExtendedDto gameMatchExtendedDto) {
        //  ToDo:   Отправить клиенту:
        //          1. Размеры главной выборки матча и умолчательные характеристики для построение пустого поля
        //             (но возможно, это в более раннем событии следует передать) для построения пустой выборки главного поля.
        //          2. Объекты матча статические (например для Сокобана: стены или дома).
        //          3. Объекты матча динамические. Например:
        //              1. Для Сокобана: игрок, ящики.
        //              2. Для Сапёра: флаги и количество мин на открытых плитках.

        throwExceptionIfIsPlaying();
        throwExceptionIfThereIsNotDefinedPlayer();
        this.paramsOfModelValueMap = gameMatchExtendedDto.getParamsOfModelValueMap();
        matchPlayerList.setReadOnlyTrue();
        status = GameMatchStatus.GAME;
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
