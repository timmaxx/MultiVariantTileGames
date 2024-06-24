package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.*;

public interface IFabricOfRemoteClientStates<ClientId> extends IFabricOfClientStates<IModelOfServer> {
    RemoteClientState01NoConnect<ClientId> getClientState01NoConnect(ClientStateAutomaton<IModelOfServer> clientStateAutomaton);
    RemoteClientState02ConnectNonIdent<ClientId> getClientState02ConnectNonIdent(ClientStateAutomaton<IModelOfServer> clientStateAutomaton);
    RemoteClientState03ConnectAuthorized<ClientId> getClientState03ConnectAuthorized(ClientStateAutomaton<IModelOfServer> clientStateAutomaton);
    RemoteClientState04GameTypeSetSelected<ClientId> getClientState04GameTypeSetSelected(ClientStateAutomaton<IModelOfServer> clientStateAutomaton);
    RemoteClientState05GameTypeSelected<ClientId> getClientState05GameTypeSelected(ClientStateAutomaton<IModelOfServer> clientStateAutomaton);
    RemoteClientState06GameMatchSetSelected<ClientId> getClientState06GameMatchSetSelected(ClientStateAutomaton<IModelOfServer> clientStateAutomaton);
    RemoteClientState07GameMatchSelected<ClientId> getClientState07GameMatchSelected(ClientStateAutomaton<IModelOfServer> clientStateAutomaton);
    RemoteClientState08GameIsPlaying<ClientId> getClientState08GameIsPlaying(ClientStateAutomaton<IModelOfServer> clientStateAutomaton);
}

/*
//  См. коммент к IFabricOfClientStates
public interface IFabricOfRemoteClientStates<ClientId> extends IFabricOfClientStates<IModelOfServer> {
    @Override
    void setClientStateAutomaton(ClientStateAutomaton ClientState);
    @Override
    RemoteClientState01NoConnect<ClientId> getClientState01NoConnect();
    @Override
    RemoteClientState02ConnectNonIdent<ClientId> getClientState02ConnectNonIdent();
    @Override
    RemoteClientState03ConnectAuthorized<ClientId> getClientState03ConnectAuthorized();
    @Override
    RemoteClientState04GameTypeSetSelected<ClientId> getClientState04GameTypeSetSelected();
    @Override
    RemoteClientState05GameTypeSelected<ClientId> getClientState05GameTypeSelected();
    @Override
    RemoteClientState06GameMatchSetSelected<ClientId> getClientState06GameMatchSetSelected();
    @Override
    RemoteClientState07GameMatchSelected<ClientId> getClientState07GameMatchSelected();
    @Override
    RemoteClientState08GameIsPlaying<ClientId> getClientState08GameIsPlaying();
}
*/
