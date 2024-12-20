package timmax.tilegame.minesweeper.model.placement.gameobject;

import timmax.tilegame.minesweeper.model.gameevent.GameEventOneTileMinesweeperOpenNoMine;

public class MGOSNoMineIsOpened extends MGOSNoMine {
    public MGOSNoMineIsOpened(MGOStateAutomaton MGOStateAutomaton) {
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
        getGameObjectStateAutomaton().initNeighbourSet();

        //  ToDo:   Вместо
        //          getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getRemoteClientStateAutomaton().getTransportOfServer()
        //          сделать getTransportOfServer(), который будет доставаться сразу из свойств сервера.
        //  Warning:(24, 9) Unchecked call to 'sendGameEventToAllViews(GameEvent, Map<String, Class<? extends View>>)' as a member of raw type 'timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton'
        getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getRemoteClientStateAutomaton().getSenderOfEventOfServer().sendGameEventToAllViews(
                getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getMatchPlayerList(),
                new GameEventOneTileMinesweeperOpenNoMine(
                        getGameObjectStateAutomaton().getXyCoordinate(),
                        getGameObjectStateAutomaton().countOfMinesInNeighbours
                ),
                //  Warning:(31, 17) Unchecked assignment: 'java.util.Map' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'. Reason: 'getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getGameType()' has raw type, so result of getViewName_ViewClassMap is erased
                getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getGameType().getViewName_ViewClassMap()
        );

        if (getGameObjectStateAutomaton().countOfMinesInNeighbours > 0) {
            return;
        }
        for (MGOStateAutomaton neighbour : getGameObjectStateAutomaton().getNeighbourSet()) {
            if (neighbour.getGameObjectState() instanceof MGOSNoMineIsNotOpenedWithoutFlag) {
                neighbour.open();
            }
        }
    }
}
