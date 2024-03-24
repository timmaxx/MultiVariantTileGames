package timmax.tilegame.basemodel.protocol.server_client.state.server;

import timmax.commons.state.AState;
import timmax.commons.state.StateContext;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.state.CS07MatchSelected;
import timmax.tilegame.transport.TransportOfServer;

public class SCS07MatchSelected<ClientId> extends CS07MatchSelected {
    private final ClientId clientId;
    private final TransportOfServer<ClientId> transportOfServer;

    public SCS07MatchSelected(StateContext stateContext, ClientId clientId, TransportOfServer<ClientId> transportOfServer) {
        super(stateContext);
        this.clientId = clientId;
        this.transportOfServer = transportOfServer;
    }

    //
    @Override
    public void changeState(AState aState) {
        super.checkPosibleToChangeState(aState);
        if (aState instanceof SCS02ConnectNonIdent<?>) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer20Logout());
        } else if (aState instanceof SCS03ConnectAuthorized<?>) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer30ForgetGameTypeSet());
        } else if (aState instanceof SCS04GameTypeSetSelected<?>) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer40ForgetGameType());
        } else if (aState instanceof SCS05GameTypeSelected<?>) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer50ForgetGameMatchSet());
        } else if (aState instanceof SCS06MatchSetSelected<?>) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer60ForgetGameMatch());
        } else {
            return;
        }
        super.changeState(aState);
    }
/*
    @Override
    public void changeState(AState aState, StateData stateData) {
        super.checkPosibleToChangeState(aState, stateData);
        if (aState instanceof SCS08GameIsPlaying<?>) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer81); // !!!
            super.changeState(aState, stateData);
        }
    }
*/
}
