package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

import java.util.Set;

public abstract class ClientState03ConnectAuthorized<Model, ClientId> extends AbstractClientState2<Model, ClientId> implements IClientState03ConnectAuthorized {
    private String userName; // ---- 2 (Пользователь)

    public ClientState03ConnectAuthorized(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    protected void setUserName_(String userName) {
        getClientStateAutomaton().clientState04GameTypeSetSelected.setGameTypeSet_(null);
        this.userName = userName;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void forgetUserName() {
        setUserName_("");
    }

    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        // ToDo: сделать какую-то проверку на корректностность.
        getClientStateAutomaton().clientState04GameTypeSetSelected.setGameTypeSet_(setOfModelOfServerDescriptor);
    }

    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.CONNECT_AUTHORIZED;
    }
}
