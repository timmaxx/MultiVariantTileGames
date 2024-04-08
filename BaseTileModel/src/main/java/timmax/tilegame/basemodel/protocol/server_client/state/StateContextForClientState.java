package timmax.tilegame.basemodel.protocol.server_client.state;

import timmax.commons.state.AState;
import timmax.commons.state.StateContext;

public class StateContextForClientState extends StateContext {
    protected CS02ConnectNonIdent cs02ConnectNonIdent;
    protected CS03ConnectAuthorized cs03ConnectAuthorized;
    protected CS04GameTypeSetSelected cs04GameTypeSetSelected;
    protected CS05GameTypeSelected cs05GameTypeSelected;
    protected CS06MatchSetSelected cs06MatchSetSelected;
    protected CS07MatchSelected cs07MatchSelected;
    protected CS08GameIsPlaying cs08GameIsPlaying;

    public StateContextForClientState(AState currentClientState) {
        super(currentClientState);
    }
}
