package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.*;
import timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfBoxesInHouses;

import java.util.HashSet;
import java.util.Set;

public class SGOPlayer extends SGOCollisionMovableObject {
    public SGOPlayer(String id, GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacementStateAutomaton, xyCoordinate);
    }

    @Override
    public void move(XYOffsetOne xyOffset) {
        Set<XYCoordinate> xyCoordinateSet = new HashSet<>();

        XYCoordinate xyCoordinateNew = getXyCoordinate().getXYCoordinateByOffset(xyOffset, getGameObjectsPlacement().getWidthHeightSizes());
        Set<GameObject> gameObjectSetInNewPlace = getGameObjectsPlacement().getGameObjectSetFilteredByXYCoordinate(xyCoordinateNew);
        GameObject gameObjectInNewPlace = gameObjectSetInNewPlace
                .stream()
                .filter(go -> go instanceof SGOBox)
                .findAny()
                .orElse(null);

        if (gameObjectInNewPlace instanceof SGOBox sgoBox) {
            sgoBox.move(xyOffset);
            xyCoordinateSet.add(sgoBox.getXyCoordinate());

            int countBoxesOnHomes = 0;
            /*
            int countBoxesOnHomes = getGameObjectsPlacement().getBoxes().stream()
                    .collect(
                            Collectors.groupingBy(SGOBox::getClass, Collectors.summingInt(SGOBox::countOnHome))
                            // Collectors.toMap(SGOBox::getClass, SGOBox::countOnHome, Integer::sum)
                    )
            */

            // public Set<GameObject> getGameObjectSetFilteredByGameObjectClass(Class<GameObject> gameObjectClass)

            // for (SGOBox sgoBox1 : getGameObjectsPlacement().getBoxes())
            for (GameObject gameObject : (/*(Set<SGOBox>)*/(getGameObjectsPlacement().getGameObjectSetFilteredByGameObjectClass(SGOBox.class)))) {
                if (gameObject instanceof SGOBox sgoBox1) {
                    countBoxesOnHomes += sgoBox1.countOnHome();
                }
            }

            getGameObjectsPlacement()
                    .getGameMatch()
                    .sendGameEventToAllViews(
                            new GameEventSokobanVariableParamsCountOfBoxesInHouses(
                                    countBoxesOnHomes
                            )
                    )
            ;
        }
        xyCoordinateSet.add(getXyCoordinate());
        super.move(xyOffset);
        xyCoordinateSet.add(getXyCoordinate());
        xyCoordinateSet.forEach(
                xyCoordinate1 -> getGameObjectsPlacement()
                        .getGameMatch()
                        .addGameEvent(xyCoordinate1)
        );
    }

    @Override
    public String toString() {
        return "SGOPlayer{" +
                "xyCoordinate=" + xyCoordinate +
                '}';
    }
}
