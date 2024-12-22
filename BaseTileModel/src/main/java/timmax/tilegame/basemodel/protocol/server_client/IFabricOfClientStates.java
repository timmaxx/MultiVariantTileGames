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
    ClientState02ConnectWithoutServerInfo getClientState02ConnectWithoutServerInfo(ClientStateAutomaton clientStateAutomaton);
    ClientState03ConnectWithServerInfo getClientState03ConnectWithServerInfo(ClientStateAutomaton gameMatchXClientStateAutomaton);
    ClientState04UserWasAuthorized getClientState04UserWasAuthorized(ClientStateAutomaton clientStateAutomaton);
    ClientState06GameTypeWasSet getClientState06GameTypeWasSet(ClientStateAutomaton clientStateAutomaton);
    ClientState07GameMatchWasSet getClientState07GameMatchWasSet(ClientStateAutomaton clientStateAutomaton);
    ClientState08GameMatchIsPlaying getClientState08GameMatchIsPlaying(ClientStateAutomaton clientStateAutomaton);
}
