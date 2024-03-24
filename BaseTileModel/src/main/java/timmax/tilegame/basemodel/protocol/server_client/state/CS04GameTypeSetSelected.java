package timmax.tilegame.basemodel.protocol.server_client.state;

import timmax.commons.state.AState;
import timmax.commons.state.PairDestStateAndCanSwitchWithoutParams;
import timmax.commons.state.StateContext;

public class CS04GameTypeSetSelected extends AState {
    public CS04GameTypeSetSelected(StateContext stateContext) {
        super(stateContext, StateDataOf04GameTypeSetSelected.class);

        setOfPairDestStateAndCanSwitchWithoutParams.add(
                new PairDestStateAndCanSwitchWithoutParams(CS05GameTypeSelected.class, false)
        );

        setOfPairDestStateAndCanSwitchWithoutParams.add(
                new PairDestStateAndCanSwitchWithoutParams(CS02ConnectNonIdent.class, true)
        );
        setOfPairDestStateAndCanSwitchWithoutParams.add(
                new PairDestStateAndCanSwitchWithoutParams(CS03ConnectAuthorized.class, true)
        );
    }

    @Override
    public String toString() {
        return "CS04GameTypeSetSelected{}";
    }
}
