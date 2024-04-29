package timmax.tilegame.basemodel.protocol.server_client.state.server;

import timmax.commons.state.AState;
import timmax.commons.state.StateContext;
import timmax.commons.state.StateData;
import timmax.tilegame.basemodel.protocol.EventOfServer20Logout;
import timmax.tilegame.basemodel.protocol.EventOfServer30ForgetGameTypeSet;
import timmax.tilegame.basemodel.protocol.EventOfServer51GetGameMatchSet;
import timmax.tilegame.basemodel.protocol.server_client.state.CS04GameTypeSetSelected;
import timmax.tilegame.transport.TransportOfServer;

public class SCS04GameTypeSetSelected<ClientId> extends CS04GameTypeSetSelected {
    private final ClientId clientId;
    private final TransportOfServer<ClientId> transportOfServer;

    public SCS04GameTypeSetSelected(StateContext stateContext, ClientId clientId, TransportOfServer<ClientId> transportOfServer) {
        super(stateContext);
        this.clientId = clientId;
        this.transportOfServer = transportOfServer;
    }

    //
    @Override
    public void changeState(AState aState) {
        super.checkPosibleToChangeState(aState, false);
        if (aState instanceof SCS02ConnectNonIdent) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer20Logout());
        } else if (aState instanceof SCS03ConnectAuthorized<?>) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer30ForgetGameTypeSet());
        } else {
            return;
        }
        super.changeState(aState);
    }

    @Override
    public void changeState(AState aState, StateData stateData) {
        super.checkPosibleToChangeState(aState, true);
        if (aState instanceof SCS05GameTypeSelected<?>) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer51GetGameMatchSet());
            super.changeState(aState, stateData);
        }
    }
}
