package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.*;

public interface IFabricOfRemoteClientStates<ClientId> extends IFabricOfClientStates<IGameMatch> {
    @Override
    RemoteClientState01NoConnect getClientState01NoConnect(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
    @Override
    RemoteClientState02ConnectNonIdent<ClientId> getClientState02ConnectNonIdent(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
    @Override
    RemoteClientState04GameTypeSetSelected<ClientId> getClientState04GameTypeSetSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
    @Override
    RemoteClientState06GameMatchSetSelected<ClientId> getClientState06GameMatchSetSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
    @Override
    RemoteClientState07GameMatchSelected<ClientId> getClientState07GameMatchSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
    @Override
    RemoteClientState08GameMatchIsPlaying<ClientId> getClientState08GameMatchIsPlaying(ClientStateAutomaton<IGameMatch> clientStateAutomaton);
}
