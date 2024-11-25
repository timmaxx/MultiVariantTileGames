package timmax.tilegame.basemodel.placement.placementstate;

import timmax.tilegame.basemodel.exception.GameObjectAlreadyExistsException;
import timmax.tilegame.basemodel.exception.WidthHeightIsNotAllowedRecalcException;
import timmax.tilegame.basemodel.exception.XYCoordinateIsOutOfRangeException;
import timmax.tilegame.basemodel.placement.gameobject.GameObjectStateAutomaton;
import timmax.tilegame.basemodel.placement.primitives.WidthHeightSizes;

//  Класс нужен для первичной инициализации расстановки, когда расстановка не сможет пройти проверку на целостность.
//  Т.е. не может быть начальной расстановкой для старта матча.
//  Например, целостность не будет достигнута если:
//  - размер поля при инициализации может быть не известен (т.е. до добавления хотя-бы первого объекта).
//      Например, для игр у которых карта может иметь различные размеры на разных уровнях -
//      тогда размеры поля будут динамически пересчитаны при добавлении нового объекта (Сокобан).
//  - нет обязательных объектов для данного типа игры.
//      Например, для Шахмат:
//          - нет короля для хотя-бы одной из сторон.
//      Например, для Сапёра:
//          - нет игрока.
//  - расстановка, заполненная объектами, не удовлетворяет правилам типа игры.
//      Например, для Шахмат:
//          - расстановка, на которой есть два и более короля для хотя-бы одной из сторон
//            (хотя при такой ситуации не получится пройти проверку на целостность даже и после, т.к. допустимо только
//            добавление объекта, а методов редактирования и удаления не должно быть).

//  В экземпляр этого класса можно произвольно добавлять игровой объект, невзирая на правила расстановки для типа игры.

//  Расстановка игровых объектов (матча) без проверки целостности.
public class GameObjectsPlacementNotVerifiedState extends GameObjectsPlacementAbstractState {
    public GameObjectsPlacementNotVerifiedState(GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton) {
        super(gameObjectsPlacementStateAutomaton);
    }

    //  Добавляется игровой объект в расстановку
    @Override
    public void add(GameObjectStateAutomaton gameObjectStateAutomaton)
            throws XYCoordinateIsOutOfRangeException, GameObjectAlreadyExistsException, WidthHeightIsNotAllowedRecalcException {
        if (getWidthHeightSizes().mayBeRecalc()) {
            getWidthHeightSizes().recalc(gameObjectStateAutomaton.getXyCoordinate());
        } else {
            getWidthHeightSizes().validateXYCoordinate(gameObjectStateAutomaton.getXyCoordinate());
        }
        if (!getGameObjectStateAutomatonSet().add(gameObjectStateAutomaton)) {
            throw new GameObjectAlreadyExistsException(gameObjectStateAutomaton.getXyCoordinate());
        }
    }

    //  Сделать расстановку целостной.
    //  Такой вызов предполагает, что после него можно проверять расстановку на целостность.
    @Override
    public void turnOnVerifable(int playerIndexOfCurrentMove) {
        getWidthHeightSizes().setMayBeReaclFalse();
        gameObjectsPlacementStateAutomaton.setCurrentStateVerified();
    }

    //  Сделать расстановку целостной с введением альтернативной ширины и высоты поля (большей, чем было вычислено).
    //  (ширина и/или высота должны быть не меньше, чем уже получилось при расстановке).
    @Override
    public void turnOnVerifable(int playerIndexOfCurrentMove, WidthHeightSizes widthHeightSizes) {
        getWidthHeightSizes().recalc(widthHeightSizes);
        turnOnVerifable(playerIndexOfCurrentMove);
    }
}
