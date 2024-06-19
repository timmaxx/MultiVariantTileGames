package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

public abstract class ClientState01NoConect<Model, ClientId> extends AbstractClientState<Model, ClientId> implements IClientState01NoConect {
    public ClientState01NoConect(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState00
    // ToDo: delete from interface IClientState00 and from this class
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.NO_CONNECT;
    }
}
