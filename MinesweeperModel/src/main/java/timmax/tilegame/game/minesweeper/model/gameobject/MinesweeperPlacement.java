package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementNotVerified;
import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementVerified;

public class MinesweeperPlacement extends GameObjectsPlacementVerified {
    public MinesweeperPlacement(GameObjectsPlacementNotVerified gameObjectsPlacementVerified) {
        super(gameObjectsPlacementVerified, 0);
    }
}
