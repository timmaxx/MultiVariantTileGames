package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState07GameMatchSelected<Model, ClientId> extends AbstractClientState<Model, ClientId> implements IClientState07GameMatchSelected<Model> {
    protected Model serverBaseModel; // ---- 6 (Конкретная модель игры)

    public ClientState07GameMatchSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // ToDo: сделать его private после реализации всех подобных следующих комментариев.
    protected void setServerBaseModel_(Model serverBaseModel) {
        // ToDo: вместо вызова с null параметром, следует вызывать соответствующий forgetXxx();
        getClientStateAutomaton().clientState08GameIsPlaying.setGameIsPlaying_(null);
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
