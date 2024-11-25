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
    protected void doWhenTurnOn() {
        getGameObjectStateAutomaton()
                .getGameObject()
                .getGameObjectsPlacement()
                .getGameMatch()
                .sendGameEventToAllViews(
                        new GameEventOneTileMinesweeperOpenMine(
                                getGameObjectStateAutomaton().getXyCoordinate()
                        )
                );
        getGameObjectStateAutomaton()
                .getGameObject()
                .getGameObjectsPlacement()
                .getGameMatch()
                .sendGameEventToAllViews(new GameEventGameOver(DEFEAT));

        throw new GameOverException();
    }
}
