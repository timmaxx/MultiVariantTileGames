package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.*;

public interface IFabricOfRemoteClientStates extends IFabricOfClientStates {
    @Override
    RemoteClientState01NoConnect getClientState01NoConnect(ClientStateAutomaton clientStateAutomaton);
    @Override
    RemoteClientState02ConnectWithoutServerInfo getClientState02ConnectWithoutServerInfo(ClientStateAutomaton clientStateAutomaton);
    @Override
    RemoteClientState03ConnectWithServerInfo getClientState03ConnectWithServerInfo(ClientStateAutomaton clientStateAutomaton);
    @Override
    RemoteClientState04UserWasAuthorized getClientState04UserWasAuthorized(ClientStateAutomaton clientStateAutomaton);
    @Override
    RemoteClientState06GameTypeWasSet getClientState06GameTypeWasSet(ClientStateAutomaton clientStateAutomaton);
    @Override
    RemoteClientState07GameMatchWasSet getClientState07GameMatchWasSet(ClientStateAutomaton clientStateAutomaton);
    @Override
    RemoteClientState08GameMatchIsPlaying getClientState08GameMatchIsPlaying(ClientStateAutomaton clientStateAutomaton);
}
