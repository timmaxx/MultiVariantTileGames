package timmax.tilegame.basemodel.gameobject;

public class GameObjectStateAutomaton implements IGameObjectState {
    private final GameObject gameObject;
    private GameObjectState gameObjectState;

    public GameObjectStateAutomaton(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public GameObjectStateAutomaton(GameObject gameObject, GameObjectState gameObjectState) {
        this(gameObject);
        setCurrentState(gameObjectState);
    }

    public XYCoordinate getXyCoordinate() {
        return gameObject.getXyCoordinate();
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setCurrentState(GameObjectState gameObjectState) {
        this.gameObjectState = gameObjectState;
    }

    public GameObjectState getGameObjectState() {
        return gameObjectState;
    }

    @Override
    public String toString() {
        return "GameObjectStateAutomaton{" +
                "gameObject=" + gameObject +
                ", gameObjectState=" + gameObjectState +
                '}';
    }
}
