package timmax.tilegame.sokoban.model.placement.gameobject;

import timmax.tilegame.basemodel.placement.placementstate.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;

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
        return
                SGOBox.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                "xyCoordinate=" + xyCoordinate +
                '}';
    }
}
