package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.ClientState03ConnectWithServerInfo;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class RemoteClientState03ConnectWithServerInfo<ClientId> extends ClientState03ConnectWithServerInfo<IGameMatch> {
    public RemoteClientState03ConnectWithServerInfo(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    //  class ClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getBaseStateAutomaton() {
        //  Warning:(23, 16) Unchecked cast: 'timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton' to 'timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton<ClientId>'
        return (RemoteClientStateAutomaton<ClientId>)(super.getBaseStateAutomaton());
    }
}
