package timmax.tilegame.basemodel.gameevent;

import java.io.Externalizable;

import timmax.tilegame.transport.TransportOfClient;
import timmax.tilegame.transport.TransportOfServer;

public abstract class GameEvent implements Externalizable {
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
    }

    public void executeOnClient(TransportOfClient transportOfClient) {
    }
}
