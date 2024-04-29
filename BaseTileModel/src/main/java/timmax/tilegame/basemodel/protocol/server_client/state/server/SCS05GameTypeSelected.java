package timmax.tilegame.basemodel.protocol.server_client.state.server;

import timmax.commons.state.AState;
import timmax.commons.state.StateContext;
import timmax.commons.state.StateData;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.state.CS05GameTypeSelected;
import timmax.tilegame.transport.TransportOfServer;

public class SCS05GameTypeSelected<ClientId> extends CS05GameTypeSelected {
    private final ClientId clientId;
    private final TransportOfServer<ClientId> transportOfServer;

    public SCS05GameTypeSelected(StateContext stateContext, ClientId clientId, TransportOfServer<ClientId> transportOfServer) {
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
        } else {
            return;
        }
        super.changeState(aState);
    }

    @Override
    public void changeState(AState aState, StateData stateData) {
        super.checkPosibleToChangeState(aState, true);
        if (aState instanceof SCS06MatchSetSelected<?>) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer61SetGameMatch());
            super.changeState(aState, stateData);
        }
    }
}
