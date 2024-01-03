package timmax.tilegame.basemodel.gamecommand;

import timmax.tilegame.transport.TransportOfServer;

public class GameCommandRestart extends GameCommand {
    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        // !!!
    }
}
