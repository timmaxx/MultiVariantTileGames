package timmax.tilegame.basemodel.gameevent;

import timmax.tilegame.transport.TransportOfServer;

import java.io.Externalizable;

public abstract class GameEvent implements Externalizable {
    public <T> void execute(TransportOfServer<T> transportOfServer, T clientId) {
    }
}