package timmax.tilegame.basemodel.gameobject;

//  Одноплиточный игровой объект.
//  Ранее назывался OneTileGameObject.
//  Предполагалость, что потом будет многоплиточный игровой объект (типа падающей фигурки у Тетриса).
//  Но такого ещё и нет, но, а если он и появится, то и пусть он называется, например, MultiTileGameObject.
public class GameObject {
    //  Идентификатор объекта для отличия двух и более объектов одного типа.
    //  Например, для Шахмат:
    //      У каждой из сторон может быть по нескольку пешек, тогда здесь можно было-бы написать:
    //      - координату начального поля.
    private final String id;

    //  Расстановка, которой принадлежит объект.
    protected final GameObjectsPlacementNotVerified gameObjectsPlacementNotVerified;

    protected XYCoordinate xyCoordinate;

    public GameObject(String id, GameObjectsPlacementNotVerified gameObjectsPlacementNotVerified) {
        this.id = id;
        this.gameObjectsPlacementNotVerified = gameObjectsPlacementNotVerified;
    }

    public GameObject(
            String id,
            GameObjectsPlacementNotVerified gameObjectsPlacementNotVerified,
            XYCoordinate xyCoordinate) {
        this(id, gameObjectsPlacementNotVerified);
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
        gameObjectsPlacementNotVerified.getWidthHeightSizes().validateXYCoordinate(xyCoordinate);
        this.xyCoordinate = xyCoordinate;
    }

    public GameObjectsPlacementNotVerified getGameObjectsPlacementNotVerified() {
        return gameObjectsPlacementNotVerified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameObject that = (GameObject) o;

        if (!gameObjectsPlacementNotVerified.equals(that.gameObjectsPlacementNotVerified)) return false;
        return xyCoordinate.equals(that.xyCoordinate);
    }

    @Override
    public int hashCode() {
        int result = gameObjectsPlacementNotVerified.hashCode();
        result = 31 * result + xyCoordinate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GameObject{" +
                "id='" + id + '\'' +
//              ", gameObjectsPlacement=" + gameObjectsPlacement +
                ", xyCoordinate=" + xyCoordinate +
                '}';
    }
}
