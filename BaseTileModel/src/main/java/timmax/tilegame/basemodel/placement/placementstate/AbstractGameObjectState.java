package timmax.tilegame.basemodel.placement.placementstate;

import timmax.tilegame.basemodel.placement.gameobject.GameObjectState;
import timmax.tilegame.basemodel.placement.gameobject.GameObjectStateAutomaton;

public abstract class AbstractGameObjectState implements GameObjectState {
    private final GameObjectStateAutomaton gameObjectStateAutomaton;

    public AbstractGameObjectState(GameObjectStateAutomaton gameObjectStateAutomaton) {
        this.gameObjectStateAutomaton = gameObjectStateAutomaton;
    }

    public GameObjectStateAutomaton getGameObjectStateAutomaton() {
        return gameObjectStateAutomaton;
    }
}
