package timmax.commons.state;

import timmax.commons.classes.Classes;

public abstract class StateContext implements IStateAutomaton {
// StateContext implements IStateAutomaton {
//
    private AState currentState;

    protected final void changeState(AState newState) {
        if (currentState != null) {
            checkPosibleToChangeState(newState);
            currentState.doOnExit();
        }
        currentState = newState;
        newState.doOnEnter();
    }

    private void checkPosibleToChangeState(AState newState) {
        for (Class<? extends IState> clazz : currentState.setOfDestState) {
            if (Classes.isInstanceOf(newState, clazz)) {
                return;
            }
        }
        throw new RuntimeException("You cannot change state from '" + currentState + "' to '" + newState + "'!");
    }

    @Override
    public IState getCurrentState() {
        return currentState;
    }
}
