package timmax.tilegame.sokoban.model.placement.gameobject;

import timmax.tilegame.basemodel.placement.gameobject.GameObject;
import timmax.tilegame.basemodel.placement.placementstate.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.sokoban.model.placement.placementstate.SokobanPlacementStateAutomaton;

public class SokobanGameObject extends GameObject {
    public SokobanGameObject(String id, GameObjectsPlacementStateAutomaton gameObjectsPlacementNotVerifiedState, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacementNotVerifiedState, xyCoordinate);
    }

    @Override
    public SokobanPlacementStateAutomaton getGameObjectsPlacement() {
        return (SokobanPlacementStateAutomaton) super.getGameObjectsPlacement();
    }
}
