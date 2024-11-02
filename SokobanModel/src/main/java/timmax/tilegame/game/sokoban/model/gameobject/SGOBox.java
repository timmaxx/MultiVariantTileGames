package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;

public class SGOBox extends SGOCollisionMovableObject {
    public SGOBox(String id, GameObjectsPlacementStateAutomaton gameObjectsPlacementNotVerifiedState, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacementNotVerifiedState, xyCoordinate);
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
