package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.ClientState01NoConect;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class RemoteClientState01NoConect<ClientId> extends ClientState01NoConect<IModelOfServer, ClientId> {
    public RemoteClientState01NoConect(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }
}
