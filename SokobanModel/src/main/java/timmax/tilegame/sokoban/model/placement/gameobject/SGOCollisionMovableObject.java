package timmax.tilegame.sokoban.model.placement.gameobject;

import timmax.tilegame.basemodel.exception.GameObjectAlreadyExistsException;
import timmax.tilegame.basemodel.placement.placementstate.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.sokoban.model.placement.primitives.SokobanXYOffset;

public abstract class SGOCollisionMovableObject extends SGOCollisionObject implements Movable {
    public SGOCollisionMovableObject(String id, GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacementStateAutomaton, xyCoordinate);
    }

    @Override
    public void move(SokobanXYOffset xyOffset) {
        XYCoordinate xyCoordinateOld = xyCoordinate;
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
        getGameObjectsPlacement().sendGameEventToAllViews(xyCoordinateOld);
        getGameObjectsPlacement().sendGameEventToAllViews(xyCoordinate);
    }
}
