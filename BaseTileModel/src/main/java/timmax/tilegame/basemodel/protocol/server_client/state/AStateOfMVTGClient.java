package timmax.tilegame.basemodel.protocol.server_client.state;

import timmax.commons.state.AState;
import timmax.commons.state.StateContext;
import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.IStateOfMVTGClient;

import java.util.Set;

public class AStateOfMVTGClient<Model> extends AState implements IStateOfMVTGClient<Model> {
    public AStateOfMVTGClient(StateContext stateContext) {
        super(stateContext);
    }

    // class AState
    @Override
    public void doOnEnter() {
        System.out.println("MVTG Client has entered into state '" + this + "'");
    }

    @Override
    public StateContextForClientState getStateAutomaton() {
        return (StateContextForClientState) super.getStateAutomaton();
    }

    // ---- 2 ConnectNonIdent
    @Override
    public void setUserName(String userName, String password) {
        System.out.println("You are trying to call void setUserName(String userName, String password)");
    }

    // ---- 3 ConnectAuthorized
    @Override
    public String getUserName() {
        System.out.println("You are trying to call String getUserName()");
        return null;
    }

    @Override
    public void forgetUserName() {
        System.out.println("You are trying to call void forgetUserName()");
    }

    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        System.out.println("You are trying to call void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor)");
    }

    // ---- 4
    @Override
    public Set<ModelOfServerDescriptor> getGameTypeSet() {
        System.out.println("You are trying to call Set<ModelOfServerDescriptor> getGameTypeSet()");
        return null;
    }

    @Override
    public void forgetGameTypeSet() {
        System.out.println("You are trying to call void forgetGameTypeSet()");
    }

    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        System.out.println("You are trying to call void setGameType(ModelOfServerDescriptor modelOfServerDescriptor)");
    }

    // ---- 5
    @Override
    public ModelOfServerDescriptor getGameType() {
        System.out.println("You are trying to call ModelOfServerDescriptor getGameType()");
        return null;
    }

    @Override
    public void forgetGameType() {
        System.out.println("You are trying to call void forgetGameType()");
    }

    @Override
    public void setGameMatchSet(Set<Model> setOfServerBaseModel) {
        System.out.println("You are trying to call void setGameMatchSet(Set<Model> setOfServerBaseModel)");
    }

    // ---- 6
    @Override
    public Set<Model> getGameMatchSet() {
        System.out.println("You are trying to call Set<Model> getGameMatchSet()");
        return null;
    }

    @Override
    public void forgetGameMatchSet() {
        System.out.println("You are trying to call void forgetGameMatchSet()");
    }

    @Override
    public void setServerBaseModel(Model serverBaseModel) {
        System.out.println("You are trying to call void setServerBaseModel(Model serverBaseModel)");
    }

    // ---- 7
    @Override
    public Model getServerBaseModel() {
        System.out.println("You are trying to call Model getServerBaseModel()");
        return null;
    }

    @Override
    public void forgetServerBaseModel() {
        System.out.println("You are trying to call void forgetServerBaseModel()");
    }

    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        System.out.println("You are trying to call void setGameIsPlaying(Boolean gameIsPlaying)");
    }

    // ---- 8
    @Override
    public Boolean getGameIsPlaying() {
        System.out.println("You are trying to call Boolean getGameIsPlaying()");
        return null;
    }

    @Override
    public void forgetGameIsPlaying() {
        System.out.println("You are trying to call void forgetGameIsPlaying()");
    }

    // ---- X
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        System.out.println("You are trying to call MainGameClientStatus getMainGameClientStatus()");
        return null;
    }

    // class Object
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
