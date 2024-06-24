package timmax.tilegame.basemodel.protocol.server_client;

// ToDo: удалить интерфейс, если будет удалён FabricOfClientStatesJfx (и LocalClientState0Х...Jfx)
public interface IFabricOfClientStates<Model> {
    ClientState01NoConnect<Model> getClientState01NoConnect(ClientStateAutomaton<Model> clientStateAutomaton);
    ClientState02ConnectNonIdent<Model> getClientState02ConnectNonIdent(ClientStateAutomaton<Model> clientStateAutomaton);
    ClientState03ConnectAuthorized<Model> getClientState03ConnectAuthorized(ClientStateAutomaton<Model> clientStateAutomaton);
    ClientState04GameTypeSetSelected<Model> getClientState04GameTypeSetSelected(ClientStateAutomaton<Model> clientStateAutomaton);
    ClientState05GameTypeSelected<Model> getClientState05GameTypeSelected(ClientStateAutomaton<Model> clientStateAutomaton);
    ClientState06GameMatchSetSelected<Model> getClientState06GameMatchSetSelected(ClientStateAutomaton<Model> clientStateAutomaton);
    ClientState07GameMatchSelected<Model> getClientState07GameMatchSelected(ClientStateAutomaton<Model> clientStateAutomaton);
    ClientState08GameIsPlaying<Model> getClientState08GameIsPlaying(ClientStateAutomaton<Model> clientStateAutomaton);
}

/*
//  Такой вариант рассматривался, но он вёл к тому, что приходилось созавать IFabricOfClientStates с пустой
//  ClientStateAtomaton, потом создавать ClientStateAtomaton, которому в конструкторе передавал IFabricOfClientStates,
//  и потом для IFabricOfClientStates вызывать setClientStateAutomaton.
//
//  Классы взаимозависимы и поэтому такой приём не привёл к хорошему варианту.
public interface IFabricOfClientStates<Model> {
    void setClientStateAutomaton(ClientStateAutomaton<Model> ClientState);
    ClientState01NoConnect<Model> getClientState01NoConnect();
    ClientState02ConnectNonIdent<Model> getClientState02ConnectNonIdent();
    ClientState03ConnectAuthorized<Model> getClientState03ConnectAuthorized();
    ClientState04GameTypeSetSelected<Model> getClientState04GameTypeSetSelected();
    ClientState05GameTypeSelected<Model> getClientState05GameTypeSelected();
    ClientState06GameMatchSetSelected<Model> getClientState06GameMatchSetSelected();
    ClientState07GameMatchSelected<Model> getClientState07GameMatchSelected();
    ClientState08GameIsPlaying<Model> getClientState08GameIsPlaying();
}
*/
