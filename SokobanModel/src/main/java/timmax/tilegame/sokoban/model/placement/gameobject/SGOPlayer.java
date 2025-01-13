package timmax.tilegame.sokoban.model.placement.gameobject;

import timmax.tilegame.basemodel.exception.GameOverException;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.sokoban.model.placement.primitives.SokobanXYOffset;
import timmax.tilegame.basemodel.placement.gameobject.GameObject;
import timmax.tilegame.basemodel.placement.placementstate.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfBoxesInHouses;

import java.util.Set;

public class SGOPlayer extends SGOCollisionMovableObject {
    public SGOPlayer(String id, GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacementStateAutomaton, xyCoordinate);
    }

    @Override
    public void move(SokobanXYOffset xyOffset) {
        //  Координаты после смещения (новые)
        XYCoordinate xyCoordinateNew = getXyCoordinate().getXYCoordinateByOffset(xyOffset, getGameObjectsPlacement().getWidthHeightSizes());
        //  Ищем множество объектов в новых координатах (оно может быть или пустым или с одним элементом)
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

            //  Посчитаем количество коробок, стоящих на домах
            for (GameObject gameObject : getGameObjectsPlacement().getGameObjectSetFilteredByGameObjectClass(SGOBox.class)) {
                countBoxes++;
                if (gameObject instanceof SGOBox sgoBox1) {
                    countBoxesOnHomes += sgoBox1.countOnHome();
                }
            }
        }
        //  Переместим игрока
        super.move(xyOffset);

        //  Условие достижения конца игры с успехом
        if (getGameObjectsPlacement().isGameOver()) {
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
