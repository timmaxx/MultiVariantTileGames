package timmax.tilegame.basemodel.protocol.server_client.state;

import timmax.commons.state.AState;
import timmax.commons.state.PairDestStateAndCanSwitchWithoutParams;
import timmax.commons.state.StateContext;

public class CS05GameTypeSelected extends AState {
    public CS05GameTypeSelected(StateContext stateContext) {
        super(stateContext, StateDataOf05GameTypeSelected.class);

        setOfPairDestStateAndCanSwitchWithoutParams.add(
                new PairDestStateAndCanSwitchWithoutParams(CS06MatchSetSelected.class, false)
        );

        setOfPairDestStateAndCanSwitchWithoutParams.add(
                new PairDestStateAndCanSwitchWithoutParams(CS02ConnectNonIdent.class, true)
        );
        setOfPairDestStateAndCanSwitchWithoutParams.add(
                new PairDestStateAndCanSwitchWithoutParams(CS03ConnectAuthorized.class, true)
        );
        setOfPairDestStateAndCanSwitchWithoutParams.add(
                new PairDestStateAndCanSwitchWithoutParams(CS04GameTypeSetSelected.class, true)
        );
    }

    @Override
    public String toString() {
        return "CS05GameTypeSelected{}";
    }
}
