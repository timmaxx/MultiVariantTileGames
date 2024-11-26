package timmax.tilegame.minesweeper.model.placement.gameobject;

import timmax.tilegame.minesweeper.model.gameevent.GameEventOneTileMinesweeperChangeFlag;

public class MGOSNoMineIsNotOpenedWithoutFlag extends MGOSNoMine {
    //  ToDo:   Избавиться от этой переменной.
    //          ЗАЛЕПУХА!
    //          Дело в том, что состояния ...NotOpenedWithoutFlag - это первичные состояния. А при инициализации
    //          каждой ячейки также будет попытка вызвать doWhenTurnOn(). Но на этапе инициализации всего поля
    //          не вся графическая составляющаяя готова. Поэтому пришлось ввести эту переменную,
    //          что-бы один раз её не выполнять для каждой плитки.
    private boolean isFirst = true;

    public MGOSNoMineIsNotOpenedWithoutFlag(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public void open() {
        getGameObjectStateAutomaton().setCurrentStateNoMineIsOpened();
    }

    @Override
    public void inverseFlag() {
        getGameObjectStateAutomaton().setCurrentStateNoMineIsNotOpenedWithFlag();
    }

    @Override
    protected void doAfterTurnOn() {
        if (isFirst) {
            isFirst = false;
            return;
        }
        getGameObjectStateAutomaton()
                .getGameObject()
                .getGameObjectsPlacement()
                .getGameMatch()
                .sendGameEventToAllViews(
                        new GameEventOneTileMinesweeperChangeFlag(
                                getGameObjectStateAutomaton().getXyCoordinate(),
                                false
                        )
                )
        ;
    }
}
