package timmax.tilegame.basemodel.gamecommand;

import timmax.tilegame.basemodel.protocol.server.IGameMatch;

public class GameCommandNewGame extends GameCommand {
    @Override
    public void executeOnServer(IGameMatch iGameMatch) {
        // iGameMatch.start();
    }
}
