package timmax.state;

public abstract class State {
    private final StateAutomaton stateAutomaton;

    public State(StateAutomaton clientStateAutomaton) {
        this.stateAutomaton = clientStateAutomaton;
    }

    protected StateAutomaton getBaseStateAutomaton() {
        return stateAutomaton;
    }

    void doBeforeTurnOff() {
    }

    void doAfterTurnOn() {
    }
}
