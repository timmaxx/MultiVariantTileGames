package timmax.tilegame.basemodel.gameobject;

public class OneTileGameObjectStateAutomaton implements IOneTileGameObjectState {
    private final OneTileGameObject oneTileGameObject;
    private OneTileGameObjectState oneTileGameObjectState;

    public OneTileGameObjectStateAutomaton(OneTileGameObject oneTileGameObject) {
        this.oneTileGameObject = oneTileGameObject;
    }

    public OneTileGameObjectStateAutomaton(OneTileGameObject oneTileGameObject, OneTileGameObjectState oneTileGameObjectState) {
        this(oneTileGameObject);
        setCurrentState(oneTileGameObjectState);
    }

    public XYCoordinate getXyCoordinate() {
        return oneTileGameObject.getXyCoordinate();
    }

    public OneTileGameObject getOneTileGameObject() {
        return oneTileGameObject;
    }

    public void setCurrentState(OneTileGameObjectState oneTileGameObjectState) {
        this.oneTileGameObjectState = oneTileGameObjectState;
    }

    public OneTileGameObjectState getOneTileGameObjectState() {
        return oneTileGameObjectState;
    }

    @Override
    public String toString() {
        return "OneTileGameObjectStateAutomaton{" +
                "oneTileGameObject=" + oneTileGameObject +
                ", oneTileGameObjectState=" + oneTileGameObjectState +
                '}';
    }
}
