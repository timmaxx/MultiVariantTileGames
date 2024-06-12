package timmax.tilegame.basemodel.protocol.server_client.state;

import timmax.commons.state.StateContext;
import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

import java.util.Set;

public class CS02ConnectNonIdent<Model> extends AStateOfMVTGClient<Model> {
    public CS02ConnectNonIdent(StateContext stateContext) {
        super(stateContext/*, StateDataOf02ConnectNonIdent.class*/);

        setOfDestState.add(
                // new PairDestStateAndCanSwitchWithoutParams(CS03ConnectAuthorized.class, false)
                CS03ConnectAuthorized.class
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
        throw new RuntimeException(toString());
    }

    @Override
    public void forgetUserName() {
        super.forgetUserName();
        // Ok
    }

    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        super.setGameTypeSet(setOfModelOfServerDescriptor);
        throw new RuntimeException(toString());
    }

    // ---- 4
    @Override
    public Set<ModelOfServerDescriptor> getGameTypeSet() {
        super.getGameTypeSet();
        throw new RuntimeException(toString());
    }

    @Override
    public void forgetGameTypeSet() {
        super.forgetGameTypeSet();
        throw new RuntimeException(toString());
    }

    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        super.setGameType(modelOfServerDescriptor);
        throw new RuntimeException(toString());
    }

    // ---- 5
    @Override
    public ModelOfServerDescriptor getGameType() {
        super.getGameType();
        throw new RuntimeException(toString());
    }

    @Override
    public void forgetGameType() {
        super.forgetGameType();
        throw new RuntimeException(toString());
    }

    @Override
    public void setGameMatchSet(Set<Model> setOfServerBaseModel) {
        super.setGameMatchSet(setOfServerBaseModel);
        throw new RuntimeException(toString());
    }

    // ---- 6
    @Override
    public Set<Model> getGameMatchSet() {
        super.getGameMatchSet();
        throw new RuntimeException(toString());
    }

    @Override
    public void forgetGameMatchSet() {
        super.forgetGameMatchSet();
        throw new RuntimeException(toString());
    }

    @Override
    public void setServerBaseModel(Model serverBaseModel) {
        super.setServerBaseModel(serverBaseModel);
        throw new RuntimeException(toString());
    }

    // ---- 7
    @Override
    public Model getServerBaseModel() {
        super.getServerBaseModel();
        throw new RuntimeException(toString());
    }

    @Override
    public void forgetServerBaseModel() {
        super.forgetServerBaseModel();
        throw new RuntimeException(toString());
    }

    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        super.setGameIsPlaying(gameIsPlaying);
        throw new RuntimeException(toString());
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
}
