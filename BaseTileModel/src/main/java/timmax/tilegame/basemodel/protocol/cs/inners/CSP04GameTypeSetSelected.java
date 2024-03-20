package timmax.tilegame.basemodel.protocol.cs.inners;

import java.util.Set;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

public class CSP04GameTypeSetSelected<Model> extends CSP03ConnectAuthorized<Model> {
    public CSP04GameTypeSetSelected(ClientState<Model> clientState) {
        super(clientState);
    }

    // ---- 4 (GameTypeSetSelected)
    @Override
    public Set<ModelOfServerDescriptor> getGameTypeSet() {
        return clientState.setOfModelOfServerDescriptor;
    }

    @Override
    public void forgetGameTypeSet() {
        forgetGameTypeSet_();
        clientState.getCSP03ConnectAuthorized().setAsCurrent();
    }

    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        setGameType_(modelOfServerDescriptor);
        clientState.getCSP05GameTypeSelected().setAsCurrent();
    }

    // ---- X
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.GAME_TYPE_SET_SELECTED;
    }
}
