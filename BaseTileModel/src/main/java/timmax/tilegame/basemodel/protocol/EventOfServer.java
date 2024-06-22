package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

public abstract class EventOfServer extends Event {
    // ToDo: В похожем классе EventOfServer параметром метода является
    // RemoteClientStateAutomaton
    // По аналогии и здесь должен быть LocalClientStateAutomaton
    public abstract void executeOnClient(IModelOfClient iModelOfClient);

    @Override
    public String toString() {
        return "EventOfServer{}";
    }
}
