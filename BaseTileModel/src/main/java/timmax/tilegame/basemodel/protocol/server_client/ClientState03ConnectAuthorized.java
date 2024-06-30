package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

import java.util.Set;

public abstract class ClientState03ConnectAuthorized<Model> extends AbstractClientState<Model> implements IClientState03ConnectAuthorized {
    public ClientState03ConnectAuthorized(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState03ConnectAuthorized
    @Override
    public String getUserName() {
        return getClientStateAutomaton().getUserName0();
    }

    @Override
    public void forgetUser() {
        getClientStateAutomaton().setUserName_("");
    }

    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        // ToDo: сделать какую-то проверку на корректностность.
        getClientStateAutomaton().setGameTypeSet_(setOfModelOfServerDescriptor);
    }
}
