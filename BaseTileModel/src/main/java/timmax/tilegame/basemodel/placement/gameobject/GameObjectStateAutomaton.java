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
        //  ToDo:   дополнить структуру перехода между состояниями методом "выполнить при входе в состояние".
        //          Может быть вариант:
        //          - как до "this.baseGameObjectState = baseGameObjectState;"
        //          - так и после "this.baseGameObjectState = baseGameObjectState;"
        //  ToDo:   Возможно в структуре перехода между состояниями понадобится метод "выполнить при выходе из состояния".
        //          И также может быть пара вариантов.
        this.baseGameObjectState = baseGameObjectState;
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
