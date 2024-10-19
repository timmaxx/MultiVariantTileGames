package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementNotVerified;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;
import timmax.tilegame.basemodel.gameobject.XYOffset;

public abstract class CollisionMovableObject extends CollisionObject implements IMovable {
    public CollisionMovableObject(String id, GameObjectsPlacementNotVerified gameObjectsPlacement, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacement, xyCoordinate);
    }

    public void move(XYOffset xyOffset) {
        xyCoordinate = xyCoordinate.getXYCoordinateByOffset(xyOffset, gameObjectsPlacementAbstract.getWidthHeightSizes());
    }
}
