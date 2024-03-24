package timmax.tilegame.basemodel.protocol.server_client.state;

import timmax.commons.state.StateData;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

public class StateDataOf05GameTypeSelected implements StateData {
    private final ModelOfServerDescriptor modelOfServerDescriptor; // ---- 5 (Конкретный тип игры)

    public StateDataOf05GameTypeSelected(ModelOfServerDescriptor modelOfServerDescriptor) {
        this.modelOfServerDescriptor = modelOfServerDescriptor;
    }

    public ModelOfServerDescriptor getModelOfServerDescriptor() {
        return modelOfServerDescriptor;
    }
}
