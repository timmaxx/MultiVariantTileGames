package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class TransportPackageOfClient92CreateNewGame<T> extends TransportPackageOfClient <T> {
    @Override
    public void execute(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onCreateNewGame");

        transportOfServer.getModelOfServer().createNewGame();
    }
}