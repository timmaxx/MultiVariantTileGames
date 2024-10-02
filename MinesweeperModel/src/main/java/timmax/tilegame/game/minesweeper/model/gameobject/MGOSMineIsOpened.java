package timmax.tilegame.game.minesweeper.model.gameobject;

//  ToDo:   Разложить класс TileOfMinesweeper на несколько и в т.ч. перенести сюда часть его функционала.
//  ToDo:   После полного отказа от класса TileOfMinesweeper, удалить его.
public class MGOSMineIsOpened extends MGOSMine {
    public MGOSMineIsOpened(MinesweeperGameObjectStateAutomaton minesweeperGameObjectStateAutomaton) {
        super(minesweeperGameObjectStateAutomaton);
    }

    @Override
    public void open() {
        //  Ничего
    }

    @Override
    public void inverseFlag() {
        //  Ничего
    }
}
