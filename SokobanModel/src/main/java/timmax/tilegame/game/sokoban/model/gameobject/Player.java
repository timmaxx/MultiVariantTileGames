package timmax.tilegame.game.sokoban.model.gameobject;

//  ToDo:   Изменить структуру наследования классов для одноплиточных игровых объектов. Сделать базовым класс
//              timmax.tilegame.basemodel.gameobject.OneTileGameObject
//          Сейчас-же в корне стоит класс
//              timmax.tilegame.basemodel.tile.Tile
//  ToDo:   После отказа от класса Tile в этой модели и в других моделях - удалить Tile.
public class Player extends CollisionMovableObject {
    public Player(int x, int y) {
        super(x, y);
    }
}
