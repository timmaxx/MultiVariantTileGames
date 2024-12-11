package timmax.state;

public class AllowedStateToStateTransition {
    protected final State state1;
    protected final State state2;

    public AllowedStateToStateTransition(State state1, State state2) {
        this.state1 = state1;
        this.state2 = state2;
    }

    public State getState1() {
        return state1;
    }

    public State getState2() {
        return state2;
    }

    @Override
    public String toString() {
        return "StateToState{" +
                "state1=" + state1 +
                ", state2=" + state2 +
                '}';
    }
}
