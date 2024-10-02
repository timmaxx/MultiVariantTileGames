package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.game.minesweeper.model.gameevent.GameEventOneTileMinesweeperChangeFlag;

//  ToDo:   Разложить класс TileOfMinesweeper на несколько и в т.ч. перенести сюда часть его функционала.
//  ToDo:   После полного отказа от класса TileOfMinesweeper, удалить его.
public class MGONoMineIsNotOpenedWithFlag extends MGONoMine {
    public MGONoMineIsNotOpenedWithFlag(MinesweeperGameObjectStateAutomaton minesweeperGameObjectStateAutomaton) {
        super(minesweeperGameObjectStateAutomaton);
    }

    @Override
    public void open() {
        //  Ничего
    }

    @Override
    public void inverseFlag() {
        getOneTileGameObjectStateAutomaton().setCurrentState(getOneTileGameObjectStateAutomaton().noMineIsNotOpenedWithoutFlag);

        getOneTileGameObjectStateAutomaton()
                .getOneTileGameObject()
                .getOneTileGameObjectsPlacement()
                .getGameMatch()
                .sendGameEventToAllViews(
                        new GameEventOneTileMinesweeperChangeFlag(
                                getOneTileGameObjectStateAutomaton().getXyCoordinate().getX(),
                                getOneTileGameObjectStateAutomaton().getXyCoordinate().getY(),
                                false
                        )
                )
        ;
    }
}
