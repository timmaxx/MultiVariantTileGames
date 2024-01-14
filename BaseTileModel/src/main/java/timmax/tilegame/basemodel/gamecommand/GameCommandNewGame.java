package timmax.tilegame.basemodel.gamecommand;

import timmax.tilegame.transport.TransportOfServer;

public class GameCommandNewGame extends GameCommand {
    @Override
    public <ClienId> void executeOnServer(TransportOfServer<ClienId> transportOfServer, ClienId clientId) {
        System.out.println("    onNewGame");
        transportOfServer
                .getRemoteClientStateByClientId(clientId)
                .getServerBaseModel()
                .createNewGame();
    }
}
