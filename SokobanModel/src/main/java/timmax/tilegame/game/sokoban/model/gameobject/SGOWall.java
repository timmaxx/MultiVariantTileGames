package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;

public class SGOWall extends SGOCollisionObject {
    public SGOWall(String id, GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacementStateAutomaton, xyCoordinate);
    }

    @Override
    public String toString() {
        return "SGOWall{" +
                "xyCoordinate=" + xyCoordinate +
                '}';
    }
}
