package timmax.tilegame.basemodel.protocol.server_client.state;

import timmax.commons.state.StateContext;
import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.IStateAutomatonOfMVTGClient;

import java.util.Set;

public class StateContextForClientState<Model> extends StateContext implements IStateAutomatonOfMVTGClient<Model> {
    protected CS02ConnectNonIdent cs02ConnectNonIdent;
    protected CS03ConnectAuthorized cs03ConnectAuthorized;
    protected CS04GameTypeSetSelected cs04GameTypeSetSelected;
    protected CS05GameTypeSelected cs05GameTypeSelected;
    protected CS06MatchSetSelected<Model> cs06MatchSetSelected;
    protected CS07MatchSelected<Model> cs07MatchSelected;
    protected CS08GameIsPlaying cs08GameIsPlaying;

    public StateContextForClientState() {
        cs02ConnectNonIdent = new CS02ConnectNonIdent(this);
        cs03ConnectAuthorized = new CS03ConnectAuthorized(this);
        cs04GameTypeSetSelected = new CS04GameTypeSetSelected(this);
        cs05GameTypeSelected = new CS05GameTypeSelected(this);
        cs06MatchSetSelected = new CS06MatchSetSelected<>(this);
        cs07MatchSelected = new CS07MatchSelected<>(this);
        cs08GameIsPlaying = new CS08GameIsPlaying(this);

        changeState(cs02ConnectNonIdent);
    }

    // class StateContext
    @Override
    public AStateOfMVTGClient<Model> getCurrentState() {
        return (AStateOfMVTGClient<Model>) super.getCurrentState();
    }

    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 1 NoConnect
    // void open();

    // ---- 2 ConnectNonIdent
    // logIn
    @Override
    public void setUserName(String userName, String password) {
        getCurrentState().setUserName(userName, password);
    }
    // void close();

    // ---- 3 ConnectAuthorized
    @Override
    public String getUserName() {
        return getCurrentState().getUserName();
    }

    @Override
    public void forgetUserName() // logOff
    {
        getCurrentState().forgetUserName();
    }

    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        getCurrentState().setGameTypeSet(setOfModelOfServerDescriptor);
    }

    // ---- 4 (GameTypeSetSelected)
    @Override
    public Set<ModelOfServerDescriptor> getGameTypeSet() {
        return getCurrentState().getGameTypeSet();
    }

    @Override
    public void forgetGameTypeSet() {
        getCurrentState().forgetGameTypeSet();
    }

    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        getCurrentState().setGameType(modelOfServerDescriptor);
    }

    // ---- 5 (GameTypeSelected)
    @Override
    public ModelOfServerDescriptor getGameType() {
        return getCurrentState().getGameType();
    }

    @Override
    public void forgetGameType() {
        getCurrentState().forgetGameType();
    }

    @Override
    public void setGameMatchSet(Set<Model> setOfServerBaseModel) {
        getCurrentState().setGameMatchSet(setOfServerBaseModel);
    }

    // ---- 6 (MatchSetSelected)
    @Override
    public Set<Model> getGameMatchSet() {
        return getCurrentState().getGameMatchSet();
    }

    @Override
    public void forgetGameMatchSet() {
        getCurrentState().forgetGameMatchSet();
    }

    @Override
    public void setServerBaseModel(Model serverBaseModel) {
        getCurrentState().setServerBaseModel(serverBaseModel);
    }

    // ---- 7 (MatchSelected)
    @Override
    public Model getServerBaseModel() {
        return getCurrentState().getServerBaseModel();
    }

    @Override
    public void forgetServerBaseModel() {
        getCurrentState().forgetServerBaseModel();
    }

    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        getCurrentState().setGameIsPlaying(gameIsPlaying);
    }

    // ---- 8 (GameIsPlaying)
    @Override
    public Boolean getGameIsPlaying() {
        return getCurrentState().getGameIsPlaying();
    }

    @Override
    public void forgetGameIsPlaying() {
        getCurrentState().forgetGameIsPlaying();
    }

    // ---- X
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return getCurrentState().getMainGameClientStatus();
    }

    void _setUserName(String userName, String password) {
        cs03ConnectAuthorized._setUserName(userName, password);
        changeState(cs03ConnectAuthorized);
    }

    String _getUserName() {
        return cs03ConnectAuthorized._getUserName();
    }

    void _forgetUserName() {
        cs03ConnectAuthorized._forgetUserName();
        changeState(cs02ConnectNonIdent);
    }

    void _setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        cs04GameTypeSetSelected._setGameTypeSet(setOfModelOfServerDescriptor);
        changeState(cs04GameTypeSetSelected);
    }

    Set<ModelOfServerDescriptor> _getGameTypeSet() {
        return cs04GameTypeSetSelected._getGameTypeSet();
    }

    void _forgetGameTypeSet() {
        cs04GameTypeSetSelected._forgetGameTypeSet();
        changeState(cs03ConnectAuthorized);
    }

    void _setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        cs05GameTypeSelected._setGameType(modelOfServerDescriptor);
        changeState(cs05GameTypeSelected);
    }

    ModelOfServerDescriptor _getGameType() {
        return cs05GameTypeSelected._getGameType();
    }

    void _forgetGameType() {
        cs05GameTypeSelected._forgetGameType();
        changeState(cs04GameTypeSetSelected);
    }

    void _setGameMatchSet(Set<Model> setOfServerBaseModel) {
        cs06MatchSetSelected._setGameMatchSet(setOfServerBaseModel);
        changeState(cs06MatchSetSelected);
    }

    Set<Model> _getGameMatchSet() {
        return cs06MatchSetSelected._getGameMatchSet();
    }

    void _forgetGameMatchSet() {
        cs06MatchSetSelected._forgetGameMatchSet();
        changeState(cs05GameTypeSelected);
    }

    void _setServerBaseModel(Model serverBaseModel) {
        cs07MatchSelected._setServerBaseModel(serverBaseModel);
        changeState(cs07MatchSelected);
    }

    Model _getServerBaseModel() {
        return cs07MatchSelected._getServerBaseModel();
    }

    void _forgetServerBaseModel() {
        cs07MatchSelected._forgetServerBaseModel();
        changeState(cs06MatchSetSelected);
    }

    void _setGameIsPlaying(Boolean gameIsPlaying) {
        cs08GameIsPlaying._setGameIsPlaying(gameIsPlaying);
        changeState(cs08GameIsPlaying);
    }

    Boolean _getGameIsPlaying() {
        return cs08GameIsPlaying._getGameIsPlaying();
    }

    void _forgetGameIsPlaying() {
        cs08GameIsPlaying._forgetGameIsPlaying();
        changeState(cs07MatchSelected);
    }
}
