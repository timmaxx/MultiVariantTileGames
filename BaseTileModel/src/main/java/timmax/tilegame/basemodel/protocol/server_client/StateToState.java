package timmax.tilegame.basemodel.protocol.server_client;

public class StateToState<GameMatchX extends IGameMatchX> {
    private final AbstractClientState<GameMatchX> state1;
    private final AbstractClientState<GameMatchX> state2;

    public StateToState(AbstractClientState<GameMatchX> state1, AbstractClientState<GameMatchX> state2) {
        this.state1 = state1;
        this.state2 = state2;
    }

    public AbstractClientState<GameMatchX> getState1() {
        return state1;
    }

    public AbstractClientState<GameMatchX> getState2() {
        return state2;
    }
}
