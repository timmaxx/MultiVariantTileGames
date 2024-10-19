package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementNotVerified;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;

public class SGOBox extends CollisionMovableObject {
    public SGOBox(String id, GameObjectsPlacementNotVerified gameObjectsPlacementNotVerified, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacementNotVerified, xyCoordinate);
    }

    @Override
    public String toString() {
        return "SGOBox{" +
                "xyCoordinate=" + xyCoordinate +
                '}';
    }
}
