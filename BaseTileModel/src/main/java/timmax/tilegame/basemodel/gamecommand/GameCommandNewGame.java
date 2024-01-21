package timmax.tilegame.basemodel.gamecommand;

import timmax.tilegame.transport.TransportOfServer;

public class GameCommandNewGame extends GameCommand {
    @Override
    public <ClientId> void executeOnServer(TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        System.out.println("    onNewGame");
        transportOfServer
                .getRemoteClientStateByClientId(clientId)
                .getServerBaseModel()
                .createNewGame();
    }
}
