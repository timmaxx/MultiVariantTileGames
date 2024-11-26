package timmax.tilegame.minesweeper.model.placement.gameobject;

import timmax.tilegame.basemodel.protocol.server.GameEventSender;
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
        GameEventSender.sendGameEventToAllViews(
                new GameEventOneTileMinesweeperChangeFlag(
                        getGameObjectStateAutomaton().getXyCoordinate(),
                        true
                ),
                //  Warning:(28, 17) Unchecked assignment: 'timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton' to 'timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton<java.lang.Object>'. Reason: 'getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch()' has raw type, so result of getRemoteClientStateAutomaton is erased
                getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getRemoteClientStateAutomaton(),
                //  Warning:(29, 17) Unchecked assignment: 'java.util.Map' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'. Reason: 'getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getGameType()' has raw type, so result of getViewName_ViewClassMap is erased
                getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getGameType().getViewName_ViewClassMap()
        );
    }
}
