package timmax.tilegame.basemodel.protocol.cs.inners;

import java.util.Set;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.cs.IClientState;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

public class ClientState<Model> implements IClientState<Model> {
    private final IClientStateProtected<Model> cSP02ConnectNonIdent;
    private final IClientStateProtected<Model> cSP03ConnectAuthorized;
    private final IClientStateProtected<Model> cSP04GameTypeSetSelected;
    private final IClientStateProtected<Model> cSP05GameTypeSelected;
    private final IClientStateProtected<Model> cSP06MatchSetSelected;
    private final IClientStateProtected<Model> csp07MatchSelected;
    private final IClientStateProtected<Model> cSP08GameIsPlaying;

    private IClientStateProtected<Model> iCSPCurrent;

    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:
    protected String userName; // ---- 2 (Пользователь)
    protected Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor; // ---- 3 (Список типов игр)
    protected ModelOfServerDescriptor modelOfServerDescriptor; // ---- 4 (Конкретный тип игры)
    protected Set<Model> setOfServerBaseModel; // ---- 5 (Набор моделей игр)
    protected Model serverBaseModel; // ---- 6 (Конкретная модель игры)
    protected Boolean gameIsPlaying; // ---- 7 (Партия была начата)

    public ClientState() {
        cSP02ConnectNonIdent = new CSP02ConnectNonIdent<>(this);
        cSP03ConnectAuthorized = new CSP03ConnectAuthorized<>(this);
        cSP04GameTypeSetSelected = new CSP04GameTypeSetSelected<>(this);
        cSP05GameTypeSelected = new CSP05GameTypeSelected<>(this);
        cSP06MatchSetSelected = new CSP06MatchSetSelected<>(this);
        csp07MatchSelected = new CSP07MatchSelected<>(this);
        cSP08GameIsPlaying = new CSP08GameIsPlaying<>(this);

        iCSPCurrent = cSP02ConnectNonIdent;
    }

    public IClientStateProtected<Model> getCSP02ConnectNonIdent() {
        return cSP02ConnectNonIdent;
    }

    public IClientStateProtected<Model> getCSP03ConnectAuthorized() {
        return cSP03ConnectAuthorized;
    }

    public IClientStateProtected<Model> getCSP04GameTypeSetSelected() {
        return cSP04GameTypeSetSelected;
    }

    public IClientStateProtected<Model> getCSP05GameTypeSelected() {
        return cSP05GameTypeSelected;
    }

    public IClientStateProtected<Model> getCSP06MatchSetSelected() {
        return cSP06MatchSetSelected;
    }

    public IClientStateProtected<Model> getCsp07MatchSelected() {
        return csp07MatchSelected;
    }

    public IClientStateProtected<Model> getcSP08GameIsPlaying() {
        return cSP08GameIsPlaying;
    }

    void setICSPCurrent(IClientStateProtected<Model> iCSPCurrent) {
        this.iCSPCurrent = iCSPCurrent;
    }

    // ---- 2 ConnectNonIdent
    @Override
    public void setUserName(String userName) {
        iCSPCurrent.setUserName(userName);
    }

    // ---- 3 ConnectAuthorized
    @Override
    public String getUserName() {
        return iCSPCurrent.getUserName();
    }

    @Override
    public void forgetUserName() {
        iCSPCurrent.forgetUserName();
    }

    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        iCSPCurrent.setGameTypeSet(setOfModelOfServerDescriptor);
    }

    // ---- 4 (GameTypeSetSelected)
    @Override
    public Set<ModelOfServerDescriptor> getGameTypeSet() {
        return iCSPCurrent.getGameTypeSet();
    }

    @Override
    public void forgetGameTypeSet() {
        iCSPCurrent.forgetGameTypeSet();
    }

    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        iCSPCurrent.setGameType(modelOfServerDescriptor);
    }

    // ---- 5 (GameTypeSelected)
    @Override
    public ModelOfServerDescriptor getGameType() {
        return iCSPCurrent.getGameType();
    }

    @Override
    public void forgetGameType() {
        iCSPCurrent.forgetGameType();
    }

    @Override
    public void setGameMatchSet(Set<Model> setOfServerBaseModel) {
        iCSPCurrent.setGameMatchSet(setOfServerBaseModel);
    }

    // ---- 6 (MatchSetSelected)
    @Override
    public Set<Model> getGameMatchSet() {
        return iCSPCurrent.getGameMatchSet();
    }

    @Override
    public void forgetGameMatchSet() {
        iCSPCurrent.forgetGameMatchSet();
    }

    @Override
    public void setServerBaseModel(Model serverBaseModel) {
        iCSPCurrent.setServerBaseModel(serverBaseModel);
    }

    // ---- 7 (MatchSelected)
    @Override
    public Model getServerBaseModel() {
        return iCSPCurrent.getServerBaseModel();
    }

    @Override
    public void forgetServerBaseModel() {
        iCSPCurrent.forgetServerBaseModel();
    }

    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        iCSPCurrent.setGameIsPlaying(gameIsPlaying);
    }

    // ---- 8 (GameIsPlaying)
    @Override
    public Boolean getGameIsPlaying() {
        return iCSPCurrent.getGameIsPlaying();
    }

    @Override
    public void forgetGameIsPlaying() {
        iCSPCurrent.forgetGameIsPlaying();
    }

    // ---- X
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return iCSPCurrent.getMainGameClientStatus();
    }
}
