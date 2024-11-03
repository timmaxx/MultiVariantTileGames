package timmax.tilegame.sokoban.model.placement.gameobject;

import timmax.tilegame.basemodel.placement.placementstate.AbstractGameObjectState;
import timmax.tilegame.basemodel.placement.gameobject.GameObjectStateAutomaton;

public class AbstractSGOState extends AbstractGameObjectState implements SGOState {
    public AbstractSGOState(GameObjectStateAutomaton gameObjectStateAutomaton) {
        super(gameObjectStateAutomaton);
    }

    @Override
    public SGOStateAutomaton getGameObjectStateAutomaton() {
        return (SGOStateAutomaton) super.getGameObjectStateAutomaton();
    }
}
