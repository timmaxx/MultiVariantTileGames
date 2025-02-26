package timmax.tilegame.basemodel.protocol.client.jfx;

import timmax.tilegame.basemodel.protocol.client.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class FabricOfClientStatesJfx implements IFabricOfClientStates {
    @Override
    public LocalClientState01NoConnect getClientState01NoConnect(ClientStateAutomaton clientStateAutomaton) {
        return new LocalClientState01NoConnect(clientStateAutomaton);
    }

    @Override
    public LocalClientState02ConnectWithoutServerInfo getClientState02ConnectWithoutServerInfo(ClientStateAutomaton clientStateAutomaton) {
        return new LocalClientState02ConnectWithoutServerInfo(clientStateAutomaton);
    }

    @Override
    public ClientState03ConnectWithServerInfo getClientState03ConnectWithServerInfo(ClientStateAutomaton clientStateAutomaton) {
        return new LocalClientState03ConnectWithServerInfo(clientStateAutomaton);
    }

    @Override
    public LocalClientState04UserWasAuthorized getClientState04UserWasAuthorized(ClientStateAutomaton clientStateAutomaton) {
        return new LocalClientState04UserWasAuthorized(clientStateAutomaton);
    }

    @Override
    public LocalClientState06GameTypeWasSet getClientState06GameTypeWasSet(ClientStateAutomaton clientStateAutomaton) {
        return new LocalClientState06GameTypeWasSet(clientStateAutomaton);
    }

    @Override
    public LocalClientState07GameMatchWasSet getClientState07GameMatchWasSet(ClientStateAutomaton clientStateAutomaton) {
        return new LocalClientState07GameMatchWasSet(clientStateAutomaton);
    }

    @Override
    public LocalClientState08GameMatchIsPlaying getClientState08GameMatchIsPlaying(ClientStateAutomaton clientStateAutomaton) {
        return new LocalClientState08GameMatchIsPlaying(clientStateAutomaton);
    }
}
