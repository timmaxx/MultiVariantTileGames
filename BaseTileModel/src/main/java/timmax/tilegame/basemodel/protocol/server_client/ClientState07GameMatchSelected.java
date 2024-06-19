package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

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

    // Overriden methods of class AbstractClientState
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

    // interface IClientState00
    // ToDo: delete from interface IClientState00 and from this class
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.GAME_MATCH_SELECTED;
    }
}
