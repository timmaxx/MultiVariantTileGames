package timmax.tilegame.basemodel.protocol.client.jfx;

import timmax.tilegame.basemodel.protocol.client.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class FabricOfClientStatesJfx implements IFabricOfClientStates<InstanceIdOfModel> {
    @Override
    public ClientState01NoConnect<InstanceIdOfModel> getClientState01NoConnect(ClientStateAutomaton<InstanceIdOfModel> clientStateAutomaton) {
        return new LocalClientState01NoConnect(clientStateAutomaton);
    }

    @Override
    public ClientState02ConnectNonIdent<InstanceIdOfModel> getClientState02ConnectNonIdent(ClientStateAutomaton<InstanceIdOfModel> clientStateAutomaton) {
        return new LocalClientState02ConnectNonIdent(clientStateAutomaton);
    }

    @Override
    public ClientState03ConnectAuthorized<InstanceIdOfModel> getClientState03ConnectAuthorized(ClientStateAutomaton<InstanceIdOfModel> clientStateAutomaton) {
        return new LocalClientState03ConnectAuthorized(clientStateAutomaton);
    }

    @Override
    public ClientState04GameTypeSetSelected<InstanceIdOfModel> getClientState04GameTypeSetSelected(ClientStateAutomaton<InstanceIdOfModel> clientStateAutomaton) {
        return new LocalClientState04GameTypeSetSelected(clientStateAutomaton);
    }

    @Override
    public ClientState05GameTypeSelected<InstanceIdOfModel> getClientState05GameTypeSelected(ClientStateAutomaton<InstanceIdOfModel> clientStateAutomaton) {
        return new LocalClientState05GameTypeSelected(clientStateAutomaton);
    }

    @Override
    public ClientState06GameMatchSetSelected<InstanceIdOfModel> getClientState06GameMatchSetSelected(ClientStateAutomaton<InstanceIdOfModel> clientStateAutomaton) {
        return new LocalClientState06GameMatchSetSelected(clientStateAutomaton);
    }

    @Override
    public ClientState07GameMatchSelected<InstanceIdOfModel> getClientState07GameMatchSelected(ClientStateAutomaton<InstanceIdOfModel> clientStateAutomaton) {
        return new LocalClientState07GameMatchSelected(clientStateAutomaton);
    }

    @Override
    public ClientState08GameIsPlaying<InstanceIdOfModel> getClientState08GameIsPlaying(ClientStateAutomaton<InstanceIdOfModel> clientStateAutomaton) {
        return new LocalClientState08GameIsPlaying(clientStateAutomaton);
    }
}
