package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState01NoConnect extends ClientState {
    public ClientState01NoConnect(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public void connect() {
        getBaseStateAutomaton().connect_();
    }
}
