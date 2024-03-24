package timmax.tilegame.basemodel.protocol.server_client.state;

import timmax.commons.state.AState;
import timmax.commons.state.PairDestStateAndCanSwitchWithoutParams;
import timmax.commons.state.StateContext;

public class CS02ConnectNonIdent extends AState {
    public CS02ConnectNonIdent(StateContext stateContext) {
        super(stateContext, StateDataOf02ConnectNonIdent.class);

        setOfPairDestStateAndCanSwitchWithoutParams.add(
                new PairDestStateAndCanSwitchWithoutParams(CS03ConnectAuthorized.class, false)
        );
    }

    @Override
    public String toString() {
        return "CS02ConnectNonIdent{}";
    }
}
