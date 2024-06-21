package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState07GameMatchSelected<Model> extends AbstractClientState<Model> implements IClientState07GameMatchSelected<Model> {
    protected Model serverBaseModel; // ---- 6 (Конкретная модель игры)

    public ClientState07GameMatchSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    protected void setServerBaseModel_(Model serverBaseModel) {
        getClientStateAutomaton().clientState08GameIsPlaying.forgetGameIsPlaying();
        this.serverBaseModel = serverBaseModel;
    }

    // interface IClientState07GameMatchSelected
    @Override
    public Model getServerBaseModel() {
        return serverBaseModel;
    }

    @Override
    public void forgetServerBaseModel() {
        setServerBaseModel_(null);
    }

    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        getClientStateAutomaton().clientState08GameIsPlaying.setGameIsPlaying_(gameIsPlaying);
    }
}
