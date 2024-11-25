package timmax.tilegame.minesweeper.model.placement.gameobject;

import timmax.tilegame.minesweeper.model.gameevent.GameEventOneTileMinesweeperChangeFlag;
import timmax.tilegame.minesweeper.model.gameevent.GameEventOneTileMinesweeperOpenNoMine;

public class MGOSNoMineIsNotOpenedWithoutFlag extends MGOSNoMine {
    public MGOSNoMineIsNotOpenedWithoutFlag(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public void open() {
        getGameObjectStateAutomaton().setCurrentStateNoMineIsOpened();

        //  ToDo:   Код ниже в этом методе лучше перенести в метод "выполнить при входе в состояние".

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

        //  ToDo:   Код ниже в это методе лучше перенести в метод "выполнить при входе в состояние".

        //  Код ниже идентичен коду в MGOSMineIsNotOpenedWithoutFlag :: void inverseFlag()
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
