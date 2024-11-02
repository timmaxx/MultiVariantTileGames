package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObject;
import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;
import timmax.tilegame.game.sokoban.model.SokobanPlacementStateAutomaton;

public class SokobanGameObject extends GameObject {
    public SokobanGameObject(String id, GameObjectsPlacementStateAutomaton gameObjectsPlacementNotVerifiedState, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacementNotVerifiedState, xyCoordinate);
    }

    @Override
    public SokobanPlacementStateAutomaton getGameObjectsPlacement() {
        return (SokobanPlacementStateAutomaton) super.getGameObjectsPlacement();
    }
}
