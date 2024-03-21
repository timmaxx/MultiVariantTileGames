package timmax.tilegame.basemodel.protocol.cs.inners.server;

import timmax.tilegame.basemodel.protocol.EventOfServer70GameMatchIsNotPlaying;
import timmax.tilegame.basemodel.protocol.cs.inners.CSP08GameIsPlaying;
import timmax.tilegame.basemodel.protocol.cs.inners.ClientState;
import timmax.tilegame.basemodel.protocol.server.IModelOfServer;
import timmax.tilegame.transport.TransportOfServer;

public class RCSP08GameIsPlaying<ClientId> extends CSP08GameIsPlaying<IModelOfServer> {
    private final ClientId clientId;
    private final TransportOfServer<ClientId> transportOfServer;

    public RCSP08GameIsPlaying(ClientState<IModelOfServer> clientState, ClientId clientId, TransportOfServer<ClientId> transportOfServer) {
        super(clientState);
        this.clientId = clientId;
        this.transportOfServer = transportOfServer;
    }

    @Override
    public void forgetGameIsPlaying() {
        super.forgetGameIsPlaying();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer70GameMatchIsNotPlaying());
    }
}
