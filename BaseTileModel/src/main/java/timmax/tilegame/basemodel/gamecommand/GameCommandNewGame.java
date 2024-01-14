package timmax.tilegame.basemodel.gamecommand;

import timmax.tilegame.transport.TransportOfServer;

public class GameCommandNewGame extends GameCommand {
    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("    onNewGame");
        transportOfServer
                .getRemoteClientStateByClientId(clientId)
                .getServerBaseModel()
                .createNewGame();
    }
}
