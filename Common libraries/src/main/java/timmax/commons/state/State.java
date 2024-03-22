package timmax.commons.state;

public interface State {
    void changeState(AState aState);
    void changeState(AState aState, StateData stateData);
    StateData getData();
}
