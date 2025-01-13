package timmax.tilegame.sokoban.model.placement.gameobject;

import timmax.tilegame.basemodel.exception.GameOverException;
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
        //  ToDo:   Вынести отсюда логику по отслеживанию координат для перерисовки и
        //          переместить её, вероятно, в SGOCollisionMovableObject.
        //  Множество координат, в которых будет движение
        Set<XYCoordinate> xyCoordinateSet = new HashSet<>();

        //  Координаты после смещения (новые)
        XYCoordinate xyCoordinateNew = getXyCoordinate().getXYCoordinateByOffset(xyOffset, getGameObjectsPlacement().getWidthHeightSizes());
        //  Ищем множество объектов в новых координатах (оно может быть и пустым или с одним элеиентом)
        Set<GameObject> gameObjectSetInNewPlace = getGameObjectsPlacement().getGameObjectSetFilteredByXYCoordinate(xyCoordinateNew);
        //  В этом множестве фильтруем только коробки
        GameObject gameObjectInNewPlace = gameObjectSetInNewPlace
                .stream()
                .filter(go -> go instanceof SGOBox)
                .findAny()
                .orElse(null);

        int countBoxesOnHomes = 0;
        int countBoxes = 0;
        //  Если коробка была найдена
        if (gameObjectInNewPlace instanceof SGOBox sgoBox) {
            //  Двигаем коробку
            sgoBox.move(xyOffset);
            //  Во множество координат, где было движение, добавляем новые координаты коробки
            //  (1)
            xyCoordinateSet.add(sgoBox.getXyCoordinate());

            //  Посчитаем количество коробок, стоящих на домах
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
                countBoxes++;
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
        //  Во множество координат, где было движение, добавляем старые координаты игрока
        //  1 (2)
        xyCoordinateSet.add(getXyCoordinate());
        //  Переместим игрока
        super.move(xyOffset);
        //  Во множество координат, где было движение, добавляем новые координаты игрока
        //  2 (3)
        xyCoordinateSet.add(getXyCoordinate());

        //  Для каждой плитки, в которой было движение, отправим сообщение клиентам (для перерисовки)
        xyCoordinateSet.forEach(xyCoordinate1 -> getGameObjectsPlacement().sendGameEventToAllViews(xyCoordinate1));

        //  Условие достижения конца игры с успехом
        if (countBoxesOnHomes > 0 && countBoxes == countBoxesOnHomes) {
            throw new GameOverException();
        }
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
