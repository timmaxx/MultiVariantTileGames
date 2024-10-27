package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.game.minesweeper.model.gameevent.GameEventOneTileMinesweeperChangeFlag;
import timmax.tilegame.game.minesweeper.model.gameevent.GameEventOneTileMinesweeperOpenNoMine;

public class MGOSNoMineIsNotOpenedWithoutFlag extends MGOSNoMine {
    public MGOSNoMineIsNotOpenedWithoutFlag(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public void open() {
        getGameObjectStateAutomaton().setCurrentStateNoMineIsOpened();

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

    @Override
    public void inverseFlag() {
        getGameObjectStateAutomaton().setCurrentStateNoMineIsNotOpenedWithFlag();

        getGameObjectStateAutomaton()
                .getGameObject()
                .getGameObjectsPlacement()
                .getGameMatch()
                .sendGameEventToAllViews(
                        new GameEventOneTileMinesweeperChangeFlag(
                                getGameObjectStateAutomaton().getXyCoordinate(),
                                true
                        )
                )
        ;
    }
}
