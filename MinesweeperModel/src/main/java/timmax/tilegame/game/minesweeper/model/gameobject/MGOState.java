package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObjectState;

public abstract class MGOState extends GameObjectState implements IMGOState {

    public MGOState(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public MGOStateAutomaton getGameObjectStateAutomaton() {
        return (MGOStateAutomaton) super.getGameObjectStateAutomaton();
    }
}
