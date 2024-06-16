package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.transport.TransportOfServer;

public abstract class ClientState08GameIsPlaying<Model, ClientId> extends AbstractClientState2<Model, ClientId> implements IClientState08GameIsPlaying {
    protected Boolean gameIsPlaying; // ---- 7 (Партия была начата)

    public ClientState08GameIsPlaying(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    public ClientState08GameIsPlaying(ClientStateAutomaton<Model, ClientId> clientStateAutomaton, TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        super(clientStateAutomaton, transportOfServer, clientId);
    }

    protected void setGameIsPlaying_(Boolean gameIsPlaying) {
        this.gameIsPlaying = gameIsPlaying;
    }

    @Override
    public Boolean getGameIsPlaying() {
        return gameIsPlaying;
    }

    @Override
    public void forgetGameIsPlaying() {
        setGameIsPlaying_(null);
    }

    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.GAME_IS_PLAYING;
    }
}
