package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.exception.GameOverException;
import timmax.tilegame.basemodel.gameevent.GameEventGameOver;
import timmax.tilegame.game.minesweeper.model.gameevent.GameEventOneTileMinesweeperChangeFlag;
import timmax.tilegame.game.minesweeper.model.gameevent.GameEventOneTileMinesweeperOpenMine;

import static timmax.tilegame.basemodel.GameMatchStatus.DEFEAT;

public class MGOSMineIsNotOpenedWithoutFlag extends MGOSMine {
    public MGOSMineIsNotOpenedWithoutFlag(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public void open() {
        getGameObjectStateAutomaton().setCurrentStateMineIsOpened();

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

    @Override
    public void inverseFlag() {
        getGameObjectStateAutomaton().setCurrentStateMineIsNotOpenedWithFlag();

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
