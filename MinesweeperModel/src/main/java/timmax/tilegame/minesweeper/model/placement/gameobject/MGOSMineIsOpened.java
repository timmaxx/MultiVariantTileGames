package timmax.tilegame.minesweeper.model.placement.gameobject;

import timmax.tilegame.basemodel.exception.GameOverException;
import timmax.tilegame.basemodel.gameevent.GameEventGameOver;
import timmax.tilegame.minesweeper.model.gameevent.GameEventOneTileMinesweeperOpenMine;

import static timmax.tilegame.basemodel.GameMatchStatus.DEFEAT;

public class MGOSMineIsOpened extends MGOSMine {
    public MGOSMineIsOpened(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public void open() {
        //  Ничего
    }

    @Override
    public void inverseFlag() {
        //  Ничего
    }

    @Override
    protected void doAfterTurnOn() {
        //  ToDo:   Вместо
        //          getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getRemoteClientStateAutomaton().getTransportOfServer()
        //          сделать getTransportOfServer(), который будет доставаться сразу из свойств сервера.
        //  Warning:(26, 9) Unchecked call to 'sendGameEventToAllViews(GameEvent, Map<String, Class<? extends View>>)' as a member of raw type 'timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton'
        getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getRemoteClientStateAutomaton().getTransportOfServer().sendGameEventToAllViews(
                getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getMatchPlayerList(),
                new GameEventOneTileMinesweeperOpenMine(
                        getGameObjectStateAutomaton().getXyCoordinate()
                ),
                //  Warning:(32, 17) Unchecked assignment: 'java.util.Map' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'. Reason: 'getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getGameType()' has raw type, so result of getViewName_ViewClassMap is erased
                getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getGameType().getViewName_ViewClassMap()
        );

        //  ToDo:   Вместо
        //          getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getRemoteClientStateAutomaton().getTransportOfServer()
        //          сделать getTransportOfServer(), который будет доставаться сразу из свойств сервера.
        //  Warning:(34, 9) Unchecked call to 'sendGameEventToAllViews(GameEvent, Map<String, Class<? extends View>>)' as a member of raw type 'timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton'
        getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getRemoteClientStateAutomaton().getTransportOfServer().sendGameEventToAllViews(
                getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getMatchPlayerList(),
                new GameEventGameOver(DEFEAT),
                //  Warning:(38, 17) Unchecked assignment: 'java.util.Map' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'. Reason: 'getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getGameType()' has raw type, so result of getViewName_ViewClassMap is erased
                getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getGameType().getViewName_ViewClassMap()
        );

        throw new GameOverException();
    }
}
