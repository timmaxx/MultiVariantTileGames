package timmax.tilegame.basemodel.placement.gameobject;

import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;

//  Автомат состояния игрового объекта.
public abstract class GameObjectStateAutomaton {
    private final GameObject gameObject;
    private BaseGameObjectState baseGameObjectState;

    public GameObjectStateAutomaton(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public GameObjectStateAutomaton(GameObject gameObject, BaseGameObjectState baseGameObjectState) {
        this(gameObject);
        setCurrentState(baseGameObjectState);
    }

    public XYCoordinate getXyCoordinate() {
        return gameObject.getXyCoordinate();
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    protected void setCurrentState(BaseGameObjectState baseGameObjectState) {
        //  MayBeToDo:
        //          Возможно при переходе между состояниями понадобится метод "выполнить при выходе из состояния".
        //          Если он понадобится, то в варианте до "this.baseGameObjectState = baseGameObjectState;"
        this.baseGameObjectState = baseGameObjectState;
        //  здесь реализован вариант "выполнить при входе в состояние" после "this.baseGameObjectState = baseGameObjectState;"
        this.baseGameObjectState.doWhenTurnOn();
    }

    public BaseGameObjectState getGameObjectState() {
        return baseGameObjectState;
    }

    @Override
    public String toString() {
        return "GameObjectStateAutomaton{" +
                "gameObject=" + gameObject +
                ", baseGameObjectState=" + baseGameObjectState +
                '}';
    }
}
