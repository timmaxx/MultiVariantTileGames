package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObjectState;
import timmax.tilegame.basemodel.gameobject.GameObjectStateAutomaton;

public class SGOState extends GameObjectState implements ISGOState {
    public SGOState(GameObjectStateAutomaton gameObjectStateAutomaton) {
        super(gameObjectStateAutomaton);
    }

    @Override
    public SGOStateAutomaton getGameObjectStateAutomaton() {
        return (SGOStateAutomaton) super.getGameObjectStateAutomaton();
    }
}
