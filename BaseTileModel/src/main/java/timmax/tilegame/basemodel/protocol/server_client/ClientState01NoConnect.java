package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState01NoConnect<GameMatchX extends IGameMatchX> extends AbstractClientState<GameMatchX> implements IClientState99<GameMatchX> {
    public ClientState01NoConnect(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }
}
