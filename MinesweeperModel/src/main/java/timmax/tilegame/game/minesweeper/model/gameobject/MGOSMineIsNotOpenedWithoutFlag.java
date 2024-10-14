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
        getGameObjectStateAutomaton().setCurrentState(getGameObjectStateAutomaton().mineIsOpened);

        getGameObjectStateAutomaton()
                .getGameObject()
                .getGameObjectsPlacementNotVerified()
                .getGameMatch()
                .sendGameEventToAllViews(
                        new GameEventOneTileMinesweeperOpenMine(
                                getGameObjectStateAutomaton().getXyCoordinate()
                        )
                );
        getGameObjectStateAutomaton()
                .getGameObject()
                .getGameObjectsPlacementNotVerified()
                .getGameMatch()
                .sendGameEventToAllViews(new GameEventGameOver(DEFEAT));

        throw new GameOverException();
    }

    @Override
    public void inverseFlag() {
        getGameObjectStateAutomaton().setCurrentState(getGameObjectStateAutomaton().mineIsNotOpenedWithFlag);

        getGameObjectStateAutomaton()
                .getGameObject()
                .getGameObjectsPlacementNotVerified()
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
