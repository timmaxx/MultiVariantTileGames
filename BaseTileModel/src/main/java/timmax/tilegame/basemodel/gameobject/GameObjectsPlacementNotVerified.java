package timmax.tilegame.basemodel.gameobject;

//  Класс нужен для первичной инициализации размещения, когда размещение не сможет пройти проверку на целостность:
//  - размер поля при инициализации может быть не известен.
//      (например, для игр у которых карта может иметь различные размеры на разных уровнях -
//      тогда размеры поля будут динамически пересчитаны при добавлении нового объекта).
//  - не выполняется целостность расстановки (а это нормальная ситуация при добавлении объектов по одному),
//    т.е. не может быть начальной расстановкой для старта матча:
//      - пустое поле (для некоторых типов игр).
//          Например, для Шахмат, Шашек, Сапёра, Сокобан.
//      - расстановка, заполненная объектами, не удовлетворяет правилам типа игры.
//          Например, для Шахмат:
//          - расстановка, на которой нет короля для хотя-бы одной из сторон.
//          - расстановка, на которой есть два и более короля для хотя-бы одной из сторон
//            (хотя при такой ситуации не получится пройти проверку на целостность даже и после, т.к. допустимо только
//            добавление объекта, а методов редактирования и удаления не должно быть).
//  В экземпляр этого класса можно произвольно добавлять игровой объект, невзирая на правила.

import timmax.tilegame.basemodel.protocol.server.GameMatch;

//  Расположение игровых объектов (матча) без проверки целостности.
public class GameObjectsPlacementNotVerified extends GameObjectsPlacementAbstract {
    //  Можно-ли в экземпляр добавлять объекты.
    private boolean isAddable;

    public GameObjectsPlacementNotVerified(GameMatch gameMatch, WidthHeightSizes widthHeightSizes) {
        super(gameMatch, widthHeightSizes);
        this.isAddable = true;
    }

    public GameObjectsPlacementNotVerified(GameMatch gameMatch) {
        super(gameMatch);
        this.isAddable = true;
    }

    public void add(GameObjectStateAutomaton gameObjectStateAutomaton) {
        if (!isAddable) {
            throw new RuntimeException("Property 'isAddable' == false, so you cannot add any object.");
        }
        if (!getWidthHeightSizes().mayBeRecalc()) {
            getWidthHeightSizes().validateXYCoordinate(gameObjectStateAutomaton.getXyCoordinate());
        }
        if (!gameObjectStateAutomatonSet.add(gameObjectStateAutomaton)) {
            throw new RuntimeException("You cannot add gameObjectStateAutomaton if there is the same one.");
        }
        if (getWidthHeightSizes().mayBeRecalc()) {
            getWidthHeightSizes().recalc(gameObjectStateAutomaton.getXyCoordinate());
        }
    }

    //  Сделать расстановку не редактируемой.
    //  Такой вызов предполагает, что после него можно проверять расстановку на целостность.
    public void turnAddableToFalse() {
        isAddable = false;
        getWidthHeightSizes().setMayBeReaclFalse();
    }

    //  Сделать расстановку не редактируемой с введением альтернативной ширины и высоты поля
    //  (ширина и/или высота должны быть не меньше, чем уже получилось при расстановке).
    public void turnAddableToFalse(WidthHeightSizes widthHeightSizes) {
        getWidthHeightSizes().recalc(widthHeightSizes);
        turnAddableToFalse();
    }

    @Override
    public String toString() {
        return super.toString() + " | " + "GameObjectsPlacementNotVerified{" +
                "isAddable=" + isAddable +
                // ", gameObjectStateAutomatonSet=" + gameObjectStateAutomatonSet +
                '}';
    }
}
