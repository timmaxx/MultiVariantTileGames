package timmax.tilegame.basemodel.gamecommand;

import timmax.tilegame.basemodel.protocol.server.IModelOfServer;

public class GameCommandNewGame extends GameCommand {
    @Override
    public void executeOnServer(IModelOfServer modelOfServer) {
        logger.info("New game");
        modelOfServer.createNewGame();
    }
}
