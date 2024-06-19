package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

import java.util.Set;

public abstract class ClientState03ConnectAuthorized<Model, ClientId> extends AbstractClientState<Model, ClientId> implements IClientState03ConnectAuthorized {
    private String userName; // ---- 2 (Пользователь)

    public ClientState03ConnectAuthorized(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // ToDo: сделать его private после реализации всех подобных следующих комментариев.
    protected void setUserName_(String userName) {
        // ToDo: вместо вызова с null параметром, следует вызывать соответствующий forgetXxx();
        getClientStateAutomaton().clientState04GameTypeSetSelected.setGameTypeSet_(null);
        this.userName = userName;
    }

    // Overriden methods of class AbstractClientState
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

    // interface IClientState00
    // ToDo: delete from interface IClientState00 and from this class
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.CONNECT_AUTHORIZED;
    }
}
