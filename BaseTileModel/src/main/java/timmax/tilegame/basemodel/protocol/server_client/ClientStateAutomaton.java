package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;
import timmax.tilegame.baseview.View;

import java.util.Map;
import java.util.Set;

public class ClientStateAutomaton<GameMatchX extends IGameMatchX> implements
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

        // ToDo: ...State01NoConnect или доработать или удалить.
        //       Ранее было, как в комменте. Но вообще State01NoConnect не полностью работает.
        // currenState = clientState01NoConnect;
        currenState = clientState02ConnectNonIdent;
    }

    protected IClientState99<GameMatchX> getCurrentState() {
        return currenState;
    }

    void setUserName_(String userName, Set<GameType> gameTypeSet) {
        this.userName = userName;
        this.gameTypeSet = gameTypeSet;
    }

    Set<GameType> getGameTypeSet_() {
        return gameTypeSet;
    }

    void setGameTypeSet_(Set<GameType> gameTypeSet) {
        this.gameTypeSet = gameTypeSet;
    }

    void setGameType_(GameType gameType, Set<GameMatchX> gameMatchXSet) {
        this.gameType = gameType;
        this.gameMatchXSet = gameMatchXSet;
    }

    Set<GameMatchX> getGameMatchXSet_() {
        return gameMatchXSet;
    }

    void setGameMatchXSet_(Set<GameMatchX> gameMatchXSet) {
        this.gameMatchXSet = gameMatchXSet;
    }

    GameMatchX getGameMatchX_() {
        return gameMatchX;
    }

    void setGameMatchX_(GameMatchX gameMatchX) {
        this.gameMatchX = gameMatchX;
    }

    Boolean getGameIsPlaying_() {
        return gameIsPlaying;
    }

    void setGameIsPlaying_(Boolean gameIsPlaying) {
        this.gameIsPlaying = gameIsPlaying;
    }

    // 2 interface IClientState02ConnectNonIdent
    @Override
    public void setUser(String userName, Set<GameType> gameTypeSet) {
        currenState.setUser(userName, gameTypeSet);
        currenState = clientState04GameTypeSetSelected;
    }

    // 4 interface IClientState04GameTypeSetSelected
    @Override
    public Set<GameType> getGameTypeSet() {
        return currenState.getGameTypeSet();
    }

    @Override
    public void forgetUser() {
        currenState.forgetUser();
        currenState = clientState02ConnectNonIdent;
    }

    @Override
    public void setGameType(GameType gameType, Set<GameMatchX> gameMatchXSet) {
        currenState.setGameType(gameType, gameMatchXSet);
        currenState = clientState06GameMatchSetSelected;
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
    public Set<GameMatchX> getGameMatchXSet() {
        return currenState.getGameMatchXSet();
    }

    @Override
    public void forgetGameType() {
        currenState.forgetGameType();
        currenState = clientState04GameTypeSetSelected;
    }

    @Override
    public void setGameMatchX(GameMatchX gameMatchX) {
        currenState.setGameMatchX(gameMatchX);
        currenState = clientState07GameMatchSelected;
    }

    // 7 interface IClientState07GameMatchSelected
    @Override
    public GameMatchX getGameMatchX() {
        return currenState.getGameMatchX();
    }

    @Override
    public void forgetGameMatchX() {
        currenState.forgetGameMatchX();
        currenState = clientState06GameMatchSetSelected;
    }

    @Override
    public void setGameMatchPlaying(Boolean gameIsPlaying) {
        currenState.setGameMatchPlaying(gameIsPlaying);
        currenState = clientState08GameMatchPlaying;
    }

    // 8 interface IClientState08GameMatchPlaying
    @Override
    public Boolean getGameIsPlaying() {
        return currenState.getGameIsPlaying();
    }

    @Override
    public void forgetGameMatchPlaying() {
        currenState.forgetGameMatchPlaying();
        currenState = clientState07GameMatchSelected;
    }

    // class Object
    @Override
    public String toString() {
        return currenState.getClass().getSimpleName();
    }
}
