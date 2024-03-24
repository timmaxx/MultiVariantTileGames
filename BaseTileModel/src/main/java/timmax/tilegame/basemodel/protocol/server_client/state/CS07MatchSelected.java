package timmax.tilegame.basemodel.protocol.server_client.state;

import timmax.commons.state.AState;
import timmax.commons.state.PairDestStateAndCanSwitchWithoutParams;
import timmax.commons.state.StateContext;

public class CS07MatchSelected extends AState {
    public CS07MatchSelected(StateContext stateContext) {
        super(stateContext, StateDataOf07MatchSelected.class);

        setOfPairDestStateAndCanSwitchWithoutParams.add(
                new PairDestStateAndCanSwitchWithoutParams(CS08GameIsPlaying.class, false)
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
        setOfPairDestStateAndCanSwitchWithoutParams.add(
                new PairDestStateAndCanSwitchWithoutParams(CS05GameTypeSelected.class, true)
        );
        setOfPairDestStateAndCanSwitchWithoutParams.add(
                new PairDestStateAndCanSwitchWithoutParams(CS06MatchSetSelected.class, true)
        );
    }

    @Override
    public String toString() {
        return "CS07MatchSelected{}";
    }
}
