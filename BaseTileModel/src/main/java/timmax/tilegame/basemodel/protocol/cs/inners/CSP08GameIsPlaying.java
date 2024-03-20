package timmax.tilegame.basemodel.protocol.cs.inners;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

public class CSP08GameIsPlaying<Model> extends CSP07MatchSelected<Model> {
    public CSP08GameIsPlaying(ClientState<Model> clientState) {
        super(clientState);
    }

    // ---- 8 (GameIsPlaying)
    @Override
    public Boolean getGameIsPlaying() {
        return clientState.gameIsPlaying;
    }

    @Override
    public void forgetGameIsPlaying() {
        forgetGameIsPlaying_();
        clientState.getCsp07MatchSelected().setAsCurrent();
    }

    // ---- X
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.GAME_IS_PLAYING;
    }
}
