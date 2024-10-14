package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.gameobject.*;
import timmax.tilegame.basemodel.protocol.server.GameMatch;

public class MinesweeperPlacementNotVerified extends GameObjectsPlacementNotVerified {
    public MinesweeperPlacementNotVerified(GameMatch gameMatch, WidthHeightSizes widthHeightSizes) {
        super(gameMatch, widthHeightSizes);
    }
}
