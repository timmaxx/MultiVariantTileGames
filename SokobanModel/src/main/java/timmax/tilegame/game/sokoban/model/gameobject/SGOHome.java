package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementNotVerified;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;

public class SGOHome extends SokobanGameObject {
    public SGOHome(String id, GameObjectsPlacementNotVerified gameObjectsPlacementNotVerified, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacementNotVerified, xyCoordinate);
    }

    @Override
    public String toString() {
        return "SGOHome{" +
                "xyCoordinate=" + xyCoordinate +
                '}';
    }
}
