package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementNotVerified;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;

public class SGOBox extends SGOCollisionMovableObject {
    public SGOBox(String id, GameObjectsPlacementNotVerified gameObjectsPlacementNotVerified, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacementNotVerified, xyCoordinate);
    }

    public int countOnHome() {
        return getGameObjectsPlacement()
                .getGameObjectSetFilteredByXYCoordinate(xyCoordinate)
                .stream()
                .filter(go -> go instanceof SGOHome)
                .map(go -> 1)
                .findAny()
                .orElse(0);
    }

    @Override
    public String toString() {
        return "SGOBox{" +
                "xyCoordinate=" + xyCoordinate +
                '}';
    }
}
