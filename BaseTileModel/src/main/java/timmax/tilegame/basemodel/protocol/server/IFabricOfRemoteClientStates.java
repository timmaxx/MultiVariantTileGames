package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.*;

public interface IFabricOfRemoteClientStates<ClientId> extends IFabricOfClientStates<IGameMatch> {
    @Override
    RemoteClientState01NoConnect getClientState01NoConnect(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
    @Override
    RemoteClientState02ConnectNonIdent<ClientId> getClientState02ConnectNonIdent(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
    @Override
    RemoteClientState04UserWasAuthorized<ClientId> getClientState04UserWasAuthorized(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
    @Override
    RemoteClientState06GameTypeWasSet<ClientId> getClientState06GameTypeWasSet(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
    @Override
    RemoteClientState07GameMatchWasSet<ClientId> getClientState07GameMatchWasSet(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
    @Override
    RemoteClientState08GameMatchIsPlaying<ClientId> getClientState08GameMatchIsPlaying(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
}
