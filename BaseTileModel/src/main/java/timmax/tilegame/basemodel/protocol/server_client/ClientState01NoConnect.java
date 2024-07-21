package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState01NoConnect<GameMatchX extends IGameMatchX> extends AbstractClientState<GameMatchX> {
    public ClientState01NoConnect(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // Метод переопределён чтобы вызов changeStateTo02ConnectNonIdent не приводил к исключению
    // (как это определено в классе AbstractClientState)
    @Override
    public void changeStateTo02ConnectNonIdent() {
    }
}
