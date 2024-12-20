package timmax.tilegame.minesweeper.model.placement.gameobject;

import timmax.tilegame.minesweeper.model.gameevent.GameEventOneTileMinesweeperChangeFlag;

public class MGOSMineIsNotOpenedWithFlag extends MGOSMine {
    public MGOSMineIsNotOpenedWithFlag(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public void open() {
        //  Ничего
    }

    @Override
    public void inverseFlag() {
        getGameObjectStateAutomaton().setCurrentStateMineIsNotOpenedWithoutFlag();
    }

    @Override
    protected void doAfterTurnOn() {
        //  ToDo:   Вместо
        //          getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getRemoteClientStateAutomaton().getTransportOfServer()
        //          сделать getTransportOfServer(), который будет доставаться сразу из свойств сервера.
        //  Warning:(22, 9) Unchecked call to 'sendGameEventToAllViews(GameEvent, Map<String, Class<? extends View>>)' as a member of raw type 'timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton'
        getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getRemoteClientStateAutomaton().getSenderOfEventOfServer().sendGameEventToAllViews(
                getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getMatchPlayerList(),
                new GameEventOneTileMinesweeperChangeFlag(
                        getGameObjectStateAutomaton().getXyCoordinate(),
                        true
                ),
                //  Warning:(29, 17) Unchecked assignment: 'java.util.Map' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'. Reason: 'getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getGameType()' has raw type, so result of getViewName_ViewClassMap is erased
                getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getGameType().getViewName_ViewClassMap()
        );
    }
}
