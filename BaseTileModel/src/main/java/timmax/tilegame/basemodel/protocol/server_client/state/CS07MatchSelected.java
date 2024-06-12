package timmax.tilegame.basemodel.protocol.server_client.state;

import timmax.commons.state.StateContext;
import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

import java.util.Set;

public class CS07MatchSelected<Model> extends AStateOfMVTGClient<Model> {
    private Model serverBaseModel; // ---- 7 (Конкретная модель игры)

    public CS07MatchSelected(StateContext stateContext) {
        super(stateContext/*, StateDataOf07MatchSelected.class*/);

        setOfDestState.add(
                // new PairDestStateAndCanSwitchWithoutParams(CS08GameIsPlaying.class, false)
                CS08GameIsPlaying.class
        );

        setOfDestState.add(
                // new PairDestStateAndCanSwitchWithoutParams(CS02ConnectNonIdent.class, true)
                CS02ConnectNonIdent.class
        );
        setOfDestState.add(
                // new PairDestStateAndCanSwitchWithoutParams(CS03ConnectAuthorized.class, true)
                CS03ConnectAuthorized.class
        );
        setOfDestState.add(
                // new PairDestStateAndCanSwitchWithoutParams(CS04GameTypeSetSelected.class, true)
                CS04GameTypeSetSelected.class
        );
        setOfDestState.add(
                // new PairDestStateAndCanSwitchWithoutParams(CS05GameTypeSelected.class, true)
                CS05GameTypeSelected.class
        );
        setOfDestState.add(
                // new PairDestStateAndCanSwitchWithoutParams(CS06MatchSetSelected.class, true)
                CS06MatchSetSelected.class
        );
    }

    // ---- 2 ConnectNonIdent
    @Override
    public void setUserName(String userName, String password) {
        super.setUserName(userName, password);
        getStateAutomaton()._setUserName(userName, password);

    }

    // ---- 3 ConnectAuthorized
    @Override
    public String getUserName() {
        super.getUserName();
        return getStateAutomaton()._getUserName();
    }

    @Override
    public void forgetUserName() {
        super.forgetUserName();
        getStateAutomaton()._forgetUserName();
    }

    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        super.setGameTypeSet(setOfModelOfServerDescriptor);
        getStateAutomaton()._setGameTypeSet(setOfModelOfServerDescriptor);
    }

    // ---- 4
    @Override
    public Set<ModelOfServerDescriptor> getGameTypeSet() {
        super.getGameTypeSet();
        return getStateAutomaton()._getGameTypeSet();
    }

    @Override
    public void forgetGameTypeSet() {
        super.forgetGameTypeSet();
        getStateAutomaton()._forgetGameTypeSet();
    }

    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        super.setGameType(modelOfServerDescriptor);
        getStateAutomaton()._setGameType(modelOfServerDescriptor);
    }

    // ---- 5
    @Override
    public ModelOfServerDescriptor getGameType() {
        super.getGameType();
        return getStateAutomaton()._getGameType();
    }

    @Override
    public void forgetGameType() {
        super.forgetGameType();
        getStateAutomaton()._forgetGameType();
    }

    @Override
    public void setGameMatchSet(Set<Model> setOfServerBaseModel) {
        super.setGameMatchSet(setOfServerBaseModel);
        getStateAutomaton()._setGameMatchSet(setOfServerBaseModel);
    }

    // ---- 6
    @Override
    public Set<Model> getGameMatchSet() {
        super.getGameMatchSet();
        return getStateAutomaton()._getGameMatchSet();
    }

    @Override
    public void forgetGameMatchSet() {
        super.forgetGameMatchSet();
        getStateAutomaton()._forgetGameMatchSet();
    }

    @Override
    public void setServerBaseModel(Model serverBaseModel) {
        super.setServerBaseModel(serverBaseModel);
        getStateAutomaton()._setServerBaseModel(serverBaseModel);
    }

    // ---- 7
    @Override
    public Model getServerBaseModel() {
        super.getServerBaseModel();
        return (Model) getStateAutomaton()._getServerBaseModel();
    }

    @Override
    public void forgetServerBaseModel() {
        super.forgetServerBaseModel();
        getStateAutomaton()._forgetServerBaseModel();
    }

    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        super.setGameIsPlaying(gameIsPlaying);
        getStateAutomaton()._setGameIsPlaying(gameIsPlaying);
    }

    // ---- 8
    @Override
    public Boolean getGameIsPlaying() {
        super.getGameIsPlaying();
        throw new RuntimeException(toString());
    }

    @Override
    public void forgetGameIsPlaying() {
        super.forgetGameIsPlaying();
        throw new RuntimeException(toString());
    }

    // ---- X
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        super.getMainGameClientStatus();
        throw new RuntimeException(toString());
    }

    protected void _setServerBaseModel(Model serverBaseModel) {
        this.serverBaseModel = serverBaseModel;
    }

    protected Model _getServerBaseModel() {
        return serverBaseModel;
    }

    protected void _forgetServerBaseModel() {
        serverBaseModel = null;
    }
}
