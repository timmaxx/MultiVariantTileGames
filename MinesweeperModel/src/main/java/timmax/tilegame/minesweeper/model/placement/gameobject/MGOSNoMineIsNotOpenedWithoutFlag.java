package timmax.tilegame.minesweeper.model.placement.gameobject;

import timmax.tilegame.basemodel.protocol.server.GameEventSender;
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

        GameEventSender.sendGameEventToAllViews(
                new GameEventOneTileMinesweeperChangeFlag(
                        getGameObjectStateAutomaton().getXyCoordinate(),
                        false
                ),
                //  Warning:(41, 17) Unchecked assignment: 'timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton' to 'timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton<java.lang.Object>'. Reason: 'getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch()' has raw type, so result of getRemoteClientStateAutomaton is erased
                getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getRemoteClientStateAutomaton(),
                // Warning:(42, 17) Unchecked assignment: 'java.util.Map' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'. Reason: 'getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getGameType()' has raw type, so result of getViewName_ViewClassMap is erased
                getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getGameType().getViewName_ViewClassMap()
        );
    }
}
