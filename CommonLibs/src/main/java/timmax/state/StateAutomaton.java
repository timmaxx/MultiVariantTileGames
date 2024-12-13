package timmax.state;

import timmax.common.Classes;

import java.util.HashSet;
import java.util.Set;

public abstract class StateAutomaton {
    protected final Set<AllowedStateToStateTransition> allowedStateToStateTransitionSet = new HashSet<>();

    protected State currentState;

    public State getCurrentState() {
        return currentState;
    }

    protected void setCurrentState(State targetState) {
        if (Classes.isInstanceOf(targetState, currentState.getClass()) ||
                Classes.isInstanceOf(currentState, targetState.getClass())
        ) {
            return;
        }

        boolean success = false;
        for (AllowedStateToStateTransition allowedStateToStateTransition : allowedStateToStateTransitionSet) {
/*
            if (stateToState.getState1().equals(currentState) &&
                    stateToState.getState2().equals(targetState))
*/
            if (Classes.isInstanceOf(allowedStateToStateTransition.getState1(), currentState.getClass())
                    &&
                    Classes.isInstanceOf(allowedStateToStateTransition.getState2(), targetState.getClass()))
            {
                success = true;
                break;
            }
        }

        if (!success) {
            throw new WrongChangeStateException(currentState, targetState);
        }

        currentState.doBeforeTurnOff();
        currentState = targetState;
        currentState.doAfterTurnOn();
    }
}
