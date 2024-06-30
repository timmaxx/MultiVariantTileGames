package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState07GameMatchSelected<Model> extends AbstractClientState<Model> implements IClientState07GameMatchSelected<Model> {
    public ClientState07GameMatchSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    protected void setServerBaseModel_(Model serverBaseModel) {
        getClientStateAutomaton().clientState08GameMatchPlaying.forgetGameMatchPlaying();
        getClientStateAutomaton().setServerBaseModel0(serverBaseModel);
    }

    // interface IClientState07GameMatchSelected
    @Override
    public Model getServerBaseModel() {
        return getClientStateAutomaton().getServerBaseModel0();
    }

    @Override
    public void forgetGameMatch() {
        setServerBaseModel_(null);
    }

    @Override
    public void setGameMatchPlaying(Boolean gameIsPlaying) {
        getClientStateAutomaton().clientState08GameMatchPlaying.setGameIsPlaying_(gameIsPlaying);
    }
}
