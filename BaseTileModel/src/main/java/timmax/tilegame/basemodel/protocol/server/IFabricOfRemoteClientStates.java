package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.*;

public interface IFabricOfRemoteClientStates extends IFabricOfClientStates<IGameMatch> {
    @Override
    RemoteClientState01NoConnect getClientState01NoConnect(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
    @Override
    RemoteClientState02ConnectWithoutServerInfo getClientState02ConnectWithoutServerInfo(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
    @Override
    RemoteClientState03ConnectWithServerInfo getClientState03ConnectWithServerInfo(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
    @Override
    RemoteClientState04UserWasAuthorized getClientState04UserWasAuthorized(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
    @Override
    RemoteClientState06GameTypeWasSet getClientState06GameTypeWasSet(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
    @Override
    RemoteClientState07GameMatchWasSet getClientState07GameMatchWasSet(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
    @Override
    RemoteClientState08GameMatchIsPlaying getClientState08GameMatchIsPlaying(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
}
