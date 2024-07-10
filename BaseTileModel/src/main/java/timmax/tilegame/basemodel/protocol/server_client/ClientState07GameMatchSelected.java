package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState07GameMatchSelected<Model> extends AbstractClientState<Model> implements IClientState07GameMatchSelected<Model> {
    public ClientState07GameMatchSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState07GameMatchSelected
    @Override
    public Model getGameMatchX() {
        return getClientStateAutomaton().getGameMatchX_();
    }

    @Override
    public void forgetGameMatchX() {
        getClientStateAutomaton().setGameMatchX_(null);
    }

    @Override
    public void setGameMatchPlaying(Boolean gameIsPlaying) {
        getClientStateAutomaton().setGameIsPlaying_(gameIsPlaying);
    }
}
