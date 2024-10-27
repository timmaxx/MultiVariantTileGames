package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementNotVerified;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;
import timmax.tilegame.basemodel.gameobject.XYOffsetOne;

public abstract class SGOCollisionMovableObject extends SGOCollisionObject implements Movable<XYOffsetOne> {
    public SGOCollisionMovableObject(String id, GameObjectsPlacementNotVerified gameObjectsPlacement, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacement, xyCoordinate);
    }

    @Override
    public void move(XYOffsetOne xyOffset) {
        XYCoordinate xyCoordinateNew = xyCoordinate.getXYCoordinateByOffset(xyOffset, gameObjectsPlacementAbstract.getWidthHeightSizes());
        if (getGameObjectsPlacement()
                .getGameObjectSetFilteredByXYCoordinate(xyCoordinateNew)
                .stream()
                .filter(go -> go instanceof SGOCollisionObject)
                .findAny()
                .orElse(null) != null) {
            //  ToDo:   Сделать отдельное исключение в BaseTileModel
            throw new RuntimeException("You cannot move " + this + " into new place " + xyCoordinateNew + " because in new place there is a collision object");
        }
        xyCoordinate = xyCoordinateNew;
    }
}
