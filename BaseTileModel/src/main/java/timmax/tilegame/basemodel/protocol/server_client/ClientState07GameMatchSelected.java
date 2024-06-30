package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState07GameMatchSelected<Model> extends AbstractClientState<Model> implements IClientState07GameMatchSelected<Model> {
    protected Model serverBaseModel; // ---- 6 (Конкретная модель игры)

    public ClientState07GameMatchSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    protected void setServerBaseModel_(Model serverBaseModel) {
        getClientStateAutomaton().clientState08GameMatchPlaying.forgetGameMatchPlaying();
        this.serverBaseModel = serverBaseModel;
    }

    // interface IClientState07GameMatchSelected
    @Override
    public Model getServerBaseModel() {
        return serverBaseModel;
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
