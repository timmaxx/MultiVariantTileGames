package timmax.tilegame.basemodel.protocol.server_client.state;

import timmax.commons.state.StateData;

public class StateDataOf07MatchSelected<Model> implements StateData {
    private final Model serverBaseModel; // ---- 7 (Конкретная модель игры)

    public StateDataOf07MatchSelected(Model serverBaseModel) {
        this.serverBaseModel = serverBaseModel;
    }

    public Model getServerBaseModel() {
        return serverBaseModel;
    }
}
