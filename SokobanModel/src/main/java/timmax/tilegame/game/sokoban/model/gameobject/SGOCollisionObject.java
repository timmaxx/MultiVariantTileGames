package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;

// For Walls, Boxes and Player
abstract public class SGOCollisionObject extends SokobanGameObject {
    public SGOCollisionObject(String id, GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacementStateAutomaton, xyCoordinate);
    }
}
