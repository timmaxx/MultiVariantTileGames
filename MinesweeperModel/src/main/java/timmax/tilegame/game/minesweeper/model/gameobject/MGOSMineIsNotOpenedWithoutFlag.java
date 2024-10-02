package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.exception.GameOverException;
import timmax.tilegame.basemodel.gameevent.GameEventGameOver;
import timmax.tilegame.game.minesweeper.model.gameevent.GameEventOneTileMinesweeperChangeFlag;
import timmax.tilegame.game.minesweeper.model.gameevent.GameEventOneTileMinesweeperOpenMine;

import static timmax.tilegame.basemodel.GameMatchStatus.DEFEAT;

//  ToDo:   Разложить класс TileOfMinesweeper на несколько и в т.ч. перенести сюда часть его функционала.
//  ToDo:   После полного отказа от класса TileOfMinesweeper, удалить его.
public class MGOSMineIsNotOpenedWithoutFlag extends MGOSMine {
    public MGOSMineIsNotOpenedWithoutFlag(MinesweeperGameObjectStateAutomaton minesweeperGameObjectStateAutomaton) {
        super(minesweeperGameObjectStateAutomaton);
    }

    @Override
    public void open() {
        getOneTileGameObjectStateAutomaton().setCurrentState(getOneTileGameObjectStateAutomaton().mineIsOpened);

        getOneTileGameObjectStateAutomaton()
                .getOneTileGameObject()
                .getOneTileGameObjectsPlacement()
                .getGameMatch()
                .sendGameEventToAllViews(
                        new GameEventOneTileMinesweeperOpenMine(
                                getOneTileGameObjectStateAutomaton().getXyCoordinate().getX(),
                                getOneTileGameObjectStateAutomaton().getXyCoordinate().getY()
                        )
                );
        getOneTileGameObjectStateAutomaton()
                .getOneTileGameObject()
                .getOneTileGameObjectsPlacement()
                .getGameMatch()
                .sendGameEventToAllViews(new GameEventGameOver(DEFEAT));

        throw new GameOverException();
    }

    @Override
    public void inverseFlag() {
        getOneTileGameObjectStateAutomaton().setCurrentState(getOneTileGameObjectStateAutomaton().mineIsNotOpenedWithFlag);

        getOneTileGameObjectStateAutomaton()
                .getOneTileGameObject()
                .getOneTileGameObjectsPlacement()
                .getGameMatch()
                .sendGameEventToAllViews(
                        new GameEventOneTileMinesweeperChangeFlag(
                                getOneTileGameObjectStateAutomaton().getXyCoordinate().getX(),
                                getOneTileGameObjectStateAutomaton().getXyCoordinate().getY(),
                                true
                        )
                )
        ;
    }
}
