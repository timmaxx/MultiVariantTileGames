package timmax.tilegame.minesweeper.model.placement.gameobject;

import timmax.tilegame.minesweeper.model.gameevent.GameEventOneTileMinesweeperChangeFlag;

public class MGOSMineIsNotOpenedWithoutFlag extends MGOSMine {
    //  ToDo:   Избавиться от этой переменной.
    //          ЗАЛЕПУХА!
    //          Дело в том, что состояния ...NotOpenedWithoutFlag - это первичные состояния. А при инициализации
    //          каждой ячейки также будет попытка вызвать doWhenTurnOn(). Но на этапе инициализации всего поля
    //          не вся графическая составляющаяя готова. Поэтому пришлось ввести эту переменную,
    //          что-бы один раз её не выполнять для каждой плитки.
    private boolean isFirst = true;

    public MGOSMineIsNotOpenedWithoutFlag(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public void open() {
        getGameObjectStateAutomaton().setCurrentStateMineIsOpened();
    }

    @Override
    public void inverseFlag() {
        getGameObjectStateAutomaton().setCurrentStateMineIsNotOpenedWithFlag();
    }

    @Override
    protected void doAfterTurnOn() {
        if (isFirst) {
            isFirst = false;
            return;
        }

        //  ToDo:   Вместо
        //          getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getRemoteClientStateAutomaton().getTransportOfServer()
        //          сделать getTransportOfServer(), который будет доставаться сразу из свойств сервера.
        //  Warning:(35, 9) Unchecked call to 'sendGameEventToAllViews(GameEvent, Map<String, Class<? extends View>>)' as a member of raw type 'timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton'
        getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getRemoteClientStateAutomaton().getSenderOfEventOfServer().sendGameEventToAllViews(
                getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getMatchPlayerList(),
                new GameEventOneTileMinesweeperChangeFlag(
                        getGameObjectStateAutomaton().getXyCoordinate(),
                        false
                ),
                //  Warning:(41, 17) Unchecked assignment: 'java.util.Map' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'. Reason: 'getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getGameType()' has raw type, so result of getViewName_ViewClassMap is erased
                getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getGameType().getViewName_ViewClassMap()
        );
    }
}
