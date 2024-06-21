package timmax.tilegame.basemodel.protocol.server_client;

// ToDo: удалить интерфейс, если будет удалён FabricOfClientStatesJfx (и LocalClientState0Х...Jfx)
public interface IFabricOfClientStates<Model> {
    ClientState01NoConect<Model> getClientState01NoConect(ClientStateAutomaton<Model> clientStateAutomaton);
    ClientState02ConnectNonIdent<Model> getClientState02ConnectNonIdent(ClientStateAutomaton<Model> clientStateAutomaton);
    ClientState03ConnectAuthorized<Model> getClientState03ConnectAuthorized(ClientStateAutomaton<Model> clientStateAutomaton);
    ClientState04GameTypeSetSelected<Model> getClientState04GameTypeSetSelected(ClientStateAutomaton<Model> clientStateAutomaton);
    ClientState05GameTypeSelected<Model> getClientState05GameTypeSelected(ClientStateAutomaton<Model> clientStateAutomaton);
    ClientState06GameMatchSetSelected<Model> getClientState06GameMatchSetSelected(ClientStateAutomaton<Model> clientStateAutomaton);
    ClientState07GameMatchSelected<Model> getClientState07GameMatchSelected(ClientStateAutomaton<Model> clientStateAutomaton);
    ClientState08GameIsPlaying<Model> getClientState08GameIsPlaying(ClientStateAutomaton<Model> clientStateAutomaton);
}
