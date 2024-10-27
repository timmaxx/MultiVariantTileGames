package timmax.tilegame.basemodel.gameobject;

public abstract class AbstractGameObjectState implements GameObjectState {
    private final GameObjectStateAutomaton gameObjectStateAutomaton;

    public AbstractGameObjectState(GameObjectStateAutomaton gameObjectStateAutomaton) {
        this.gameObjectStateAutomaton = gameObjectStateAutomaton;
    }

    public GameObjectStateAutomaton getGameObjectStateAutomaton() {
        return gameObjectStateAutomaton;
    }
}
