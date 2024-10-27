package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.game.minesweeper.model.gameevent.GameEventOneTileMinesweeperChangeFlag;

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
