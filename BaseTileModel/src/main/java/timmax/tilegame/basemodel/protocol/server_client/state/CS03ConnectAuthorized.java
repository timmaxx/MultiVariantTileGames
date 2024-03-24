package timmax.tilegame.basemodel.protocol.server_client.state;

import timmax.commons.state.AState;
import timmax.commons.state.PairDestStateAndCanSwitchWithoutParams;
import timmax.commons.state.StateContext;

public class CS03ConnectAuthorized extends AState {
    public CS03ConnectAuthorized(StateContext stateContext) {
        super(stateContext, StateDataOf03ConnectAuthorized.class);

        setOfPairDestStateAndCanSwitchWithoutParams.add(
                new PairDestStateAndCanSwitchWithoutParams(CS04GameTypeSetSelected.class, false)
        );

        setOfPairDestStateAndCanSwitchWithoutParams.add(
                new PairDestStateAndCanSwitchWithoutParams(CS02ConnectNonIdent.class, true)
        );
    }

    @Override
    public String toString() {
        return "CS03ConnectAuthorized{}";
    }
}