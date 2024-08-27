package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.*;

public interface IFabricOfRemoteClientStates<ClientId> extends IFabricOfClientStates {
    @Override
    RemoteClientState01NoConnect getClientState01NoConnect(ClientStateAutomaton clientStateAutomaton);
    @Override
    RemoteClientState02ConnectNonIdent<ClientId> getClientState02ConnectNonIdent(ClientStateAutomaton clientStateAutomaton);
    @Override
    RemoteClientState04GameTypeSetSelected<ClientId> getClientState04GameTypeSetSelected(ClientStateAutomaton clientStateAutomaton);
    @Override
    RemoteClientState06GameMatchSetSelected<ClientId> getClientState06GameMatchSetSelected(ClientStateAutomaton clientStateAutomaton);
    @Override
    RemoteClientState07GameMatchSelected<ClientId> getClientState07GameMatchSelected(ClientStateAutomaton clientStateAutomaton);
    @Override
    RemoteClientState08GameMatchIsPlaying<ClientId> getClientState08GameMatchIsPlaying(ClientStateAutomaton clientStateAutomaton);
}
