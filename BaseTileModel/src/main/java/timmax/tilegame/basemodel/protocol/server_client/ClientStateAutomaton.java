package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;
import timmax.tilegame.baseview.View;

import java.util.Map;
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

    protected IClientState99<GameMatchX> getCurrentState() {
        return currenState;
    }

    private void setCurrentState(IClientState99<GameMatchX> currentState) {
        this.currenState.doBeforeTurnOff();
        this.currenState = currentState;
        currentState.doAfterTurnOn();
    }

    // Все методы с именами такими-же, как есть public, но:
    // 1. они private-package
    // 2. последняя строка делает изменение состояния.
    void openConnectWithoutUserIdentify_() {
        setCurrentState(clientState02ConnectNonIdent);
    }

    void closeConnect_() {
        setCurrentState(clientState01NoConnect);
    }

    void authorizeUser_(String userName, Set<GameType> gameTypeSet) {
        if (userName == null || userName.isEmpty()) {
            setCurrentState(clientState01NoConnect);
            return;
        }
        this.userName = userName;
        this.gameTypeSet = gameTypeSet;
        setCurrentState(clientState04GameTypeSetSelected);
    }

    void setGameType_(GameType gameType, Set<GameMatchX> gameMatchXSet) {
        this.gameType = gameType;
        this.gameMatchXSet = gameMatchXSet;
        setCurrentState(clientState06GameMatchSetSelected);
    }

    void setGameMatchX_(GameMatchX gameMatchX) {
        this.gameMatchX = gameMatchX;
        setCurrentState(clientState07GameMatchSelected);
    }

    void setGameIsPlaying_(Boolean gameIsPlaying) {
        this.gameIsPlaying = gameIsPlaying;
        setCurrentState(clientState08GameMatchPlaying);
    }

    // Геттерам, имеющим прямой доступ к полям(..._), тоже достаточно быть private-package:
    String getUserName_() {
        return userName;
    }

    GameType getGameType_() {
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
        currenState.openConnectWithoutUserIdentify();
    }

    // 2 interface IClientState02ConnectNonIdent
    @Override
    public void closeConnect() {
        currenState.closeConnect();
    }

    @Override
    public void authorizeUser(String userName, Set<GameType> gameTypeSet) {
        currenState.authorizeUser(userName, gameTypeSet);
    }

    // 4 interface IClientState04GameTypeSetSelected
    @Override
    public void reauthorizeUser() {
        currenState.reauthorizeUser();
    }

    @Override
    public Set<GameType> getGameTypeSet() {
        return currenState.getGameTypeSet();
    }

    @Override
    public void setGameType(GameType gameType, Set<GameMatchX> gameMatchXSet) {
        currenState.setGameType(gameType, gameMatchXSet);
    }

    @Override
    public Map<String, Class<? extends View>> getViewName_ViewClassMap() {
        return gameType.getViewName_ViewClassMap();
    }

    @Override
    public String getGameTypeName() {
        return gameType.getGameTypeName();
    }

    @Override
    public Map<String, ParamOfModelDescription> getParamName_paramModelDescriptionMap() {
        return gameType.getParamName_paramModelDescriptionMap();
    }

    // 6 interface IClientState06GameMatchSetSelected
    @Override
    public void resetGameType() {
        currenState.resetGameType();
    }

    @Override
    public Set<GameMatchX> getGameMatchXSet() {
        return currenState.getGameMatchXSet();
    }

    @Override
    public void setGameMatchX(GameMatchX gameMatchX) {
        currenState.setGameMatchX(gameMatchX);
    }

    // 7 interface IClientState07GameMatchSelected
    @Override
    public void resetGameMatchX() {
        currenState.resetGameMatchX();
    }

    @Override
    public GameMatchX getGameMatchX() {
        return currenState.getGameMatchX();
    }

    @Override
    public void setGameMatchPlaying(Boolean gameIsPlaying) {
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
