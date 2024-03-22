package timmax.commons.state;

public class StateContext implements State {
    AState currentClientState;

    public StateContext(AState currentClientState) {
        this.currentClientState = currentClientState;
    }

    @Override
    public void changeState(AState aState) {
        currentClientState.changeState(aState);
    }

    @Override
    public void changeState(AState aState, StateData stateData) {
        currentClientState.changeState(aState, stateData);
    }

    @Override
    public StateData getData() {
        return currentClientState.getData();
    }
}
