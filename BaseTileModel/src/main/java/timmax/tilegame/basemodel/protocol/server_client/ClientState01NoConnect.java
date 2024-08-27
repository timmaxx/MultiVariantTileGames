package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState01NoConnect extends AbstractClientState {
    public ClientState01NoConnect(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public void connect() {
        getClientStateAutomaton().connect_();
    }
}
