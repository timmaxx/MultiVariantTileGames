package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.GameMatchStatus;
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
        IClientState04GameTypeSetSelected<GameMatchX>,
        IClientState06GameMatchSetSelected<GameMatchX>,
        IClientState07GameMatchSelected<GameMatchX>,
        IClientState08GameMatchIsPlaying {
    private final Set<StateToState<GameMatchX>> stateToStateSet;

    final ClientState01NoConnect<GameMatchX> clientState01NoConnect;
    final ClientState02ConnectNonIdent<GameMatchX> clientState02ConnectNonIdent;
    final ClientState04GameTypeSetSelected<GameMatchX> clientState04GameTypeSetSelected;
    final ClientState06GameMatchSetSelected<GameMatchX> clientState06GameMatchSetSelected;
    final ClientState07GameMatchSelected<GameMatchX> clientState07GameMatchSelected;
    final ClientState08GameMatchIsPlaying<GameMatchX> clientState08GameMatchIsPlaying;

    private IClientState99<GameMatchX> currentState;

    //  ToDo:   Создать тип User и в т.ч. в его составе должен быть Set<GameType> gameTypeSet.
    private String userName; // ---- 2 (Пользователь)

    //  ToDo:   Удалить, т.к. в User должен быть Set<GameType>.
    //      Warning:(31, 17) Raw use of parameterized class 'GameType'
    private Set<GameType> gameTypeSet; // ---- 3 (Список типов игр)

    private GameType<GameMatchX> gameType; // ---- 4 (Конкретный тип игры)
    private GameMatchX gameMatchX; // ---- 6 (Конкретная модель игры)

    public ClientStateAutomaton(
            IFabricOfClientStates<GameMatchX> iFabricOfClientStates) {
        clientState01NoConnect = iFabricOfClientStates.getClientState01NoConnect(this);
        clientState02ConnectNonIdent = iFabricOfClientStates.getClientState02ConnectNonIdent(this);
        clientState04GameTypeSetSelected = iFabricOfClientStates.getClientState04GameTypeSetSelected(this);
        clientState06GameMatchSetSelected = iFabricOfClientStates.getClientState06GameMatchSetSelected(this);
        clientState07GameMatchSelected = iFabricOfClientStates.getClientState07GameMatchSelected(this);
        clientState08GameMatchIsPlaying = iFabricOfClientStates.getClientState08GameMatchIsPlaying(this);

        currentState = clientState01NoConnect;

        stateToStateSet = new HashSet<>();

        stateToStateSet.add(new StateToState<>(clientState01NoConnect, clientState02ConnectNonIdent));

        stateToStateSet.add(new StateToState<>(clientState02ConnectNonIdent, clientState01NoConnect));
        stateToStateSet.add(new StateToState<>(clientState02ConnectNonIdent, clientState02ConnectNonIdent));
        stateToStateSet.add(new StateToState<>(clientState02ConnectNonIdent, clientState04GameTypeSetSelected));

        stateToStateSet.add(new StateToState<>(clientState04GameTypeSetSelected, clientState01NoConnect));
        stateToStateSet.add(new StateToState<>(clientState04GameTypeSetSelected, clientState02ConnectNonIdent));
        stateToStateSet.add(new StateToState<>(clientState04GameTypeSetSelected, clientState06GameMatchSetSelected));

        stateToStateSet.add(new StateToState<>(clientState06GameMatchSetSelected, clientState01NoConnect));
        stateToStateSet.add(new StateToState<>(clientState06GameMatchSetSelected, clientState02ConnectNonIdent));
        stateToStateSet.add(new StateToState<>(clientState06GameMatchSetSelected, clientState04GameTypeSetSelected));
        stateToStateSet.add(new StateToState<>(clientState06GameMatchSetSelected, clientState07GameMatchSelected));

        stateToStateSet.add(new StateToState<>(clientState07GameMatchSelected, clientState01NoConnect));
        stateToStateSet.add(new StateToState<>(clientState07GameMatchSelected, clientState02ConnectNonIdent));
        stateToStateSet.add(new StateToState<>(clientState07GameMatchSelected, clientState04GameTypeSetSelected));
        stateToStateSet.add(new StateToState<>(clientState07GameMatchSelected, clientState06GameMatchSetSelected));
        stateToStateSet.add(new StateToState<>(clientState07GameMatchSelected, clientState08GameMatchIsPlaying));

        stateToStateSet.add(new StateToState<>(clientState08GameMatchIsPlaying, clientState01NoConnect));
        stateToStateSet.add(new StateToState<>(clientState08GameMatchIsPlaying, clientState02ConnectNonIdent));
        stateToStateSet.add(new StateToState<>(clientState08GameMatchIsPlaying, clientState04GameTypeSetSelected));
        stateToStateSet.add(new StateToState<>(clientState08GameMatchIsPlaying, clientState06GameMatchSetSelected));
        stateToStateSet.add(new StateToState<>(clientState08GameMatchIsPlaying, clientState07GameMatchSelected));
    }

    private void setCurrentState(IClientState99<GameMatchX> currentState) {
        boolean success = false;
        for (StateToState<GameMatchX> stateToState : stateToStateSet) {
            if (stateToState.getState1().equals(this.currentState) &&
                    stateToState.getState2().equals(currentState)) {
                success = true;
                break;
            }
        }

        if (!success) {
            throw new RuntimeException("You cannot change state from '" + this.currentState + "' to '" + currentState + "'!");
        }

        this.currentState.doBeforeTurnOff();
        this.currentState = currentState;
        currentState.doAfterTurnOn();
    }

    protected final void changeStateFrom01To02_() {
        if (!currentState.equals(clientState01NoConnect)) {
            throw new RuntimeException("This method allowed only for changing state from 01 to 02");
        }
        currentState = clientState02ConnectNonIdent;
    }

    // Все методы с именами такими-же, как есть public, но:
    // 1. они private-package
    // 2. делают целевое действие в уже установленном состоянии.
    void connect_() {
    }

    void close_() {
    }

    void authorizeUser_(String userName, Set<GameType> gameTypeSet) {
        if (userName == null || userName.isEmpty()) {
            setCurrentState(clientState01NoConnect);
            return;
        }
        this.userName = userName;
        this.gameTypeSet = gameTypeSet;
    }

    void selectGameType_(GameType gameType) {
        //  Warning:(121, 25) Unchecked assignment: 'timmax.tilegame.basemodel.protocol.server.GameType' to 'timmax.tilegame.basemodel.protocol.server.GameType<GameMatchX>'
        this.gameType = gameType;
    }

    void selectGameMatchX_(GameMatchX gameMatchX) {
        this.gameMatchX = gameMatchX;
    }

    // ToDo: Избавиться от protected (см. коммент к LocalClientStateAutomaton)
    protected GameMatchExtendedDto startGameMatch_(GameMatchExtendedDto gameMatchExtendedDto) {
        return getGameMatchX_().start(gameMatchExtendedDto);
    }

    // Геттерам, имеющим прямой доступ к полям(..._), тоже достаточно быть private-package:
    String getUserName_() {
        return userName;
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

    // Публичные методы класса, вызов которых будет в т.ч. приводить к смене состояния.
    // 1 interface IClientState01NoConnect
    @Override
    public void connect() {
        setCurrentState(clientState02ConnectNonIdent);
        currentState.connect();
    }

    // 2 interface IClientState02ConnectNonIdent
    @Override
    public void close() {
        setCurrentState(clientState01NoConnect);
        currentState.close();
    }

    @Override
    public void authorizeUser(String userName, Set<GameType> gameTypeSet) {
        setCurrentState(clientState04GameTypeSetSelected);
        currentState.authorizeUser(userName, gameTypeSet);
    }

    // 4 interface IClientState04GameTypeSetSelected
    @Override
    public void reauthorizeUser() {
        setCurrentState(clientState04GameTypeSetSelected);
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
    public void selectGameType(GameType gameType) {
        setCurrentState(clientState06GameMatchSetSelected);
        //  Warning:(209, 37) Unchecked assignment: 'timmax.tilegame.basemodel.protocol.server.GameType' to 'timmax.tilegame.basemodel.protocol.server.GameType<GameMatchX>'
        currentState.selectGameType(gameType);
    }

    // 6 interface IClientState06GameMatchSetSelected
    @Override
    public void reselectGameType() {
        setCurrentState(clientState06GameMatchSetSelected);
        currentState.reselectGameType();
    }

    @Override
    public Set<GameMatchX> getGameMatchXSet() {
        return currentState.getGameMatchXSet();
    }

    @Override
    public void selectGameMatchX(GameMatchX gameMatchX) {
        setCurrentState(clientState07GameMatchSelected);
        currentState.selectGameMatchX(gameMatchX);
    }

    // 7 interface IClientState07GameMatchSelected
    @Override
    public void reselectGameMatch() {
        setCurrentState(clientState07GameMatchSelected);
        currentState.reselectGameMatch();
    }

    @Override
    public GameMatchX getGameMatchX() {
        return currentState.getGameMatchX();
    }

    public GameMatchExtendedDto startGameMatch(GameMatchExtendedDto gameMatchExtendedDto) {
        setCurrentState(clientState08GameMatchIsPlaying);
        return currentState.startGameMatch(gameMatchExtendedDto);
    }

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
