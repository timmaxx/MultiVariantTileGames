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
/*
        //  Этот код мог-бы пригодититься если-бы не было ситуаций с переходом в тоже самое состояние, что и текущее.
        if (Classes.isInstanceOf(targetState, currentState.getClass()) ||
                Classes.isInstanceOf(currentState, targetState.getClass())
        ) {
            return;
        }
*/
        boolean success = false;
        for (AllowedStateToStateTransition allowedStateToStateTransition : allowedStateToStateTransitionSet) {
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
