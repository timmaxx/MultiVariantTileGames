package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public class ClientStateAutomaton<Model> implements
        IClientState01NoConnect,
        IClientState02ConnectNonIdent,
        IClientState03ConnectAuthorized,
        IClientState04GameTypeSetSelected,
        IClientState05GameTypeSelected<Model>,
        IClientState06GameMatchSetSelected<Model>,
        IClientState07GameMatchSelected<Model>,
        IClientState08GameMatchPlaying {
    final ClientState01NoConnect<Model> clientState01NoConnect;
    final ClientState02ConnectNonIdent<Model> clientState02ConnectNonIdent;
    final ClientState03ConnectAuthorized<Model> clientState03ConnectAuthorized;
    final ClientState04GameTypeSetSelected<Model> clientState04GameTypeSetSelected;
    final ClientState05GameTypeSelected<Model> clientState05GameTypeSelected;
    final ClientState06GameMatchSetSelected<Model> clientState06GameMatchSetSelected;
    final ClientState07GameMatchSelected<Model> clientState07GameMatchSelected;
    final ClientState08GameMatchPlaying<Model> clientState08GameMatchPlaying;

    private IClientState00 currenState;

    private String userName; // ---- 2 (Пользователь)
    private Set<GameType> gameTypeSet; // ---- 3 (Список типов игр)
    private GameType gameType; // ---- 4 (Конкретный тип игры)
    private Set<Model> serverBaseModelSet; // ---- 5 (Набор моделей игр)
    private Model serverBaseModel; // ---- 6 (Конкретная модель игры)
    private Boolean gameIsPlaying; // ---- 7 (Партия была начата)

    public ClientStateAutomaton(
            IFabricOfClientStates<Model> iFabricOfClientStates) {
        clientState01NoConnect = iFabricOfClientStates.getClientState01NoConnect(this);
        clientState02ConnectNonIdent = iFabricOfClientStates.getClientState02ConnectNonIdent(this);
        clientState03ConnectAuthorized = iFabricOfClientStates.getClientState03ConnectAuthorized(this);
        clientState04GameTypeSetSelected = iFabricOfClientStates.getClientState04GameTypeSetSelected(this);
        clientState05GameTypeSelected = iFabricOfClientStates.getClientState05GameTypeSelected(this);
        clientState06GameMatchSetSelected = iFabricOfClientStates.getClientState06GameMatchSetSelected(this);
        clientState07GameMatchSelected = iFabricOfClientStates.getClientState07GameMatchSelected(this);
        clientState08GameMatchPlaying = iFabricOfClientStates.getClientState08GameMatchPlaying(this);

        currenState = clientState01NoConnect;
    }

    String getUserName0() {
        return userName;
    }

    void setUserName0(String userName) {
        this.userName = userName;
    }

    void setUserName_(String userName) {
        clientState04GameTypeSetSelected.forgetGameTypeSet();
        setUserName0(userName);
    }

    Set<GameType> getGameTypeSet0() {
        return gameTypeSet;
    }

    void setGameTypeSet0(Set<GameType> gameTypeSet) {
        this.gameTypeSet = gameTypeSet;
    }

    void setGameTypeSet_(Set<GameType> gameTypeSet) {
        clientState05GameTypeSelected.forgetGameType();
        setGameTypeSet0(gameTypeSet);
    }

    GameType getGameType0() {
        return gameType;
    }

    void setGameType0(GameType gameType) {
        this.gameType = gameType;
    }

    void setGameType_(GameType gameType) {
        clientState06GameMatchSetSelected.forgetGameMatchSet();
        setGameType0(gameType);
    }

    Set<Model> getServerBaseModelSet0() {
        return serverBaseModelSet;
    }

    void setServerBaseModelSet0(Set<Model> serverBaseModelSet) {
        this.serverBaseModelSet = serverBaseModelSet;
    }

    // ToDo: Имя метода с предыдущим должно почти совпадать!
    void setGameMatchSet_(Set<Model> serverBaseModelSet) {
        clientState07GameMatchSelected.forgetGameMatch();
        setServerBaseModelSet0(serverBaseModelSet);
    }

    // ToDo: Только из-за класса RemoteClientState07GameMatchSelected понадобилось сделать public.
    public Model getServerBaseModel0() {
        return serverBaseModel;
    }

    void setServerBaseModel0(Model serverBaseModel) {
        this.serverBaseModel = serverBaseModel;
    }

    // ToDo: Имя метода с предыдущим должно почти совпадать!
    void setServerBaseModel_(Model serverBaseModel) {
        clientState08GameMatchPlaying.forgetGameMatchPlaying();
        setServerBaseModel0(serverBaseModel);
    }

    Boolean getGameIsPlaying0() {
        return gameIsPlaying;
    }

    void setGameIsPlaying0(Boolean gameIsPlaying) {
        this.gameIsPlaying = gameIsPlaying;
    }

    void setGameIsPlaying_(Boolean gameIsPlaying) {
        setGameIsPlaying0(gameIsPlaying);
    }

    // 2 interface IClientState02ConnectNonIdent
    @Override
    public void setUser(String userName) {
        clientState02ConnectNonIdent.setUser(userName);
        currenState = clientState03ConnectAuthorized;
    }

    // 3 interface IClientState03ConnectAuthorized
    @Override
    public String getUserName() {
        return clientState03ConnectAuthorized.getUserName();
    }

    @Override
    public void forgetUser() {
        clientState03ConnectAuthorized.forgetUser();
        currenState = clientState02ConnectNonIdent;
    }

    @Override
    public void setGameTypeSet(Set<GameType> gameTypeSet) {
        clientState03ConnectAuthorized.setGameTypeSet(gameTypeSet);
        currenState = clientState04GameTypeSetSelected;
    }

    // 4 interface IClientState04GameTypeSetSelected
    @Override
    public Set<GameType> getGameTypeSet() {
        return clientState04GameTypeSetSelected.getGameTypeSet();
    }

    @Override
    public void forgetGameTypeSet() {
        clientState04GameTypeSetSelected.forgetGameTypeSet();
        currenState = clientState03ConnectAuthorized;
    }

    @Override
    public void setGameType(GameType gameType) {
        clientState04GameTypeSetSelected.setGameType(gameType);
        currenState = clientState05GameTypeSelected;
    }

    // 5 interface IClientState05GameTypeSelected
    @Override
    public GameType getGameType() {
        return clientState05GameTypeSelected.getGameType();
    }

    @Override
    public void forgetGameType() {
        clientState05GameTypeSelected.forgetGameType();
        currenState = clientState03ConnectAuthorized;
    }

    @Override
    public void setGameMatchSet(Set<Model> serverBaseModelSet) {
        clientState05GameTypeSelected.setGameMatchSet(serverBaseModelSet);
        currenState = clientState06GameMatchSetSelected;
    }

    // 6 interface IClientState06GameMatchSetSelected
    @Override
    public Set<Model> getGameMatchSet() {
        return clientState06GameMatchSetSelected.getGameMatchSet();
    }

    @Override
    public void forgetGameMatchSet() {
        clientState06GameMatchSetSelected.forgetGameMatchSet();
        currenState = clientState05GameTypeSelected;
    }

    @Override
    public void setGameMatch(Model serverBaseModel) {
        clientState06GameMatchSetSelected.setGameMatch(serverBaseModel);
        currenState = clientState07GameMatchSelected;
    }

    // 7 interface IClientState07GameMatchSelected
    @Override
    public Model getServerBaseModel() {
        return clientState07GameMatchSelected.getServerBaseModel();
    }

    @Override
    public void forgetGameMatch() {
        clientState07GameMatchSelected.forgetGameMatch();
        currenState = clientState06GameMatchSetSelected;
    }

    @Override
    public void setGameMatchPlaying(Boolean gameIsPlaying) {
        clientState07GameMatchSelected.setGameMatchPlaying(gameIsPlaying);
        currenState = clientState08GameMatchPlaying;
    }

    // 8 interface IClientState08GameMatchPlaying
    @Override
    public Boolean getGameIsPlaying() {
        return clientState08GameMatchPlaying.getGameIsPlaying();
    }

    @Override
    public void forgetGameMatchPlaying() {
        clientState08GameMatchPlaying.forgetGameMatchPlaying();
        currenState = clientState07GameMatchSelected;
    }

    // class Object
    @Override
    public String toString() {
        return currenState.getClass().getSimpleName();
    }
}
