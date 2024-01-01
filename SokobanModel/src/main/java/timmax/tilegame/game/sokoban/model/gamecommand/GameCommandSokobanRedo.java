package timmax.tilegame.game.sokoban.model.gamecommand;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.transport.TransportOfServer;

import timmax.tilegame.game.sokoban.model.SokobanModel;

public class GameCommandSokobanRedo extends GameCommand {
    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        ((SokobanModel<T>) transportOfServer).moveRedo();
    }
}
