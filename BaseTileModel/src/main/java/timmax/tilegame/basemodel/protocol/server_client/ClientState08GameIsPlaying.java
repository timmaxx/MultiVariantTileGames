package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

public abstract class ClientState08GameIsPlaying<Model, ClientId> extends AbstractClientState<Model, ClientId> implements IClientState08GameIsPlaying {
    protected Boolean gameIsPlaying; // ---- 7 (Партия была начата)

    public ClientState08GameIsPlaying(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // ToDo: сделать его private после реализации всех подобных следующих комментариев.
    protected void setGameIsPlaying_(Boolean gameIsPlaying) {
        this.gameIsPlaying = gameIsPlaying;
    }

    // Overriden methods of class AbstractClientState
    @Override
    public Boolean getGameIsPlaying() {
        return gameIsPlaying;
    }

    @Override
    public void forgetGameIsPlaying() {
        setGameIsPlaying_(null);
    }

    // interface IClientState00
    // ToDo: delete from interface IClientState00 and from this class
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.GAME_IS_PLAYING;
    }
}
