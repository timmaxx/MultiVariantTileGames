package timmax.tilegame.basemodel.protocol.server_client.state;

import java.util.Set;

import timmax.commons.state.StateData;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

public class StateDataOf04GameTypeSetSelected implements StateData {
    private final Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor; // ---- 4 (Список типов игр)

    public StateDataOf04GameTypeSetSelected(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        this.setOfModelOfServerDescriptor = setOfModelOfServerDescriptor;
    }

    public Set<ModelOfServerDescriptor> getSetOfModelOfServerDescriptor() {
        return setOfModelOfServerDescriptor;
    }
}
