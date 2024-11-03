package timmax.tilegame.basemodel.placement.gameobject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timmax.tilegame.basemodel.placement.placementstate.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;

//  Одноплиточный игровой объект.
//  Ранее назывался OneTileGameObject.
//  Предполагалость, что потом будет многоплиточный игровой объект (типа падающей фигурки у Тетриса).
//  Но такого ещё и нет, но, а если он и появится, то и пусть он называется, например, MultiTileGameObject.
public class GameObject {
    protected static final Logger logger = LoggerFactory.getLogger(GameObject.class);

    //  Идентификатор объекта для отличия двух и более объектов одного типа.
    //  Например, для Шахмат:
    //      У каждой из сторон может быть по нескольку пешек, тогда здесь можно было-бы написать:
    //      - координату начального поля.
    private final String id;

    //  Расстановка, которой принадлежит объект.
    protected GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton;

    protected XYCoordinate xyCoordinate;

    public GameObject(String id, GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton) {
        this.id = id;
        this.gameObjectsPlacementStateAutomaton = gameObjectsPlacementStateAutomaton;
    }

    public GameObject(
            String id,
            GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton,
            XYCoordinate xyCoordinate) {
        this(id, gameObjectsPlacementStateAutomaton);
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
        if (!gameObjectsPlacementStateAutomaton.getWidthHeightSizes().mayBeRecalc()) {
            gameObjectsPlacementStateAutomaton.getWidthHeightSizes().validateXYCoordinate(xyCoordinate);
        }
        this.xyCoordinate = xyCoordinate;
    }

    public GameObjectsPlacementStateAutomaton getGameObjectsPlacement() {
        return gameObjectsPlacementStateAutomaton;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameObject that = (GameObject) o;

        if (!gameObjectsPlacementStateAutomaton.equals(that.gameObjectsPlacementStateAutomaton)) return false;
        return xyCoordinate.equals(that.xyCoordinate);
    }

    @Override
    public int hashCode() {
        int result = gameObjectsPlacementStateAutomaton.hashCode();
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
