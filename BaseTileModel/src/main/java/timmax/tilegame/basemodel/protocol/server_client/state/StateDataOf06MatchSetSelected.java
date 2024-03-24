package timmax.tilegame.basemodel.protocol.server_client.state;

import java.util.Set;

import timmax.commons.state.StateData;

public class StateDataOf06MatchSetSelected<Model> implements StateData {
    private final Set<Model> setOfServerBaseModel; // ---- 6 (Набор моделей игр)

    public StateDataOf06MatchSetSelected(Set<Model> setOfServerBaseModel) {
        this.setOfServerBaseModel = setOfServerBaseModel;
    }

    public Set<Model> getSetOfServerBaseModel() {
        return setOfServerBaseModel;
    }
}
