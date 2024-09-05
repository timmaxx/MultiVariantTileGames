package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.OneTileGameObject;

//  ToDo:   Изменить структуру наследования классов для одноплиточных игровых объектов. Сделать базовым класс
//              timmax.tilegame.basemodel.gameobject.OneTileGameObject
//          А для этой модели базовым должен стать этот класс.
//          Сейчас-же в корне стоит класс
//              timmax.tilegame.basemodel.tile.Tile
//  ToDo:   После отказа от класса Tile в этой модели и в других моделях - удалить Tile.

//  ToDo:   Разложить класс TileOfMinesweeper на несколько и в т.ч. перенести сюда часть его функционала.
public class SokobanGameObject extends OneTileGameObject {
    public SokobanGameObject(String id) {
        super(id);
    }
}
