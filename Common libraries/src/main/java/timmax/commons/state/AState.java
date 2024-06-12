package timmax.commons.state;

import java.util.HashSet;
import java.util.Set;

public abstract class AState implements IState {
    private final StateContext stateContext;

    protected final Set<Class<? extends IState>> setOfDestState;

    public AState(StateContext stateContext) {
        this.stateContext = stateContext;
        this.setOfDestState = new HashSet<>();
    }

    protected final void changeState(AState state) {
        stateContext.changeState(state);
    }

    protected void doOnEnter() {
    }

    protected void doOnExit() {
    }

    // Implemented methods of interface IState
    @Override
    public IStateAutomaton getStateAutomaton() {
        return stateContext;
    }

    // class Object
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
