package timmax.tilegame.basemodel.protocol.server_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timmax.tilegame.basemodel.GameMatchStatus;
import timmax.tilegame.basemodel.credential.Credentials;
import timmax.tilegame.basemodel.credential.User;
import timmax.tilegame.basemodel.exception.WrongChangeStateException;
import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.HashSet;
import java.util.Set;

//  Базовый автомат состояний клиента.
//  Он будет родителем:
//  - как для автомата состояний клиента на сервере,
//  - так и для автомата состояний клиента на клиенте.
public abstract class ClientStateAutomaton<GameMatchX extends IGameMatchX> implements
        IClientState01NoConnect,
        IClientState02ConnectNonIdent,
        IClientState04UserWasAuthorized<GameMatchX>,
        IClientState06GameTypeWasSet<GameMatchX>,
        IClientState07GameMatchWasSet<GameMatchX>,
        IClientState08GameMatchIsPlaying {
    protected static final Logger logger = LoggerFactory.getLogger(ClientStateAutomaton.class);

    private final Set<StateToState<GameMatchX>> stateToStateSet;

    final ClientState01NoConnect<GameMatchX> clientState01NoConnect;
    final ClientState02ConnectNonIdent<GameMatchX> clientState02ConnectNonIdent;
    final ClientState04UserWasAuthorized<GameMatchX> clientState04UserWasAuthorized;
    final ClientState06GameTypeWasSet<GameMatchX> clientState06GameTypeWasSet;
    final ClientState07GameMatchWasSet<GameMatchX> clientState07GameMatchWasSet;
    final ClientState08GameMatchIsPlaying<GameMatchX> clientState08GameMatchIsPlaying;

    private IClientState99<GameMatchX> currentState;

    private User user;

    //  ToDo:   Удалить, т.к. у сервера должен быть перечень типов, поддерживаемых игр Set<GameType>.
    //          Но при этом геттер оставить.
    //      Warning:(31, 17) Raw use of parameterized class 'GameType'

    private Set<GameType> gameTypeSet; // ---- 3 (Список типов игр)

    private GameType<GameMatchX> gameType; // ---- 4 (Конкретный тип игры)
    private GameMatchX gameMatchX; // ---- 6 (Конкретная модель игры)
    //  GameMatchExtendedDto

    public ClientStateAutomaton(
            IFabricOfClientStates<GameMatchX> iFabricOfClientStates) {
        clientState01NoConnect = iFabricOfClientStates.getClientState01NoConnect(this);
        clientState02ConnectNonIdent = iFabricOfClientStates.getClientState02ConnectNonIdent(this);
        clientState04UserWasAuthorized = iFabricOfClientStates.getClientState04UserWasAuthorized(this);
        clientState06GameTypeWasSet = iFabricOfClientStates.getClientState06GameTypeWasSet(this);
        clientState07GameMatchWasSet = iFabricOfClientStates.getClientState07GameMatchWasSet(this);
        clientState08GameMatchIsPlaying = iFabricOfClientStates.getClientState08GameMatchIsPlaying(this);

        currentState = clientState01NoConnect;

        stateToStateSet = new HashSet<>();

        stateToStateSet.add(new StateToState<>(clientState01NoConnect, clientState02ConnectNonIdent));

        stateToStateSet.add(new StateToState<>(clientState02ConnectNonIdent, clientState01NoConnect));
        stateToStateSet.add(new StateToState<>(clientState02ConnectNonIdent, clientState02ConnectNonIdent));
        stateToStateSet.add(new StateToState<>(clientState02ConnectNonIdent, clientState04UserWasAuthorized));

        stateToStateSet.add(new StateToState<>(clientState04UserWasAuthorized, clientState01NoConnect));
        stateToStateSet.add(new StateToState<>(clientState04UserWasAuthorized, clientState02ConnectNonIdent));
        stateToStateSet.add(new StateToState<>(clientState04UserWasAuthorized, clientState06GameTypeWasSet));

        stateToStateSet.add(new StateToState<>(clientState06GameTypeWasSet, clientState01NoConnect));
        stateToStateSet.add(new StateToState<>(clientState06GameTypeWasSet, clientState02ConnectNonIdent));
        stateToStateSet.add(new StateToState<>(clientState06GameTypeWasSet, clientState04UserWasAuthorized));
        stateToStateSet.add(new StateToState<>(clientState06GameTypeWasSet, clientState07GameMatchWasSet));

        stateToStateSet.add(new StateToState<>(clientState07GameMatchWasSet, clientState01NoConnect));
        stateToStateSet.add(new StateToState<>(clientState07GameMatchWasSet, clientState02ConnectNonIdent));
        stateToStateSet.add(new StateToState<>(clientState07GameMatchWasSet, clientState04UserWasAuthorized));
        stateToStateSet.add(new StateToState<>(clientState07GameMatchWasSet, clientState06GameTypeWasSet));
        stateToStateSet.add(new StateToState<>(clientState07GameMatchWasSet, clientState08GameMatchIsPlaying));

        stateToStateSet.add(new StateToState<>(clientState08GameMatchIsPlaying, clientState01NoConnect));
        stateToStateSet.add(new StateToState<>(clientState08GameMatchIsPlaying, clientState02ConnectNonIdent));
        stateToStateSet.add(new StateToState<>(clientState08GameMatchIsPlaying, clientState04UserWasAuthorized));
        stateToStateSet.add(new StateToState<>(clientState08GameMatchIsPlaying, clientState06GameTypeWasSet));
        stateToStateSet.add(new StateToState<>(clientState08GameMatchIsPlaying, clientState07GameMatchWasSet));
    }

    public User getUser() {
        return user;
    }

    private void setCurrentState(IClientState99<GameMatchX> targetState) {
        boolean success = false;
        for (StateToState<GameMatchX> stateToState : stateToStateSet) {
            if (stateToState.getState1().equals(currentState) &&
                    stateToState.getState2().equals(targetState)) {
                success = true;
                break;
            }
        }

        if (!success) {
            throw new WrongChangeStateException(currentState, targetState);
        }

        currentState.doBeforeTurnOff();
        currentState = targetState;
        currentState.doAfterTurnOn();
    }

    public IClientState99<GameMatchX> getCurrentState() {
        return currentState;
    }

    protected void changeStateFrom01To02_() {
        if (!currentState.equals(clientState01NoConnect)) {
            throw new RuntimeException("This method allowed only for changing state from 01 to 02");
        }
        setCurrentState(clientState02ConnectNonIdent);
    }

    // Все методы с именами такими-же, как есть public, но:
    // 1. они private-package
    // 2. делают целевое действие в уже установленном состоянии.
    void connect_() {
        setCurrentState(clientState02ConnectNonIdent);
    }

    void close_() {
        setCurrentState(clientState01NoConnect);
    }

    void authorizeUser_(String userName) {
        this.user = Credentials.getUserByUserName(userName);
        if (user == null) {
            setCurrentState(clientState01NoConnect);
            return;
        }
        setCurrentState(clientState04UserWasAuthorized);
    }

    void setGameType_(GameType gameType) {
        //  Warning:(121, 25) Unchecked assignment: 'timmax.tilegame.basemodel.protocol.server.GameType' to 'timmax.tilegame.basemodel.protocol.server.GameType<GameMatchX>'
        this.gameType = gameType;
        setCurrentState(clientState06GameTypeWasSet);
    }

    void setGameMatchX_(GameMatchX gameMatchX) {
        this.gameMatchX = gameMatchX;
        setCurrentState(clientState07GameMatchWasSet);
    }

    //  ToDo:   Избавиться от protected (см. коммент к LocalClientStateAutomaton)
    //  ToDo:   Как-то не единообразно с предыдущими void получилось...
    //  ToDo:   Сделать возвращаемое значение void.
    //          В классе-наследнике LocalClientStateAutomaton метод полностью перегружается - не хорошо!
    protected GameMatchExtendedDto startGameMatch_(GameMatchExtendedDto gameMatchExtendedDto) {
        return getGameMatchX_().start(gameMatchExtendedDto);
    }

    // Геттерам, имеющим прямой доступ к полям(..._), тоже достаточно быть private-package:
    String getUserName_() {
        return user.getUserName();
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

    Set<GameMatchX> getGameMatchXSet_() {
        return gameType.getGameMatchXSet();
    }

    GameMatchX getGameMatchX_() {
        return gameMatchX;
    }

    GameMatchStatus getGameMatchStatus_() {
        return getGameMatchX_().getStatus();
    }

    //  ToDo:   Удалить метод, т.к. он используется только для клиента, и перенести инициализацию перечня типов игр в
    //          состояние "Установлено соединение с сервером".
    //          У сервера перечень типов игр одинаков, определяется вне зависимости от авторизации пользователя на сервере,
    //            и мог-бы храниться вне экземпляра этого класса.
    public void setGameTypeSet(Set<GameType> gameTypeSet) {
        this.gameTypeSet = gameTypeSet;
    }

    // Публичные методы класса, вызов которых будет в т.ч. приводить к смене состояния.
    // 1 interface IClientState01NoConnect
    @Override
    public void connect() {
        currentState.connect();
    }

    // 2 interface IClientState02ConnectNonIdent
    @Override
    public void close() {
        currentState.close();
    }

    @Override
    public void authorizeUser(String userName) {
        currentState.authorizeUser(userName);
    }

    // 4 interface IClientState04GameTypeSetSelected
    @Override
    public void reauthorizeUser() {
        currentState.reauthorizeUser();
    }

    @Override
    public Set<GameType> getGameTypeSet() {
        return currentState.getGameTypeSet();
    }

    @Override
    public GameType getGameType() {
        return currentState.getGameType();
    }

    @Override
    public void setGameType(GameType gameType) {
        //  Warning:(209, 37) Unchecked assignment: 'timmax.tilegame.basemodel.protocol.server.GameType' to 'timmax.tilegame.basemodel.protocol.server.GameType<GameMatchX>'
        currentState.setGameType(gameType);
    }

    // 6 interface IClientState06GameMatchSetSelected
    @Override
    public void resetGameType() {
        currentState.resetGameType();
    }

    @Override
    public Set<GameMatchX> getGameMatchXSet() {
        return currentState.getGameMatchXSet();
    }

    @Override
    public void setGameMatchX(GameMatchX gameMatchX) {
        currentState.setGameMatchX(gameMatchX);
    }

    // 7 interface IClientState07GameMatchSelected
    @Override
    public void resetGameMatch() {
        currentState.resetGameMatch();
    }

    @Override
    public GameMatchX getGameMatchX() {
        return currentState.getGameMatchX();
    }

    public GameMatchExtendedDto startGameMatch(GameMatchExtendedDto gameMatchExtendedDto) {
        //  ToDo:   Переместить setCurrentState(...) в startGameMatch_().
        setCurrentState(clientState08GameMatchIsPlaying);
        //  Место, где используется возвращаемое значение!
        return currentState.startGameMatch(gameMatchExtendedDto);
    }
/*
    public void startGameMatch(GameMatchExtendedDto gameMatchExtendedDto) {
*//*
        //  ToDo:   Переместить setCurrentState(...) в startGameMatch_().
        setCurrentState(clientState08GameMatchIsPlaying);
*//*
        currentState.startGameMatch(gameMatchExtendedDto);
    }
*/

    // 8 interface IClientState08GameMatchIsPlaying
    @Override
    public GameMatchStatus getGameMatchStatus() {
        return currentState.getGameMatchStatus();
    }

    // class Object
    @Override
    public String toString() {
        return currentState.getClass().getSimpleName();
    }
}
