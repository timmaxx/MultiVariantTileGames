package timmax.tilegame.basemodel.protocol.server_client;

// ToDo: удалить интерфейс, если будет удалён FabricOfClientStatesJfx (и LocalClientState0Х...Jfx)
public interface IFabricOfClientStates<GameMatchX extends IGameMatchX> {
    // ToDo: Устранить взаимозависимость классов, реализующих IFabricOfClientStates
    //  (FabricOfClientStatesJfx и FabricOfRemoteClientStates)
    //  и класса ClientStateAutomaton.
    //  Взаимозависимость может проявиться тогда, когда в реализациях IFabricOfClientStates
    //  появляется ClientStateAutomaton (а ей желательно там появиться).
    //  Поэтому при вызове их конструкторов приходится создавать и пользоваться сеттером.
    ClientState01NoConnect<GameMatchX> getClientState01NoConnect(ClientStateAutomaton<GameMatchX> clientStateAutomaton);
    ClientState02ConnectWithoutServerInfo<GameMatchX> getClientState02ConnectWithoutServerInfo(ClientStateAutomaton<GameMatchX> clientStateAutomaton);
    ClientState03ConnectWithServerInfo<GameMatchX> getClientState03ConnectWithServerInfo(ClientStateAutomaton<GameMatchX> gameMatchXClientStateAutomaton);
    ClientState04UserWasAuthorized<GameMatchX> getClientState04UserWasAuthorized(ClientStateAutomaton<GameMatchX> clientStateAutomaton);
    ClientState06GameTypeWasSet<GameMatchX> getClientState06GameTypeWasSet(ClientStateAutomaton<GameMatchX> clientStateAutomaton);
    ClientState07GameMatchWasSet<GameMatchX> getClientState07GameMatchWasSet(ClientStateAutomaton<GameMatchX> clientStateAutomaton);
    ClientState08GameMatchIsPlaying<GameMatchX> getClientState08GameMatchIsPlaying(ClientStateAutomaton<GameMatchX> clientStateAutomaton);
}
