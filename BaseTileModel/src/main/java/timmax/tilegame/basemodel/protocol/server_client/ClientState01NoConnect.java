package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState01NoConnect<GameMatchX extends IGameMatchX> extends AbstractClientState<GameMatchX> {
    public ClientState01NoConnect(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public void openConnectWithoutUserIdentify() {
        getClientStateAutomaton().openConnectWithoutUserIdentify_();
    }
}
