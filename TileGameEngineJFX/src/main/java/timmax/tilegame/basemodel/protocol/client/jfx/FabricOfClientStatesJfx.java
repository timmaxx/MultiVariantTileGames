package timmax.tilegame.basemodel.protocol.client.jfx;

import timmax.tilegame.basemodel.protocol.client.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class FabricOfClientStatesJfx<Model, ClientId> implements IFabricOfClientStates<Model, ClientId> {
    @Override
    public ClientState01NoConect<Model, ClientId> getClientState01NoConect(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        return new LocalClientState01NoConect<>(clientStateAutomaton);
    }

    @Override
    public ClientState02ConnectNonIdent<Model, ClientId> getClientState02ConnectNonIdent(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        return new LocalClientState02ConnectNonIdent<>(clientStateAutomaton);
    }

    @Override
    public ClientState03ConnectAuthorized<Model, ClientId> getClientState03ConnectAuthorized(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        return new LocalClientState03ConnectAuthorized<>(clientStateAutomaton);
    }

    @Override
    public ClientState04GameTypeSetSelected<Model, ClientId> getClientState04GameTypeSetSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        return new LocalClientState04GameTypeSetSelected<>(clientStateAutomaton);
    }

    @Override
    public ClientState05GameTypeSelected<Model, ClientId> getClientState05GameTypeSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        return new LocalClientState05GameTypeSelected<>(clientStateAutomaton);
    }

    @Override
    public ClientState06GameMatchSetSelected<Model, ClientId> getClientState06GameMatchSetSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        return new LocalClientState06GameMatchSetSelected<>(clientStateAutomaton);
    }

    @Override
    public ClientState07GameMatchSelected<Model, ClientId> getClientState07GameMatchSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        return new LocalClientState07GameMatchSelected<>(clientStateAutomaton);
    }

    @Override
    public ClientState08GameIsPlaying<Model, ClientId> getClientState08GameIsPlaying(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        return new LocalClientState08GameIsPlaying<>(clientStateAutomaton);
    }
}
