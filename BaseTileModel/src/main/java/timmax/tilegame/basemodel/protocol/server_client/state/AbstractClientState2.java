package timmax.tilegame.basemodel.protocol.server_client.state;

import java.util.Set;

import timmax.commons.state.AState;
import timmax.commons.state.StateData;
import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.IClientState;

public class AbstractClientState2<Model> extends AState implements IClientState<Model> {
    public AbstractClientState2(StateContextForClientState stateContext, Class<? extends StateData> stateDataClass) {
        super(stateContext, stateDataClass);
    }

    @Override
    public StateContextForClientState getStateContext() {
        return (StateContextForClientState) super.getStateContext();
    }

    // ---- 2 ConnectNonIdent
    @Override
    public void setUserName(String userName) {
        getStateContext().changeState(getStateContext().cs03ConnectAuthorized, new StateDataOf03ConnectAuthorized(userName, ""));
    }

    // ---- 3 ConnectAuthorized
    @Override
    public String getUserName() {
        CS03ConnectAuthorized cs03ConnectAuthorized = getStateContext().cs03ConnectAuthorized;
        StateDataOf03ConnectAuthorized stateDataOf03ConnectAuthorized = (StateDataOf03ConnectAuthorized) cs03ConnectAuthorized.getData();
        return stateDataOf03ConnectAuthorized.getUserName();
    }

    @Override
    public void forgetUserName() {
        getStateContext().changeState(getStateContext().cs02ConnectNonIdent);
    }

    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        getStateContext().changeState(getStateContext().cs04GameTypeSetSelected, new StateDataOf04GameTypeSetSelected(setOfModelOfServerDescriptor));
    }

    // ---- 4 (GameTypeSetSelected)
    @Override
    public Set<ModelOfServerDescriptor> getGameTypeSet() {
        CS04GameTypeSetSelected cs04GameTypeSetSelected = getStateContext().cs04GameTypeSetSelected;
        StateDataOf04GameTypeSetSelected stateDataOf04GameTypeSetSelected = (StateDataOf04GameTypeSetSelected) cs04GameTypeSetSelected.getData();
        return stateDataOf04GameTypeSetSelected.getSetOfModelOfServerDescriptor();
    }

    @Override
    public void forgetGameTypeSet() {
        getStateContext().changeState(getStateContext().cs03ConnectAuthorized);
    }

    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        getStateContext().changeState(getStateContext().cs05GameTypeSelected, new StateDataOf05GameTypeSelected(modelOfServerDescriptor));
    }

    // ---- 5 (GameTypeSelected)
    @Override
    public ModelOfServerDescriptor getGameType() {
        CS05GameTypeSelected cs05GameTypeSelected = getStateContext().cs05GameTypeSelected;
        StateDataOf05GameTypeSelected stateDataOf05GameTypeSelected = (StateDataOf05GameTypeSelected) cs05GameTypeSelected.getData();
        return stateDataOf05GameTypeSelected.getModelOfServerDescriptor();
    }

    @Override
    public void forgetGameType() {
        getStateContext().changeState(getStateContext().cs04GameTypeSetSelected);
    }

    @Override
    public void setGameMatchSet(Set<Model> setOfServerBaseModel) {
        getStateContext().changeState(getStateContext().cs06MatchSetSelected, new StateDataOf06MatchSetSelected<Model>(setOfServerBaseModel));
    }

    // ---- 6 (MatchSetSelected)
    @Override
    public Set<Model> getGameMatchSet() {
        CS06MatchSetSelected cs06MatchSetSelected = getStateContext().cs06MatchSetSelected;
        StateDataOf06MatchSetSelected stateDataOf06MatchSetSelected = (StateDataOf06MatchSetSelected) cs06MatchSetSelected.getData();
        return stateDataOf06MatchSetSelected.getSetOfServerBaseModel();
    }

    @Override
    public void forgetGameMatchSet() {
        getStateContext().changeState(getStateContext().cs05GameTypeSelected);
    }

    @Override
    public void setServerBaseModel(Model serverBaseModel) {
        getStateContext().changeState(getStateContext().cs07MatchSelected, new StateDataOf07MatchSelected<>(serverBaseModel));
    }

    // ---- 7 (MatchSelected)
    @Override
    public Model getServerBaseModel() {
        CS07MatchSelected cs07MatchSelected = getStateContext().cs07MatchSelected;
        StateDataOf07MatchSelected stateDataOf07MatchSelected = (StateDataOf07MatchSelected) cs07MatchSelected.getData();
        return (Model) stateDataOf07MatchSelected.getServerBaseModel();
    }

    @Override
    public void forgetServerBaseModel() {
        getStateContext().changeState(getStateContext().cs06MatchSetSelected);
    }

    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        getStateContext().changeState(getStateContext().cs08GameIsPlaying, new StateDataOfCS08GameIsPlaying(gameIsPlaying));
    }

    // ---- 8 (GameIsPlaying)
    @Override
    public Boolean getGameIsPlaying() {
        CS08GameIsPlaying cs08GameIsPlaying = getStateContext().cs08GameIsPlaying;
        StateDataOfCS08GameIsPlaying stateDataOfCS08GameIsPlaying = (StateDataOfCS08GameIsPlaying) cs08GameIsPlaying.getData();
        return stateDataOfCS08GameIsPlaying.getGameIsPlaying();
    }

    @Override
    public void forgetGameIsPlaying() {
        getStateContext().changeState(getStateContext().cs07MatchSelected);
    }

    // ---- X
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return null;
    }
}
