package timmax.tilegame.basemodel.gameobject;

public class GameObjectStateAutomaton implements GameObjectState {
    private final GameObject gameObject;
    private AbstractGameObjectState abstractGameObjectState;

    public GameObjectStateAutomaton(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public GameObjectStateAutomaton(GameObject gameObject, AbstractGameObjectState abstractGameObjectState) {
        this(gameObject);
        setCurrentState(abstractGameObjectState);
    }

    public XYCoordinate getXyCoordinate() {
        return gameObject.getXyCoordinate();
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    protected void setCurrentState(AbstractGameObjectState abstractGameObjectState) {
        this.abstractGameObjectState = abstractGameObjectState;
    }

    public AbstractGameObjectState getGameObjectState() {
        return abstractGameObjectState;
    }

    @Override
    public String toString() {
        return "GameObjectStateAutomaton{" +
                "gameObject=" + gameObject +
                ", abstractGameObjectState=" + abstractGameObjectState +
                '}';
    }
}
