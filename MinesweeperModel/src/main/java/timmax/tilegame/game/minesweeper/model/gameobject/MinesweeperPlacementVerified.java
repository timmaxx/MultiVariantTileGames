package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementNotVerified;
import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementVerified;

public class MinesweeperPlacementVerified extends GameObjectsPlacementVerified {
    public MinesweeperPlacementVerified(GameObjectsPlacementNotVerified gameObjectsPlacementVerified) {
        super(gameObjectsPlacementVerified, 0);
    }
}
