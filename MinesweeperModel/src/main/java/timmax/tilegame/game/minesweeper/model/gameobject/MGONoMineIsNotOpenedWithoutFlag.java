package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.game.minesweeper.model.gameevent.GameEventOneTileMinesweeperChangeFlag;
import timmax.tilegame.game.minesweeper.model.gameevent.GameEventOneTileMinesweeperOpenNoMine;

//  ToDo:   Разложить класс TileOfMinesweeper на несколько и в т.ч. перенести сюда часть его функционала.
//  ToDo:   После полного отказа от класса TileOfMinesweeper, удалить его.
public class MGONoMineIsNotOpenedWithoutFlag extends MGONoMine {
    public MGONoMineIsNotOpenedWithoutFlag(MinesweeperGameObjectStateAutomaton minesweeperGameObjectStateAutomaton) {
        super(minesweeperGameObjectStateAutomaton);
    }

    @Override
    public void open() {
        getOneTileGameObjectStateAutomaton().setCurrentState(getOneTileGameObjectStateAutomaton().noMineIsOpened);

        getOneTileGameObjectStateAutomaton().initNeighbourSet();

        getOneTileGameObjectStateAutomaton()
                .getOneTileGameObject()
                .getOneTileGameObjectsPlacement()
                .getGameMatch()
                .sendGameEventToAllViews(
                        new GameEventOneTileMinesweeperOpenNoMine(
                                getOneTileGameObjectStateAutomaton().getXyCoordinate().getX(),
                                getOneTileGameObjectStateAutomaton().getXyCoordinate().getY(),
                                getOneTileGameObjectStateAutomaton().countOfMinesInNeighbours
                        )
                )
        ;

        if (getOneTileGameObjectStateAutomaton().countOfMinesInNeighbours > 0) {
            return;
        }
        for (MinesweeperGameObjectStateAutomaton neighbour : getOneTileGameObjectStateAutomaton().getNeighbourSet()) {
            if (neighbour.getOneTileGameObjectState() instanceof MGONoMineIsNotOpenedWithoutFlag) {
                //  Если соседняя плитка ещё не была открыта и там нет мины
                //      Откроем соседнюю плитку
                neighbour.open();
            }
        }
    }

    @Override
    public void inverseFlag() {
        getOneTileGameObjectStateAutomaton().setCurrentState(getOneTileGameObjectStateAutomaton().noMineIsNotOpenedWithFlag);

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
