package timmax.tilegame.basemodel.gameobject;

public abstract class GameObjectState implements IGameObjectState {
    private final GameObjectStateAutomaton gameObjectStateAutomaton;

    public GameObjectState(GameObjectStateAutomaton gameObjectStateAutomaton) {
        this.gameObjectStateAutomaton = gameObjectStateAutomaton;
    }

    public GameObjectStateAutomaton getGameObjectStateAutomaton() {
        return gameObjectStateAutomaton;
    }
}
