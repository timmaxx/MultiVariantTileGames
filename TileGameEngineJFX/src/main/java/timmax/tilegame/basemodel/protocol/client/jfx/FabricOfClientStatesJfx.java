package timmax.tilegame.basemodel.protocol.client.jfx;

import timmax.tilegame.basemodel.protocol.client.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class FabricOfClientStatesJfx<Model> implements IFabricOfClientStates<Model> {
    @Override
    public ClientState01NoConnect<Model> getClientState01NoConnect(ClientStateAutomaton<Model> clientStateAutomaton) {
        return new LocalClientState01NoConnect<>(clientStateAutomaton);
    }

    @Override
    public ClientState02ConnectNonIdent<Model> getClientState02ConnectNonIdent(ClientStateAutomaton<Model> clientStateAutomaton) {
        return new LocalClientState02ConnectNonIdent<>(clientStateAutomaton);
    }

    @Override
    public ClientState03ConnectAuthorized<Model> getClientState03ConnectAuthorized(ClientStateAutomaton<Model> clientStateAutomaton) {
        return new LocalClientState03ConnectAuthorized<>(clientStateAutomaton);
    }

    @Override
    public ClientState04GameTypeSetSelected<Model> getClientState04GameTypeSetSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        return new LocalClientState04GameTypeSetSelected<>(clientStateAutomaton);
    }

    @Override
    public ClientState05GameTypeSelected<Model> getClientState05GameTypeSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        return new LocalClientState05GameTypeSelected<>(clientStateAutomaton);
    }

    @Override
    public ClientState06GameMatchSetSelected<Model> getClientState06GameMatchSetSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        return new LocalClientState06GameMatchSetSelected<>(clientStateAutomaton);
    }

    @Override
    public ClientState07GameMatchSelected<Model> getClientState07GameMatchSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        return new LocalClientState07GameMatchSelected<>(clientStateAutomaton);
    }

    @Override
    public ClientState08GameIsPlaying<Model> getClientState08GameIsPlaying(ClientStateAutomaton<Model> clientStateAutomaton) {
        return new LocalClientState08GameIsPlaying<>(clientStateAutomaton);
    }
}
