package timmax.tilegame.sokoban.model.placement.gameobject;

import timmax.tilegame.basemodel.exception.GameOverException;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.sokoban.model.placement.primitives.SokobanXYOffset;
import timmax.tilegame.basemodel.placement.placementstate.GameObjectsPlacementStateAutomaton;

public class SGOPlayer extends SGOCollisionMovableObject {
    public SGOPlayer(String id, GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton, XYCoordinate xyCoordinate) {
        super(id, gameObjectsPlacementStateAutomaton, xyCoordinate);
    }

    @Override
    public void move(SokobanXYOffset xyOffset) {
        //  Координаты после смещения (новые)
        XYCoordinate xyCoordinateNew = getXyCoordinate().getXYCoordinateByOffset(xyOffset, getGameObjectsPlacement().getWidthHeightSizes());
        getGameObjectsPlacement()
                //  Ищем множество объектов в новых координатах, по направлению движения игрока
                //  (оно может быть или пустым или с одним элементом)
                .getGameObjectSetFilteredByXYCoordinate(xyCoordinateNew)
                .stream()
                //  В этом множестве фильтруем только коробки
                .filter(go -> go instanceof SGOBox)
                .findAny()
                //  Если коробка была найдена, то перемещаем её
                .ifPresent(sgoBox -> ((SGOBox) sgoBox).move(xyOffset))
        ;
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
