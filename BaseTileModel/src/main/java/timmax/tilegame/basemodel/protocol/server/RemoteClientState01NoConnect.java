package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.ClientState01NoConnect;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class RemoteClientState01NoConnect<ClientId> extends ClientState01NoConnect<IGameMatch> {
    ClientId clientId;

    public RemoteClientState01NoConnect(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }
}
