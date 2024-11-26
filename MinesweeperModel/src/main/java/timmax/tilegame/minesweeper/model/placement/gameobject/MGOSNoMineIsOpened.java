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

        getGameObjectStateAutomaton()
                .getGameObject()
                .getGameObjectsPlacement()
                .getGameMatch()
                .sendGameEventToAllViews(
                        new GameEventOneTileMinesweeperOpenNoMine(
                                getGameObjectStateAutomaton().getXyCoordinate(),
                                getGameObjectStateAutomaton().countOfMinesInNeighbours
                        )
                )
        ;

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
