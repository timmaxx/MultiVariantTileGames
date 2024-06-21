package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.ClientState01NoConect;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class RemoteClientState01NoConect<ClientId> extends ClientState01NoConect<IModelOfServer> {
    public RemoteClientState01NoConect(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        super(clientStateAutomaton);
    }
}
