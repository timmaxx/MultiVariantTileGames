package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

// ToDo:
//      1. Классы (см. ниже) свести в одну иерархию наследования.
//      2. Model желательно определить жёстче (через extends).
//      3. Переименовать Model в GameMatchX.
// В качестве параметра Model в классах наследниках используются:
// - IGameMatch и GameMatch для серверной стороны;
// - GameMatchId для клиента и для передачи по сети.
public class ClientStateAutomaton<Model> implements
        IClientState01NoConnect,
        IClientState02ConnectNonIdent,
        IClientState04GameTypeSetSelected<Model>,
        IClientState06GameMatchSetSelected<Model>,
        IClientState07GameMatchSelected<Model>,
        IClientState08GameMatchPlaying {
    final ClientState01NoConnect<Model> clientState01NoConnect;
    final ClientState02ConnectNonIdent<Model> clientState02ConnectNonIdent;
    final ClientState04GameTypeSetSelected<Model> clientState04GameTypeSetSelected;
    final ClientState06GameMatchSetSelected<Model> clientState06GameMatchSetSelected;
    final ClientState07GameMatchSelected<Model> clientState07GameMatchSelected;
    final ClientState08GameMatchPlaying<Model> clientState08GameMatchPlaying;

    private IClientState00 currenState;

    private String userName; // ---- 2 (Пользователь)
    private Set<GameType> gameTypeSet; // ---- 3 (Список типов игр)
    private GameType gameType; // ---- 4 (Конкретный тип игры)
    private Set<Model> gameMatchXSet; // ---- 5 (Набор моделей игр)
    private Model gameMatchX; // ---- 6 (Конкретная модель игры)
    private Boolean gameIsPlaying; // ---- 7 (Партия была начата)

    public ClientStateAutomaton(
            IFabricOfClientStates<Model> iFabricOfClientStates) {
        clientState01NoConnect = iFabricOfClientStates.getClientState01NoConnect(this);
        clientState02ConnectNonIdent = iFabricOfClientStates.getClientState02ConnectNonIdent(this);
        clientState04GameTypeSetSelected = iFabricOfClientStates.getClientState04GameTypeSetSelected(this);
        clientState06GameMatchSetSelected = iFabricOfClientStates.getClientState06GameMatchSetSelected(this);
        clientState07GameMatchSelected = iFabricOfClientStates.getClientState07GameMatchSelected(this);
        clientState08GameMatchPlaying = iFabricOfClientStates.getClientState08GameMatchPlaying(this);

        currenState = clientState01NoConnect;
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

    void setGameType_(GameType gameType, Set<Model> gameMatchXSet) {
        this.gameType = gameType;
        this.gameMatchXSet = gameMatchXSet;
    }

    Set<Model> getGameMatchXSet_() {
        return gameMatchXSet;
    }

    void setGameMatchXSet_(Set<Model> gameMatchXSet) {
        this.gameMatchXSet = gameMatchXSet;
    }

    Model getGameMatchX_() {
        return gameMatchX;
    }

    void setGameMatchX_(Model gameMatchX) {
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
        clientState02ConnectNonIdent.setUser(userName, gameTypeSet);
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
        currenState = clientState02ConnectNonIdent;
    }

    @Override
    public void setGameType(GameType gameType, Set<Model> gameMatchXSet) {
        clientState04GameTypeSetSelected.setGameType(gameType, gameMatchXSet);
        currenState = clientState06GameMatchSetSelected;
    }

    // ToDo: Этот метод был определён в интерфейсе IClientState05GameTypeSelected,
    //       а сейчас в interface IClientState04GameTypeSetSelected.
    @Override
    public GameType getGameType() {
        return gameType;
    }

    // 6 interface IClientState06GameMatchSetSelected
    @Override
    public Set<Model> getGameMatchXSet() {
        return clientState06GameMatchSetSelected.getGameMatchXSet();
    }

    @Override
    public void forgetGameMatchXSet() {
        clientState06GameMatchSetSelected.forgetGameMatchXSet();
        currenState = clientState04GameTypeSetSelected;
    }

    @Override
    public void setGameMatchX(Model gameMatchX) {
        clientState06GameMatchSetSelected.setGameMatchX(gameMatchX);
        currenState = clientState07GameMatchSelected;
    }

    // 7 interface IClientState07GameMatchSelected
    @Override
    public Model getGameMatchX() {
        return clientState07GameMatchSelected.getGameMatchX();
    }

    @Override
    public void forgetGameMatchX() {
        clientState07GameMatchSelected.forgetGameMatchX();
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
