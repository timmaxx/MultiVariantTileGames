package timmax.tilegame.basemodel.gameobject;

//  ToDo:   Изменить структуру наследования классов для одноплиточных игровых объектов. Сделать базовым класс
//              timmax.tilegame.basemodel.gameobject.OneTileGameObject
//          Сейчас-же в корне стоит класс
//              timmax.tilegame.basemodel.tile.Tile
//  ToDo:   После отказа от класса Tile в этой модели и в других моделях - удалить Tile.

//  Одноплиточный игровой объект
public abstract class OneTileGameObject {
    //  Идентификатор объекта для отличия двух и более объектов одного типа.
    //  Например, для Шахмат:
    //      У каждой из сторон может быть по нескольку пешек, тогда здесь можно было-бы написать:
    //      - начальное поле при игре со стандартного начала (a2).
    //      - произвольный номер при решении какой-то позиции.
    private final String id;

    public OneTileGameObject(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "OneTileGameObject{" +
                "id='" + id + '\'' +
                '}';
    }
}
