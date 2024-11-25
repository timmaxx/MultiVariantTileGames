package timmax.tilegame.minesweeper.model.placement.gameobject;

import timmax.tilegame.minesweeper.model.gameevent.GameEventOneTileMinesweeperChangeFlag;

public class MGOSNoMineIsNotOpenedWithFlag extends MGOSNoMine {
    public MGOSNoMineIsNotOpenedWithFlag(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public void open() {
        //  Ничего
    }

    @Override
    public void inverseFlag() {
        getGameObjectStateAutomaton().setCurrentStateNoMineIsNotOpenedWithoutFlag();

        //  ToDo:   Код ниже в этом методе лучше перенести в метод "выполнить при входе в состояние".

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
