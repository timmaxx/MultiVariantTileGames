package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.tile.Direction;
import timmax.tilegame.basemodel.tile.Tile;

//  ToDo:   Изменить структуру наследования классов для одноплиточных игровых объектов. Сделать базовым класс
//              timmax.tilegame.basemodel.gameobject.OneTileGameObject
//          Сейчас-же в корне стоит класс
//              timmax.tilegame.basemodel.tile.Tile
//  ToDo:   После отказа от класса Tile в этой модели и в других моделях - удалить Tile.
public abstract class CollisionMovableObject extends CollisionObject implements Movable {
    public CollisionMovableObject(int x, int y) {
        super(x, y);
    }

    public void move(Direction direction) {
        Tile tile = add(direction);
        x = tile.getX();
        y = tile.getY();
    }
}
