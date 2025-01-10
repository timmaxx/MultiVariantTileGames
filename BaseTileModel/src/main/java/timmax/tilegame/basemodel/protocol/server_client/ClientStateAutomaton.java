package timmax.tilegame.basemodel.protocol.server_client;

import timmax.state.StateAutomaton;
import timmax.tilegame.basemodel.GameMatchStatus;
import timmax.tilegame.basemodel.credential.Credentials;
import timmax.tilegame.basemodel.credential.User;
import timmax.tilegame.basemodel.dto.BaseDto;
import timmax.tilegame.basemodel.dto.GameMatchExtendedDto;
import timmax.tilegame.basemodel.protocol.server.GameType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timmax.tilegame.basemodel.util.BaseUtil;

import java.util.Set;

//  Базовый автомат состояний клиента.
//  Он будет родителем:
//  - как для автомата состояний клиента на сервере,
//  - так и для автомата состояний клиента на клиенте.
public abstract class ClientStateAutomaton extends StateAutomaton implements IClientState99 {
    protected static final Logger logger = LoggerFactory.getLogger(ClientStateAutomaton.class);

    //  ToDo:   Константы-состояния можно было-бы определить как массив State в StateAutomaton, а в этом классе
    //          переопределить его как ClientState. Ну и ввести перечисление для доступа к этим элементам.
    final ClientState01NoConnect clientState01NoConnect;
    final ClientState02ConnectWithoutServerInfo clientState02ConnectWithoutServerInfo;
    final ClientState03ConnectWithServerInfo clientState03ConnectWithServerInfo;
    final ClientState04UserWasAuthorized clientState04UserWasAuthorized;
    final ClientState06GameTypeWasSet clientState06GameTypeWasSet;
    final ClientState07GameMatchWasSet clientState07GameMatchWasSet;
    final ClientState08GameMatchIsPlaying clientState08GameMatchIsPlaying;

    private User user;

    private Set<GameType> gameTypeSet; // ---- 3 (Список типов игр)

    private GameType gameType; // ---- 4 (Конкретный тип игры)

    private IGameMatchX gameMatchX; // ---- 6 (Конкретная модель игры)
    //  Это поле нужно только клиенту - нехорошо.
    private GameMatchExtendedDto gameMatchExtendedDto;

    public ClientStateAutomaton(
            IFabricOfClientStates iFabricOfClientStates) {
        clientState01NoConnect = iFabricOfClientStates.getClientState01NoConnect(this);
        clientState02ConnectWithoutServerInfo = iFabricOfClientStates.getClientState02ConnectWithoutServerInfo(this);
        clientState03ConnectWithServerInfo = iFabricOfClientStates.getClientState03ConnectWithServerInfo(this);
        clientState04UserWasAuthorized = iFabricOfClientStates.getClientState04UserWasAuthorized(this);
        clientState06GameTypeWasSet = iFabricOfClientStates.getClientState06GameTypeWasSet(this);
        clientState07GameMatchWasSet = iFabricOfClientStates.getClientState07GameMatchWasSet(this);
        clientState08GameMatchIsPlaying = iFabricOfClientStates.getClientState08GameMatchIsPlaying(this);

        currentState = clientState01NoConnect;

        //  ToDo:   Для клиента можно было-бы создать состояние "Ожидание соединения", оно как-бы уже соответствует
        //          тому, что контролы блокируются после того, как что-то отправляется серверу.
        //          При установлении соединения - переходим в следующее состояние (активируем и деактивируем контролы),
        //          при ошибке - откатываемся в предыдущее состояние (активируем и деактивируем контролы).
        //          Можно расширить и тогда можно сделать универсальное состояние "Ожидание подтверждения".
        //          Тогда переход из состояния clientState01NoConnect в себя не понадобится.
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState01NoConnect, clientState01NoConnect));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState01NoConnect, clientState02ConnectWithoutServerInfo));

        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState02ConnectWithoutServerInfo, clientState01NoConnect));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState02ConnectWithoutServerInfo, clientState03ConnectWithServerInfo));

        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState03ConnectWithServerInfo, clientState01NoConnect));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState03ConnectWithServerInfo, clientState02ConnectWithoutServerInfo));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState03ConnectWithServerInfo, clientState04UserWasAuthorized));

        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState04UserWasAuthorized, clientState01NoConnect));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState04UserWasAuthorized, clientState02ConnectWithoutServerInfo));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState04UserWasAuthorized, clientState03ConnectWithServerInfo));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState04UserWasAuthorized, clientState06GameTypeWasSet));

        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState06GameTypeWasSet, clientState01NoConnect));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState06GameTypeWasSet, clientState02ConnectWithoutServerInfo));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState06GameTypeWasSet, clientState03ConnectWithServerInfo));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState06GameTypeWasSet, clientState04UserWasAuthorized));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState06GameTypeWasSet, clientState07GameMatchWasSet));

        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState07GameMatchWasSet, clientState01NoConnect));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState07GameMatchWasSet, clientState02ConnectWithoutServerInfo));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState07GameMatchWasSet, clientState03ConnectWithServerInfo));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState07GameMatchWasSet, clientState04UserWasAuthorized));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState07GameMatchWasSet, clientState06GameTypeWasSet));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState07GameMatchWasSet, clientState08GameMatchIsPlaying));

        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState08GameMatchIsPlaying, clientState01NoConnect));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState08GameMatchIsPlaying, clientState02ConnectWithoutServerInfo));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState08GameMatchIsPlaying, clientState03ConnectWithServerInfo));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState08GameMatchIsPlaying, clientState04UserWasAuthorized));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState08GameMatchIsPlaying, clientState06GameTypeWasSet));
        allowedStateToStateTransitionSet.add(new ClientAllowedStateToStateTransition(clientState08GameMatchIsPlaying, clientState07GameMatchWasSet));
    }

    public User getUser() {
        return user;
    }

    public GameMatchExtendedDto getGameMatchExtendedDto() {
        return gameMatchExtendedDto;
    }

    protected void changeStateFrom01To02_() {
        if (!currentState.equals(clientState01NoConnect)) {
            throw new RuntimeException("This method allowed only for changing state from 01 to 02");
        }
        setCurrentState(clientState02ConnectWithoutServerInfo);
    }

    // Все методы с именами такими-же, как есть public, но:
    // 1. они private-package
    // 2. делают целевое действие и переводят состояние.
    void connect_() {
        setCurrentState(clientState02ConnectWithoutServerInfo);
    }

    void close_() {
        setCurrentState(clientState01NoConnect);
    }

    void setGameTypeSet_(Set<GameType> gameTypeSet) {
        this.gameTypeSet = gameTypeSet;
        setCurrentState(clientState03ConnectWithServerInfo);
    }

    void authorizeUser_(BaseDto userDtoId) {
        this.user = Credentials.getUserByUserId(userDtoId);
        if (user == null) {
            setCurrentState(clientState01NoConnect);
            return;
        }
        setCurrentState(clientState04UserWasAuthorized);
    }

    void setGameType_(GameType gameType) {
        this.gameType = gameType;
        setCurrentState(clientState06GameTypeWasSet);
    }

    void setGameMatchX_(IGameMatchX gameMatchX) {
        this.gameMatchX = gameMatchX;
        setCurrentState(clientState07GameMatchWasSet);
    }

    //  ToDo:   Устранить не единообразие этого метода с вышенаходящимися set..._().
    //          Этот метод подобен вышенаходящимися set..._(), т.к.:
    //          -   инициализируется переменная экземпляра,
    //          -   имеется вызов метода перевода статуса.
    //          Но он и отличается, т.к.:
    //          -   после инициализации переменной gameMatchExtendedDto запускается метод (здесь это start()).
    void startGameMatch_(GameMatchExtendedDto gameMatchExtendedDto) {
        //  Эта инициализация нужна только клиенту.
        this.gameMatchExtendedDto = gameMatchExtendedDto;
        //  Вызов этого метода нужен только серверу.
        gameMatchX.start(gameMatchExtendedDto);
        setCurrentState(clientState08GameMatchIsPlaying);
    }

    // Геттерам, имеющим прямой доступ к полям(get..._()), тоже достаточно быть private-package:
    BaseDto getUserDtoId_() {
        return BaseUtil.createBaseDto(user);
    }

    // ToDo: Может обойтись без protected?
    //       Используется как protected в LocalClientStateAutomaton
    //       - :: Map<String, Class<? extends View>> getViewName_ViewClassMap()
    //       - :: Map<String, ParamOfModelDescription> getParamName_paramModelDescriptionMap()
    protected GameType getGameType_() {
        return gameType;
    }

    Set<GameType> getGameTypeSet_() {
        return gameTypeSet;
    }

    Set<IGameMatchX> getGameMatchXSet_() {
        return gameType.getGameMatchXSet();
    }

    IGameMatchX getGameMatchX_() {
        return gameMatchX;
    }

    GameMatchStatus getGameMatchStatus_() {
        return getGameMatchX_().getStatus();
    }

    @Override
    public ClientState getCurrentState() {
        return (ClientState) currentState;
    }

    // Публичные методы класса, вызов которых будет в т.ч. приводить к смене состояния.
    // 1 interface IClientState01NoConnect
    @Override
    public void connect() {
        getCurrentState().connect();
    }

    // 2 interface IClientState02ConnectNonIdent
    @Override
    public void close() {
        close_();
    }

    //  ToDo:   Удалить метод, т.к. он используется только для клиента, и перенести инициализацию перечня типов игр в
    //          состояние "Установлено соединение с сервером".
    //          У сервера перечень типов игр одинаков, определяется вне зависимости от авторизации пользователя на сервере,
    //            и мог-бы храниться вне экземпляра этого класса.
    @Override
    public void setGameTypeSet(Set<GameType> gameTypeSet) {
        getCurrentState().setGameTypeSet(gameTypeSet);
    }

    @Override
    public void authorizeUser(BaseDto userDto) {
        getCurrentState().authorizeUser(userDto);
    }

    // 4 interface IClientState04GameTypeSetSelected
    @Override
    public void reauthorizeUser() {
        getCurrentState().reauthorizeUser();
    }

    @Override
    public Set<GameType> getGameTypeSet() {
        return getCurrentState().getGameTypeSet();
    }

    @Override
    public GameType getGameType() {
        return getCurrentState().getGameType();
    }

    @Override
    public void setGameType(GameType gameType) {
        getCurrentState().setGameType(gameType);
    }

    // 6 interface IClientState06GameMatchSetSelected
    @Override
    public void resetGameType() {
        getCurrentState().resetGameType();
    }

    @Override
    public Set<IGameMatchX> getGameMatchXSet() {
        return getCurrentState().getGameMatchXSet();
    }

    @Override
    public void setGameMatchX(IGameMatchX gameMatchX) {
        getCurrentState().setGameMatchX(gameMatchX);
    }

    // 7 interface IClientState07GameMatchSelected
    @Override
    public void resetGameMatch() {
        getCurrentState().resetGameMatch();
    }

    @Override
    public IGameMatchX getGameMatchX() {
        return getCurrentState().getGameMatchX();
    }

    @Override
    public void startGameMatch(GameMatchExtendedDto gameMatchExtendedDto) {
        getCurrentState().startGameMatch(gameMatchExtendedDto);
    }

    // 8 interface IClientState08GameMatchIsPlaying
    @Override
    public GameMatchStatus getGameMatchStatus() {
        return getCurrentState().getGameMatchStatus();
    }

    // class Object
    @Override
    public String toString() {
        return
                ClientStateAutomaton.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                        "currentState=" + currentState;
    }
}
