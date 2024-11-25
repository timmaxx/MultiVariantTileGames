package timmax.tilegame.basemodel.placement.gameobject;

//  Базовое состояние игрового объекта - родитель для других состояний игрового объекта.
public abstract class BaseGameObjectState {
    private final GameObjectStateAutomaton gameObjectStateAutomaton;

    public BaseGameObjectState(GameObjectStateAutomaton gameObjectStateAutomaton) {
        this.gameObjectStateAutomaton = gameObjectStateAutomaton;
    }

    public GameObjectStateAutomaton getGameObjectStateAutomaton() {
        return gameObjectStateAutomaton;
    }
}
