package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientStateAutomaton<GameMatchX extends IGameMatchX> implements
        IClientState01NoConnect,
        IClientState02ConnectNonIdent,
        IClientState04GameTypeSetSelected<GameMatchX>,
        IClientState06GameMatchSetSelected<GameMatchX>,
        IClientState07GameMatchSelected<GameMatchX>,
        IClientState08GameMatchPlaying {
    final ClientState01NoConnect<GameMatchX> clientState01NoConnect;
    final ClientState02ConnectNonIdent<GameMatchX> clientState02ConnectNonIdent;
    final ClientState04GameTypeSetSelected<GameMatchX> clientState04GameTypeSetSelected;
    final ClientState06GameMatchSetSelected<GameMatchX> clientState06GameMatchSetSelected;
    final ClientState07GameMatchSelected<GameMatchX> clientState07GameMatchSelected;
    final ClientState08GameMatchPlaying<GameMatchX> clientState08GameMatchPlaying;

    private IClientState99<GameMatchX> currenState;

    private String userName; // ---- 2 (Пользователь)
    private Set<GameType> gameTypeSet; // ---- 3 (Список типов игр)
    private GameType gameType; // ---- 4 (Конкретный тип игры)
    private Set<GameMatchX> gameMatchXSet; // ---- 5 (Набор моделей игр)
    private GameMatchX gameMatchX; // ---- 6 (Конкретная модель игры)
    private Boolean gameIsPlaying; // ---- 7 (Партия была начата)

    public ClientStateAutomaton(
            IFabricOfClientStates<GameMatchX> iFabricOfClientStates) {
        clientState01NoConnect = iFabricOfClientStates.getClientState01NoConnect(this);
        clientState02ConnectNonIdent = iFabricOfClientStates.getClientState02ConnectNonIdent(this);
        clientState04GameTypeSetSelected = iFabricOfClientStates.getClientState04GameTypeSetSelected(this);
        clientState06GameMatchSetSelected = iFabricOfClientStates.getClientState06GameMatchSetSelected(this);
        clientState07GameMatchSelected = iFabricOfClientStates.getClientState07GameMatchSelected(this);
        clientState08GameMatchPlaying = iFabricOfClientStates.getClientState08GameMatchPlaying(this);

        currenState = clientState01NoConnect;
    }

    // ToDo: Сделать контроль возможности перехода в состояние в начале метода.
    //       Если нельзя - возбуждать исключение.
    private void setCurrentState(IClientState99<GameMatchX> currentState) {
        this.currenState.doBeforeTurnOff();
        this.currenState = currentState;
        currentState.doAfterTurnOn();
    }

    // Все методы с именами такими-же, как есть public, но:
    // 1. они private-package
    // 2. делают целевое действие в уже установленном состоянии.
    void openConnectWithoutUserIdentify_() {
    }

    void closeConnect_() {
    }

    void authorizeUser_(String userName, Set<GameType> gameTypeSet) {
        if (userName == null || userName.isEmpty()) {
            setCurrentState(clientState01NoConnect);
            return;
        }
        this.userName = userName;
        this.gameTypeSet = gameTypeSet;
    }

    void setGameType_(GameType gameType, Set<GameMatchX> gameMatchXSet) {
        this.gameType = gameType;
        this.gameMatchXSet = gameMatchXSet;
    }

    void setGameMatchX_(GameMatchX gameMatchX) {
        this.gameMatchX = gameMatchX;
    }

    void setGameIsPlaying_(Boolean gameIsPlaying) {
        this.gameIsPlaying = gameIsPlaying;
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
        return gameMatchXSet;
    }

    GameMatchX getGameMatchX_() {
        return gameMatchX;
    }

    Boolean getGameIsPlaying_() {
        return gameIsPlaying;
    }

    // Публичные методы класса, вызов которых будет в т.ч. приводить к смене состояния.
    // 1 interface IClientState01NoConnect
    @Override
    public void openConnectWithoutUserIdentify() {
        setCurrentState(clientState02ConnectNonIdent);
        currenState.openConnectWithoutUserIdentify();
    }

    // 2 interface IClientState02ConnectNonIdent
    @Override
    public void closeConnect() {
        setCurrentState(clientState01NoConnect);
        currenState.closeConnect();
    }

    @Override
    public void authorizeUser(String userName, Set<GameType> gameTypeSet) {
        setCurrentState(clientState04GameTypeSetSelected);
        currenState.authorizeUser(userName, gameTypeSet);
    }

    // 4 interface IClientState04GameTypeSetSelected
    @Override
    public void reauthorizeUser() {
        setCurrentState(clientState04GameTypeSetSelected);
        currenState.reauthorizeUser();
    }

    @Override
    public Set<GameType> getGameTypeSet() {
        return currenState.getGameTypeSet();
    }

    @Override
    public void setGameType(GameType gameType, Set<GameMatchX> gameMatchXSet) {
        setCurrentState(clientState06GameMatchSetSelected);
        currenState.setGameType(gameType, gameMatchXSet);
    }

    // 6 interface IClientState06GameMatchSetSelected
    @Override
    public void resetGameType() {
        setCurrentState(clientState06GameMatchSetSelected);
        currenState.resetGameType();
    }

    @Override
    public Set<GameMatchX> getGameMatchXSet() {
        return currenState.getGameMatchXSet();
    }

    @Override
    public void setGameMatchX(GameMatchX gameMatchX) {
        setCurrentState(clientState07GameMatchSelected);
        currenState.setGameMatchX(gameMatchX);
    }

    // 7 interface IClientState07GameMatchSelected
    @Override
    public void resetGameMatchX() {
        setCurrentState(clientState07GameMatchSelected);
        currenState.resetGameMatchX();
    }

    @Override
    public GameMatchX getGameMatchX() {
        return currenState.getGameMatchX();
    }

    // ToDo: Проверить имя метода ..._().
    @Override
    public void setGameMatchPlaying(Boolean gameIsPlaying) {
        setCurrentState(clientState08GameMatchPlaying);
        currenState.setGameMatchPlaying(gameIsPlaying);
    }

    // 8 interface IClientState08GameMatchPlaying
    @Override
    public Boolean getGameIsPlaying() {
        return currenState.getGameIsPlaying();
    }

    // class Object
    @Override
    public String toString() {
        return currenState.getClass().getSimpleName();
    }
}
