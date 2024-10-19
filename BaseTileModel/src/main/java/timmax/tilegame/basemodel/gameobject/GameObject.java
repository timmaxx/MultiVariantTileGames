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
    protected final GameObjectsPlacementAbstract gameObjectsPlacementAbstract;

    protected XYCoordinate xyCoordinate;

    public GameObject(String id, GameObjectsPlacementAbstract gameObjectsPlacementAbstract) {
        this.id = id;
        this.gameObjectsPlacementAbstract = gameObjectsPlacementAbstract;
    }

    public GameObject(
            String id,
            GameObjectsPlacementAbstract gameObjectsPlacementAbstract,
            XYCoordinate xyCoordinate) {
        this(id, gameObjectsPlacementAbstract);
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
        if (!gameObjectsPlacementAbstract.getWidthHeightSizes().mayBeRecalc()) {
            gameObjectsPlacementAbstract.getWidthHeightSizes().validateXYCoordinate(xyCoordinate);
        }
        this.xyCoordinate = xyCoordinate;
    }

    public GameObjectsPlacementAbstract getGameObjectsPlacementAbstract() {
        return gameObjectsPlacementAbstract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameObject that = (GameObject) o;

        if (!gameObjectsPlacementAbstract.equals(that.gameObjectsPlacementAbstract)) return false;
        return xyCoordinate.equals(that.xyCoordinate);
    }

    @Override
    public int hashCode() {
        int result = gameObjectsPlacementAbstract.hashCode();
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
