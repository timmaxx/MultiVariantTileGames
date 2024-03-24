package timmax.tilegame.basemodel.protocol.server_client.state.server;

import timmax.commons.state.AState;
import timmax.commons.state.StateContext;
import timmax.commons.state.StateData;
import timmax.tilegame.basemodel.protocol.EventOfServer20Logout;
import timmax.tilegame.basemodel.protocol.EventOfServer41SetGameType;
import timmax.tilegame.basemodel.protocol.server_client.state.CS03ConnectAuthorized;
import timmax.tilegame.transport.TransportOfServer;

public class SCS03ConnectAuthorized<ClientId> extends CS03ConnectAuthorized {
    private final ClientId clientId;
    private final TransportOfServer<ClientId> transportOfServer;

    public SCS03ConnectAuthorized(StateContext stateContext, ClientId clientId, TransportOfServer<ClientId> transportOfServer) {
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
        } else {
            return;
        }
        super.changeState(aState);
    }

    @Override
    public void changeState(AState aState, StateData stateData) {
        super.checkPosibleToChangeState(aState, stateData);
        if (aState instanceof SCS04GameTypeSetSelected<?>) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer41SetGameType());
            super.changeState(aState, stateData);
        }
    }
}
