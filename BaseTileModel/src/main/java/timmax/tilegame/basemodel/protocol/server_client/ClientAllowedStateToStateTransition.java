package timmax.tilegame.basemodel.protocol.server_client;

import timmax.state.AllowedStateToStateTransition;

public class ClientAllowedStateToStateTransition
        extends AllowedStateToStateTransition {
    public ClientAllowedStateToStateTransition(ClientState state1, ClientState state2) {
        super(state1, state2);
    }

    public ClientState getState1() {
        return (ClientState) state1;
    }

    public ClientState getState2() {
        return (ClientState) state2;
    }
}
