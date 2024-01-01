package timmax.tilegame.game.minesweeper.model.gamecommand;

import timmax.tilegame.basemodel.gamecommand.GameCommandOneTile;
import timmax.tilegame.transport.TransportOfServer;

import timmax.tilegame.game.minesweeper.model.MinesweeperModel;

public class GameCommandMinesweeperInverseFlag extends GameCommandOneTile {
    public GameCommandMinesweeperInverseFlag() {
        super();
    }

    public GameCommandMinesweeperInverseFlag(int x, int y) {
        super(x, y);
    }

    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        ((MinesweeperModel<T>) transportOfServer).inverseFlag(getX(), getY());
    }
}
