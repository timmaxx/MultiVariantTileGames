package timmax.tilegame.server.websocket;

import timmax.tilegame.basemodel.protocol.server.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class FabricOfRemoteClientStates<ClientId> implements IFabricOfRemoteClientStates<ClientId> {
    @Override
    public RemoteClientState01NoConnect<ClientId> getClientState01NoConnect(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        return new RemoteClientState01NoConnect<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState02ConnectNonIdent<ClientId> getClientState02ConnectNonIdent(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        return new RemoteClientState02ConnectNonIdent<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState03ConnectAuthorized<ClientId> getClientState03ConnectAuthorized(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        return new RemoteClientState03ConnectAuthorized<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState04GameTypeSetSelected<ClientId> getClientState04GameTypeSetSelected(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        return new RemoteClientState04GameTypeSetSelected<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState05GameTypeSelected<ClientId> getClientState05GameTypeSelected(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        return new RemoteClientState05GameTypeSelected<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState06GameMatchSetSelected<ClientId> getClientState06GameMatchSetSelected(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        return new RemoteClientState06GameMatchSetSelected<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState07GameMatchSelected<ClientId> getClientState07GameMatchSelected(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        return new RemoteClientState07GameMatchSelected<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState08GameIsPlaying<ClientId> getClientState08GameIsPlaying(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        return new RemoteClientState08GameIsPlaying<>(clientStateAutomaton);
    }
}
