package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

import java.util.Set;

public abstract class ClientState03ConnectAuthorized<Model> extends AbstractClientState<Model> implements IClientState03ConnectAuthorized {
    private String userName; // ---- 2 (Пользователь)

    public ClientState03ConnectAuthorized(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    protected void setUserName_(String userName) {
        getClientStateAutomaton().clientState04GameTypeSetSelected.forgetGameTypeSet();
        this.userName = userName;
    }

    // interface IClientState03ConnectAuthorized
    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void forgetUser() {
        setUserName_("");
    }

    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        // ToDo: сделать какую-то проверку на корректностность.
        getClientStateAutomaton().clientState04GameTypeSetSelected.setGameTypeSet_(setOfModelOfServerDescriptor);
    }
}
