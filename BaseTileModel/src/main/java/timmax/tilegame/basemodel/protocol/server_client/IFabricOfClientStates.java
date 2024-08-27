package timmax.tilegame.basemodel.protocol.server_client;

// ToDo: удалить интерфейс, если будет удалён FabricOfClientStatesJfx (и LocalClientState0Х...Jfx)
public interface IFabricOfClientStates {
    //  ToDo:   Устранить взаимозависимость классов, реализующих IFabricOfClientStates
    //          (FabricOfClientStatesJfx и FabricOfRemoteClientStates)
    //          и класса ClientStateAutomaton.
    //          Взаимозависимость может проявиться тогда, когда в реализациях IFabricOfClientStates
    //          появляется ClientStateAutomaton (а ей желательно там появиться).
    //          Поэтому при вызове их конструкторов приходится создавать и пользоваться сеттером.
    ClientState01NoConnect getClientState01NoConnect(ClientStateAutomaton clientStateAutomaton);
    ClientState02ConnectNonIdent getClientState02ConnectNonIdent(ClientStateAutomaton clientStateAutomaton);
    ClientState04GameTypeSetSelected getClientState04GameTypeSetSelected(ClientStateAutomaton clientStateAutomaton);
    ClientState06GameMatchSetSelected getClientState06GameMatchSetSelected(ClientStateAutomaton clientStateAutomaton);
    ClientState07GameMatchSelected getClientState07GameMatchSelected(ClientStateAutomaton clientStateAutomaton);
    ClientState08GameMatchIsPlaying getClientState08GameMatchIsPlaying(ClientStateAutomaton clientStateAutomaton);
}
