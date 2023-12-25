package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfClient;

public abstract class EventOfServer<T> extends Event {

    public abstract void executeOnClient(TransportOfClient<T> transportOfClient);

    @Override
    public String toString() {
        return "EventOfServer{}";
    }
}