package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.gameobject.AbstractGameObjectState;

public abstract class AbstractMGOState extends AbstractGameObjectState implements MGOState {

    public AbstractMGOState(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public MGOStateAutomaton getGameObjectStateAutomaton() {
        return (MGOStateAutomaton) super.getGameObjectStateAutomaton();
    }
}
