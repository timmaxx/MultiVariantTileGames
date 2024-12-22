package timmax.tilegame.server.websocket;

import timmax.tilegame.basemodel.protocol.server.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class FabricOfRemoteClientStates implements IFabricOfRemoteClientStates {
    @Override
    public RemoteClientState01NoConnect getClientState01NoConnect(ClientStateAutomaton clientStateAutomaton) {
        return new RemoteClientState01NoConnect(clientStateAutomaton);
    }

    @Override
    public RemoteClientState02ConnectWithoutServerInfo getClientState02ConnectWithoutServerInfo(ClientStateAutomaton clientStateAutomaton) {
        return new RemoteClientState02ConnectWithoutServerInfo(clientStateAutomaton);
    }

    @Override
    public RemoteClientState03ConnectWithServerInfo getClientState03ConnectWithServerInfo(ClientStateAutomaton clientStateAutomaton) {
        return new RemoteClientState03ConnectWithServerInfo(clientStateAutomaton);
    }

    @Override
    public RemoteClientState04UserWasAuthorized getClientState04UserWasAuthorized(ClientStateAutomaton clientStateAutomaton) {
        return new RemoteClientState04UserWasAuthorized(clientStateAutomaton);
    }

    @Override
    public RemoteClientState06GameTypeWasSet getClientState06GameTypeWasSet(ClientStateAutomaton clientStateAutomaton) {
        return new RemoteClientState06GameTypeWasSet(clientStateAutomaton);
    }

    @Override
    public RemoteClientState07GameMatchWasSet getClientState07GameMatchWasSet(ClientStateAutomaton clientStateAutomaton) {
        return new RemoteClientState07GameMatchWasSet(clientStateAutomaton);
    }

    @Override
    public RemoteClientState08GameMatchIsPlaying getClientState08GameMatchIsPlaying(ClientStateAutomaton clientStateAutomaton) {
        return new RemoteClientState08GameMatchIsPlaying(clientStateAutomaton);
    }
}
