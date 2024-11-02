package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.exception.GameObjectAlreadyExistsException;
import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;
import timmax.tilegame.basemodel.gameobject.XYOffsetOne;

public abstract class SGOCollisionMovableObject extends SGOCollisionObject implements Movable<XYOffsetOne> {
    public SGOCollisionMovableObject(String id, GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacementStateAutomaton, xyCoordinate);
    }

    @Override
    public void move(XYOffsetOne xyOffset) {
        XYCoordinate xyCoordinateNew = xyCoordinate.getXYCoordinateByOffset(xyOffset, gameObjectsPlacementStateAutomaton.getWidthHeightSizes());
        if (getGameObjectsPlacement()
                .getGameObjectSetFilteredByXYCoordinate(xyCoordinateNew)
                .stream()
                .filter(go -> go instanceof SGOCollisionObject)
                .findAny()
                .orElse(null) != null) {
            throw new GameObjectAlreadyExistsException(xyCoordinateNew);
        }
        xyCoordinate = xyCoordinateNew;
    }
}
