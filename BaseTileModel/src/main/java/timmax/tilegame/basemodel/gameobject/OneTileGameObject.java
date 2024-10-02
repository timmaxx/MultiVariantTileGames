package timmax.tilegame.basemodel.gameobject;

//  ToDo:   Изменить структуру наследования классов для одноплиточных игровых объектов. Сделать базовым класс
//              timmax.tilegame.basemodel.gameobject.OneTileGameObject
//          Сейчас-же в корне стоит класс
//              timmax.tilegame.basemodel.tile.Tile
//  ToDo:   После отказа от класса Tile в этой модели и в других моделях - удалить Tile.

//  Одноплиточный игровой объект
public class OneTileGameObject {
    //  Идентификатор объекта для отличия двух и более объектов одного типа.
    //  Например, для Шахмат:
    //      У каждой из сторон может быть по нескольку пешек, тогда здесь можно было-бы написать:
    //      - координату начального поля.
    private final String id;

    //  Расстановка, которой принадлежит объект.
    protected final OneTileGameObjectsPlacement oneTileGameObjectsPlacement;

    protected XYCoordinate xyCoordinate;


    public OneTileGameObject(String id, OneTileGameObjectsPlacement oneTileGameObjectsPlacement) {
        this.id = id;
        this.oneTileGameObjectsPlacement = oneTileGameObjectsPlacement;
    }

    public OneTileGameObject(String id, OneTileGameObjectsPlacement oneTileGameObjectsPlacement, XYCoordinate xyCoordinate) {
        this(id, oneTileGameObjectsPlacement);
        setXyCoordinate(xyCoordinate);
    }

    public String getId() {
        return id;
    }

    public XYCoordinate getXyCoordinate() {
        return xyCoordinate;
    }

    protected void setXyCoordinate(XYCoordinate xyCoordinate) {
        if (xyCoordinate == null) {
            throw new NullPointerException("xyCoordinate must be not null.");
        }
        //  ToDo:   Проверить координаты на допустимость (минимум и максимум)
        oneTileGameObjectsPlacement.getWidthHeightSizes().validateXYCoordinate(xyCoordinate);
        this.xyCoordinate = xyCoordinate;
    }

    public OneTileGameObjectsPlacement getOneTileGameObjectsPlacement() {
        return oneTileGameObjectsPlacement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OneTileGameObject that = (OneTileGameObject) o;

        if (!oneTileGameObjectsPlacement.equals(that.oneTileGameObjectsPlacement)) return false;
        return xyCoordinate.equals(that.xyCoordinate);
    }

    @Override
    public int hashCode() {
        int result = oneTileGameObjectsPlacement.hashCode();
        result = 31 * result + xyCoordinate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OneTileGameObject{" +
                "id='" + id + '\'' +
//              ", oneTileGameObjectsPlacement=" + oneTileGameObjectsPlacement +
                ", xyCoordinate=" + xyCoordinate +
                '}';
    }
}
