package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObject;
import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementNotVerified;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;

public class SokobanGameObject extends GameObject {
    public SokobanGameObject(String id, GameObjectsPlacementNotVerified gameObjectsPlacementNotVerified, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacementNotVerified, xyCoordinate);
    }

    @Override
    public SokobanPlacementVerified getGameObjectsPlacement() {
        return (SokobanPlacementVerified) super.getGameObjectsPlacement();
    }
}
