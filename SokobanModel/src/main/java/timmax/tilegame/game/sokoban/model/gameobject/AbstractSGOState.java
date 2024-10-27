package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.AbstractGameObjectState;
import timmax.tilegame.basemodel.gameobject.GameObjectStateAutomaton;

public class AbstractSGOState extends AbstractGameObjectState implements SGOState {
    public AbstractSGOState(GameObjectStateAutomaton gameObjectStateAutomaton) {
        super(gameObjectStateAutomaton);
    }

    @Override
    public SGOStateAutomaton getGameObjectStateAutomaton() {
        return (SGOStateAutomaton) super.getGameObjectStateAutomaton();
    }
}
