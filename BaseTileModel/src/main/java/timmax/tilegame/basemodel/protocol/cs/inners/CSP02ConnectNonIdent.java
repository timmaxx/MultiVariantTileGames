package timmax.tilegame.basemodel.protocol.cs.inners;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

public class CSP02ConnectNonIdent<Model> extends AClientStateProtected<Model> {
    public CSP02ConnectNonIdent(ClientState<Model> clientState) {
        super(clientState);
    }

    // ---- 2 ConnectNonIdent
    @Override
    public void setUserName(String userName) {
        setUserName_(userName);
        clientState.getCSP03ConnectAuthorized().setAsCurrent();
    }

    // ---- X
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.CONNECT_NON_IDENT;
    }
}
