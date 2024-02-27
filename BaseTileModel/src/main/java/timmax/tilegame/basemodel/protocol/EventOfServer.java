package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

public abstract class EventOfServer extends Event {
    public abstract void executeOnClient(IModelOfClient iModelOfClient);

    @Override
    public String toString() {
        return "EventOfServer{}";
    }
}
