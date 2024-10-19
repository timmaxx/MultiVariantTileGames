package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementNotVerified;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;
import timmax.tilegame.basemodel.gameobject.XYOffset;

// For Walls, Boxes and Player
abstract public class CollisionObject extends SokobanGameObject {
    public CollisionObject(String id, GameObjectsPlacementNotVerified gameObjectsPlacementNotVerified, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacementNotVerified, xyCoordinate);
    }

    public boolean isCollision(CollisionObject collisionObject, XYOffset xyOffset) {
        return xyCoordinate
                .getXYCoordinateByOffset(xyOffset, gameObjectsPlacementAbstract.getWidthHeightSizes())
                .equals(collisionObject.xyCoordinate)
                ;
    }

    public boolean isOutOfBoard(XYOffset xyOffset) {
        try {
            xyCoordinate
                    .getXYCoordinateByOffset(xyOffset, gameObjectsPlacementAbstract.getWidthHeightSizes())
            ;
            return false;
        } catch (RuntimeException rte) {
            return true;
        }
    }
}
