package timmax.tilegame.minesweeper.model.placement.gameobject;

import timmax.tilegame.basemodel.exception.GameOverException;
import timmax.tilegame.basemodel.gameevent.GameEventGameOver;
import timmax.tilegame.minesweeper.model.gameevent.GameEventOneTileMinesweeperChangeFlag;
import timmax.tilegame.minesweeper.model.gameevent.GameEventOneTileMinesweeperOpenMine;

import static timmax.tilegame.basemodel.GameMatchStatus.DEFEAT;

public class MGOSMineIsNotOpenedWithoutFlag extends MGOSMine {
    public MGOSMineIsNotOpenedWithoutFlag(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public void open() {
        getGameObjectStateAutomaton().setCurrentStateMineIsOpened();

        //  ToDo:   Код ниже в этом методе лучше перенести в метод "выполнить при входе в состояние".

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

        //  ToDo:   Код ниже в этом методе лучше перенести в метод "выполнить при входе в состояние".

        //  Код ниже идентичен коду в MGOSNoMineIsNotOpenedWithoutFlag :: void inverseFlag()
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
