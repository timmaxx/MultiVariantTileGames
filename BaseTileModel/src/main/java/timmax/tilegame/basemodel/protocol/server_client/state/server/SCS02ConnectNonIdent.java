package timmax.tilegame.basemodel.protocol.server_client.state.server;

import timmax.commons.state.AState;
import timmax.commons.state.StateContext;
import timmax.commons.state.StateData;
import timmax.tilegame.basemodel.protocol.EventOfServer31GetGameTypeSet;
import timmax.tilegame.basemodel.protocol.server_client.state.CS02ConnectNonIdent;
import timmax.tilegame.transport.TransportOfServer;

public class SCS02ConnectNonIdent<ClientId> extends CS02ConnectNonIdent {
    private final ClientId clientId;
    private final TransportOfServer<ClientId> transportOfServer;

    public SCS02ConnectNonIdent(StateContext stateContext, ClientId clientId, TransportOfServer<ClientId> transportOfServer) {
        super(stateContext);
        this.clientId = clientId;
        this.transportOfServer = transportOfServer;
    }

    //
/*
    @Override
    public void changeState(AState aState) {
        super.checkPosibleToChangeState(aState);
        if (aState instanceof SCS01) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer10);
        } else {
            return;
        }
        super.changeState(aState);
    }
*/
    @Override
    public void changeState(AState aState, StateData stateData) {
        super.checkPosibleToChangeState(aState, stateData);
        if (aState instanceof SCS03ConnectAuthorized<?>) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer31GetGameTypeSet());
            super.changeState(aState, stateData);
        }
    }
}
