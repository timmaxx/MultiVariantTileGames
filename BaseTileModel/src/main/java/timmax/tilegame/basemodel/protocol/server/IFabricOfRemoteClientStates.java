package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.*;

public interface IFabricOfRemoteClientStates<ClientId> extends IFabricOfClientStates<IModelOfServer, ClientId> {
    RemoteClientState01NoConect<ClientId> getClientState01NoConect(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton);
    RemoteClientState02ConnectNonIdent<ClientId> getClientState02ConnectNonIdent(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton);
    RemoteClientState03ConnectAuthorized<ClientId> getClientState03ConnectAuthorized(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton);
    RemoteClientState04GameTypeSetSelected<ClientId> getClientState04GameTypeSetSelected(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton);
    RemoteClientState05GameTypeSelected<ClientId> getClientState05GameTypeSelected(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton);
    RemoteClientState06GameMatchSetSelected<ClientId> getClientState06GameMatchSetSelected(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton);
    RemoteClientState07GameMatchSelected<ClientId> getClientState07GameMatchSelected(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton);
    RemoteClientState08GameIsPlaying<ClientId> getClientState08GameIsPlaying(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton);
}
