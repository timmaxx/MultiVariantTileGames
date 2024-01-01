package timmax.tilegame.game.minesweeper.model.gamecommand;

import timmax.tilegame.basemodel.gamecommand.GameCommandOneTile;
import timmax.tilegame.transport.TransportOfServer;

import timmax.tilegame.game.minesweeper.model.MinesweeperModel;

public class GameCommandMinesweeperOpen extends GameCommandOneTile {
    public GameCommandMinesweeperOpen() {
        super();
    }

    public GameCommandMinesweeperOpen(int x, int y) {
        super(x, y);
    }

    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        ((MinesweeperModel<T>) transportOfServer).open(getX(), getY());
    }
}
