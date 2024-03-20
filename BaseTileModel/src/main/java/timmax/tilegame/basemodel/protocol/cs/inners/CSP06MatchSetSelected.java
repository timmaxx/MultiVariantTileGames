package timmax.tilegame.basemodel.protocol.cs.inners;

import java.util.Set;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

public class CSP06MatchSetSelected<Model> extends CSP05GameTypeSelected<Model> {
    public CSP06MatchSetSelected(ClientState<Model> clientState) {
        super(clientState);
    }

    // ---- 6 (MatchSetSelected)
    @Override
    public Set<Model> getGameMatchSet() {
        // return clientState.gameMatchSet;
        return clientState.setOfServerBaseModel;
    }

    @Override
    public void forgetGameMatchSet() {
        forgetGameMatchSet_();
        clientState.getCSP05GameTypeSelected().setAsCurrent();
    }

    @Override
    public void setServerBaseModel(Model serverBaseModel) {
        setServerBaseModel_(serverBaseModel);
        clientState.getCsp07MatchSelected().setAsCurrent();
    }

    // ---- X
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.GAME_MATCH_SET_SELECTED;
    }
}
