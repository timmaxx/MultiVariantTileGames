package timmax.tilegame.basemodel.protocol.server_client;

public class StateToState {
    private final AbstractClientState state1;
    private final AbstractClientState state2;

    public StateToState(AbstractClientState state1, AbstractClientState state2) {
        this.state1 = state1;
        this.state2 = state2;
    }

    public AbstractClientState getState1() {
        return state1;
    }

    public AbstractClientState getState2() {
        return state2;
    }
}
