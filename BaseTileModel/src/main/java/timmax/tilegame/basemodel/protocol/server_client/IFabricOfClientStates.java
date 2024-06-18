package timmax.tilegame.basemodel.protocol.server_client;

// ToDo: удалить интерфейс, если будет удалён FabricOfClientStatesJfx (и LocalClientState0Х...Jfx)
public interface IFabricOfClientStates<Model, ClientId> {
    ClientState01NoConect<Model, ClientId> getClientState01NoConect(ClientStateAutomaton<Model, ClientId> clientStateAutomaton);
    ClientState02ConnectNonIdent<Model, ClientId> getClientState02ConnectNonIdent(ClientStateAutomaton<Model, ClientId> clientStateAutomaton);
    ClientState03ConnectAuthorized<Model, ClientId> getClientState03ConnectAuthorized(ClientStateAutomaton<Model, ClientId> clientStateAutomaton);
    ClientState04GameTypeSetSelected<Model, ClientId> getClientState04GameTypeSetSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton);
    ClientState05GameTypeSelected<Model, ClientId> getClientState05GameTypeSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton);
    ClientState06GameMatchSetSelected<Model, ClientId> getClientState06GameMatchSetSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton);
    ClientState07GameMatchSelected<Model, ClientId> getClientState07GameMatchSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton);
    ClientState08GameIsPlaying<Model, ClientId> getClientState08GameIsPlaying(ClientStateAutomaton<Model, ClientId> clientStateAutomaton);
}
