package timmax.tilegame.minesweeper.model.placement.gameobject;

import timmax.tilegame.basemodel.placement.placementstate.AbstractGameObjectState;

public abstract class AbstractMGOState extends AbstractGameObjectState implements MGOState {

    public AbstractMGOState(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public MGOStateAutomaton getGameObjectStateAutomaton() {
        return (MGOStateAutomaton) super.getGameObjectStateAutomaton();
    }
}
