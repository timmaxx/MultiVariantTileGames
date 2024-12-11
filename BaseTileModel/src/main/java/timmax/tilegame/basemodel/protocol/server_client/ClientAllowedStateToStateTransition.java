package timmax.tilegame.basemodel.protocol.server_client;

import timmax.state.AllowedStateToStateTransition;

public class ClientAllowedStateToStateTransition<GameMatchX extends IGameMatchX>
        extends AllowedStateToStateTransition {
    public ClientAllowedStateToStateTransition(ClientState<GameMatchX> state1, ClientState<GameMatchX> state2) {
        super(state1, state2);
    }

    public ClientState<GameMatchX> getState1() {
        return (ClientState<GameMatchX>) state1;
    }

    public ClientState<GameMatchX> getState2() {
        return (ClientState<GameMatchX>) state2;
    }
}
