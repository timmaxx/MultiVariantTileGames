package timmax.tilegame.game.minesweeper.model.gameobject;

//  ToDo:   Разложить класс TileOfMinesweeper на несколько и в т.ч. перенести сюда часть его функционала.
//  ToDo:   После полного отказа от класса TileOfMinesweeper, удалить его.
public abstract class MGONoMine extends MinesweeperGameObjectState {

    public MGONoMine(MinesweeperGameObjectStateAutomaton minesweeperGameObjectStateAutomaton) {
        super(minesweeperGameObjectStateAutomaton);
    }

    @Override
    public int getOneOrZeroMines() {
        return 0;
    }
}
