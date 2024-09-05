package timmax.tilegame.game.minesweeper.model.gameobject;

//  ToDo:   Разложить класс TileOfMinesweeper на несколько и в т.ч. перенести сюда часть его функционала.
//  ToDo:   После полного отказа от класса TileOfMinesweeper, удалить его.
public class UnOpenedTile extends MinesweeperGameObject {
    public UnOpenedTile(String id) {
        super(id);
    }
}
