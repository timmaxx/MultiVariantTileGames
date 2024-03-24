package timmax.tilegame.basemodel.protocol.server_client.state;

import timmax.commons.state.AState;
import timmax.commons.state.PairDestStateAndCanSwitchWithoutParams;
import timmax.commons.state.StateContext;

public class CS08GameIsPlaying extends AState {
    public CS08GameIsPlaying(StateContext stateContext) {
        super(stateContext, StateDataOfCS08GameIsPlaying.class);

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
        setOfPairDestStateAndCanSwitchWithoutParams.add(
                new PairDestStateAndCanSwitchWithoutParams(CS07MatchSelected.class, true)
        );
    }

    @Override
    public String toString() {
        return "CS08GameIsPlaying{}";
    }
}
