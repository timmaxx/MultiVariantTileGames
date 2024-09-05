package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.tile.Direction;
import timmax.tilegame.basemodel.tile.Tile;

//  ToDo:   Изменить структуру наследования классов для одноплиточных игровых объектов. Сделать базовым класс
//              timmax.tilegame.basemodel.gameobject.OneTileGameObject
//          Сейчас-же в корне стоит класс
//              timmax.tilegame.basemodel.tile.Tile
//  ToDo:   После отказа от класса Tile в этой модели и в других моделях - удалить Tile.

// For Walls, Boxes and player
abstract public class CollisionObject extends Tile {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(CollisionObject collisionObject, Direction direction) {
        Tile tile = add(direction);
        return tile.getX() == collisionObject.x && tile.getY() == collisionObject.y;
    }

    public boolean isOutOfBoard(Direction direction, int width, int height) {
        Tile tile = add(direction);
        return tile.getX() < 0 || tile.getX() >= width ||
                tile.getY() < 0 || tile.getY() >= height;
    }
}
