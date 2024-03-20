package timmax.tilegame.basemodel.protocol.cs.inners;

import java.util.Set;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

public class CSP05GameTypeSelected<Model> extends CSP04GameTypeSetSelected<Model> {
    public CSP05GameTypeSelected(ClientState<Model> clientState) {
        super(clientState);
    }

    // ---- 5 (GameTypeSelected)
    @Override
    public ModelOfServerDescriptor getGameType() {
        // return clientState.gameType;
        return clientState.modelOfServerDescriptor;
    }

    @Override
    public void forgetGameType() {
        forgetGameType_();
        clientState.getCSP04GameTypeSetSelected().setAsCurrent();
    }

    @Override
    public void setGameMatchSet(Set<Model> setOfServerBaseModel) {
        setGameMatchSet_(setOfServerBaseModel);
        clientState.getCSP06MatchSetSelected().setAsCurrent();
    }

    // ---- X
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.GAME_TYPE_SELECTED;
    }
}
