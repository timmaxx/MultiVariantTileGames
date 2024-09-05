package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.gameobject.OneTileGameObject;

//  ToDo:   Разложить класс TileOfMinesweeper на несколько и в т.ч. перенести сюда часть его функционала.
//          А для этой модели базовым должен стать этот класс.
//  ToDo:   После полного отказа от класса TileOfMinesweeper, удалить его.
public abstract class MinesweeperGameObject extends OneTileGameObject {
    public MinesweeperGameObject(String id) {
        super(id);
    }
}
