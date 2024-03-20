package timmax.tilegame.basemodel.protocol.cs.inners;

import java.util.Set;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

public class CSP03ConnectAuthorized<Model> extends CSP02ConnectNonIdent<Model> {

    public CSP03ConnectAuthorized(ClientState<Model> clientState) {
        super(clientState);
    }

    // ---- 3 ConnectAuthorized
    @Override
    public String getUserName() {
        return clientState.userName;
    }

    @Override
    public void forgetUserName() {
        forgetUserName_();
        clientState.getCSP02ConnectNonIdent().setAsCurrent();
    }

    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        setGameTypeSet_(setOfModelOfServerDescriptor);
        clientState.getCSP04GameTypeSetSelected().setAsCurrent();
    }

    // ---- X
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.CONNECT_AUTHORIZED;
    }
}
