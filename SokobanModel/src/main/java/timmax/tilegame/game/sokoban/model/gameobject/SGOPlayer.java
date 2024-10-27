package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.*;
import timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfBoxesInHouses;

import java.util.HashSet;
import java.util.Set;

public class SGOPlayer extends SGOCollisionMovableObject {
    public SGOPlayer(String id, GameObjectsPlacementNotVerified gameObjectsPlacement, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacement, xyCoordinate);
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

            int i/* = getGameObjectsPlacement().getBoxes().stream()
                    .collect(
                            Collectors.groupingBy(SGOBox::getClass, Collectors.summingInt(SGOBox::countOnHome))
                            // Collectors.toMap(SGOBox::getClass, SGOBox::countOnHome, Integer::sum)
                    )*/;
            i = 0;
            for (SGOBox sgoBox1 : getGameObjectsPlacement().getBoxes())
            // for (SGOBox sgoBox1 : (((SokobanPlacementVerified)(getGameObjectsPlacement())).getBoxes()))
            {
                i += sgoBox1.countOnHome();
            }

            getGameObjectsPlacement()
                    .getGameMatch()
                    .sendGameEventToAllViews(
                            new GameEventSokobanVariableParamsCountOfBoxesInHouses(
                                    i
                            )
                    )
            ;
        }
        xyCoordinateSet.add(getXyCoordinate());
        //  Если возникнет исключение, то не нужно делать изменения координат и рассылку сообщений об изменениях в плитках.
        //  Exception in thread "Thread-96" java.lang.RuntimeException: You cannot move SGOPlayer{xyCoordinate=XYCoordinate{x=1, y=3}} into new place XYCoordinate{x=0, y=3} because in new place there is a collision object
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
