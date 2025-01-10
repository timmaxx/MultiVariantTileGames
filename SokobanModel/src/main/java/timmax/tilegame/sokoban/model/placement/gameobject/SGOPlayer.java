package timmax.tilegame.sokoban.model.placement.gameobject;

import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.sokoban.model.placement.primitives.SokobanXYOffset;
import timmax.tilegame.basemodel.placement.gameobject.GameObject;
import timmax.tilegame.basemodel.placement.placementstate.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfBoxesInHouses;

import java.util.HashSet;
import java.util.Set;

public class SGOPlayer extends SGOCollisionMovableObject {
    public SGOPlayer(String id, GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacementStateAutomaton, xyCoordinate);
    }

    @Override
    public void move(SokobanXYOffset xyOffset) {
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
            for (GameObject gameObject : getGameObjectsPlacement().getGameObjectSetFilteredByGameObjectClass(SGOBox.class)) {
                if (gameObject instanceof SGOBox sgoBox1) {
                    countBoxesOnHomes += sgoBox1.countOnHome();
                }
            }

            //  ToDo:   Вместо
            //          getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getRemoteClientStateAutomaton().getSenderOfEventOfServer()
            //          сделать getSenderOfEventOfServer(), который будет доставаться сразу из свойств сервера.
            //  Warning:(51, 13) Unchecked call to 'sendGameEventToAllViews(GameEvent, Map<String, Class<? extends View>>)' as a member of raw type 'timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton'
            getGameObjectsPlacement().getGameMatch().getRemoteClientStateAutomaton().getSenderOfEventOfServer().sendGameEventToAllViews(
                    getGameObjectsPlacement().getGameMatch().getMatchPlayerList(),
                    new GameEventSokobanVariableParamsCountOfBoxesInHouses(countBoxesOnHomes),
                    //  Warning:(55, 21) Unchecked assignment: 'java.util.Map' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'. Reason: 'getGameObjectsPlacement().getGameMatch().getGameType()' has raw type, so result of getViewName_ViewClassMap is erased
                    getGameObjectsPlacement().getGameMatch().getGameType().getViewName_ViewClassMap()
            );
        }
        xyCoordinateSet.add(getXyCoordinate());
        super.move(xyOffset);
        xyCoordinateSet.add(getXyCoordinate());

        xyCoordinateSet.forEach(xyCoordinate1 -> getGameObjectsPlacement().sendGameEventToAllViews(xyCoordinate1));
    }

    @Override
    public String toString() {
        return
                SGOPlayer.class.getSimpleName()
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
