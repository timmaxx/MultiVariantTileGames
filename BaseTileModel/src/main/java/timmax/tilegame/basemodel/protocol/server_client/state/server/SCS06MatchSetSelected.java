package timmax.tilegame.basemodel.protocol.server_client.state.server;

import timmax.commons.state.AState;
import timmax.commons.state.StateContext;
import timmax.commons.state.StateData;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.state.CS06MatchSetSelected;
import timmax.tilegame.transport.TransportOfServer;

public class SCS06MatchSetSelected<ClientId> extends CS06MatchSetSelected {
    private final ClientId clientId;
    private final TransportOfServer<ClientId> transportOfServer;

    public SCS06MatchSetSelected(StateContext stateContext, ClientId clientId, TransportOfServer<ClientId> transportOfServer) {
        super(stateContext);
        this.clientId = clientId;
        this.transportOfServer = transportOfServer;
    }

    //
    @Override
    public void changeState(AState aState) {
        super.checkPosibleToChangeState(aState, false);
        if (aState instanceof SCS02ConnectNonIdent<?>) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer20Logout());
        } else if (aState instanceof SCS03ConnectAuthorized<?>) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer30ForgetGameTypeSet());
        } else if (aState instanceof SCS04GameTypeSetSelected<?>) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer40ForgetGameType());
        } else if (aState instanceof SCS05GameTypeSelected<?>) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer50ForgetGameMatchSet());
        } else {
            return;
        }
        super.changeState(aState);
    }

    @Override
    public void changeState(AState aState, StateData stateData) {
        super.checkPosibleToChangeState(aState, true);
        if (aState instanceof SCS07MatchSelected<?>) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer71GameMatchIsPlaying());
            super.changeState(aState, stateData);
        }
    }
}
