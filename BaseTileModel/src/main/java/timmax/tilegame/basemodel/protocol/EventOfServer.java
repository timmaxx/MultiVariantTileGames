package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfClient;

public abstract class EventOfServer extends Event {

    public abstract void executeOnClient(TransportOfClient transportOfClient);

    @Override
    public String toString() {
        return "EventOfServer{}";
    }
}
