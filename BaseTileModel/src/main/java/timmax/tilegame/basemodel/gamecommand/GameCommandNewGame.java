package timmax.tilegame.basemodel.gamecommand;

import timmax.tilegame.basemodel.protocol.server.IModelOfServer;

public class GameCommandNewGame extends GameCommand {
    @Override
    public void executeOnServer(IModelOfServer modelOfServer) {
        System.out.println("    onNewGame");
        modelOfServer.createNewGame();
    }
}
