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
    ClientState02ConnectNonIdent<GameMatchX> getClientState02ConnectNonIdent(ClientStateAutomaton<GameMatchX> clientStateAutomaton);
    ClientState04UserAuthorized<GameMatchX> getClientState04UserAuthorized(ClientStateAutomaton<GameMatchX> clientStateAutomaton);
    ClientState06GameMatchSetSelected<GameMatchX> getClientState06GameMatchSetSelected(ClientStateAutomaton<GameMatchX> clientStateAutomaton);
    ClientState07GameMatchSelected<GameMatchX> getClientState07GameMatchSelected(ClientStateAutomaton<GameMatchX> clientStateAutomaton);
    ClientState08GameMatchIsPlaying<GameMatchX> getClientState08GameMatchIsPlaying(ClientStateAutomaton<GameMatchX> clientStateAutomaton);
}
