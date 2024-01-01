package timmax.tilegame.game.sokoban.model.gamecommand;

import timmax.tilegame.basemodel.gamecommand.GameCommandNextLevel;
import timmax.tilegame.transport.TransportOfServer;

import timmax.tilegame.game.sokoban.model.SokobanModel;

public class GameCommandSokobanRestart extends GameCommandNextLevel {
    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        ((SokobanModel<T>) transportOfServer).restart();
    }
}
