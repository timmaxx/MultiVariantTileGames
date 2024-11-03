package timmax.tilegame.sokoban.model.placement.gameobject;

import timmax.tilegame.basemodel.placement.placementstate.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;

public class SGOHome extends SokobanGameObject {
    public SGOHome(String id, GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacementStateAutomaton, xyCoordinate);
    }

    @Override
    public String toString() {
        return "SGOHome{" +
                "xyCoordinate=" + xyCoordinate +
                '}';
    }
}
