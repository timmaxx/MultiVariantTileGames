package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

public abstract class ClientState07GameMatchSelected<Model, ClientId> extends AbstractClientState2<Model, ClientId> implements IClientState07GameMatchSelected<Model> {
    protected Model serverBaseModel; // ---- 6 (Конкретная модель игры)

    public ClientState07GameMatchSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    protected void setServerBaseModel_(Model serverBaseModel) {
        getClientStateAutomaton().clientState08GameIsPlaying.setGameIsPlaying_(null);
        this.serverBaseModel = serverBaseModel;
    }

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

    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.GAME_MATCH_SELECTED;
    }
}
