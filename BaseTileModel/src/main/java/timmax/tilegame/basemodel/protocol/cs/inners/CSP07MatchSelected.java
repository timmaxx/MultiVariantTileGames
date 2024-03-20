package timmax.tilegame.basemodel.protocol.cs.inners;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

public class CSP07MatchSelected<Model> extends CSP06MatchSetSelected<Model> {
    public CSP07MatchSelected(ClientState<Model> clientState) {
        super(clientState);
    }

    // ---- 7 (MatchSelected)
    @Override
    public Model getServerBaseModel() {
        return clientState.serverBaseModel;
    }

    @Override
    public void forgetServerBaseModel() {
        forgetServerBaseModel_();
        clientState.getCSP06MatchSetSelected().setAsCurrent();
    }

    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        setGameIsPlaying_(gameIsPlaying);
        clientState.getcSP08GameIsPlaying().setAsCurrent();
    }

    // ---- X
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.GAME_MATCH_SELECTED;
    }
}
