package timmax.tilegame.server.websocket;

import timmax.tilegame.basemodel.protocol.server.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

// Если из классов LocalClientState0Х убрать метод
// Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView)
// И убрать из AbstractClientState и перенести в ClientStateAutomaton,
// то не понадобятся классы LocalClientState0Х...Jfx и FabricOfClientStatesJfx и FabricOfRemoteClientStates
public class FabricOfRemoteClientStates<ClientId> implements IFabricOfRemoteClientStates<ClientId> {
    @Override
    public RemoteClientState01NoConect<ClientId> getClientState01NoConect(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton) {
        return new RemoteClientState01NoConect<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState02ConnectNonIdent<ClientId> getClientState02ConnectNonIdent(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton) {
        return new RemoteClientState02ConnectNonIdent<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState03ConnectAuthorized<ClientId> getClientState03ConnectAuthorized(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton) {
        return new RemoteClientState03ConnectAuthorized<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState04GameTypeSetSelected<ClientId> getClientState04GameTypeSetSelected(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton) {
        return new RemoteClientState04GameTypeSetSelected<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState05GameTypeSelected<ClientId> getClientState05GameTypeSelected(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton) {
        return new RemoteClientState05GameTypeSelected<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState06GameMatchSetSelected<ClientId> getClientState06GameMatchSetSelected(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton) {
        return new RemoteClientState06GameMatchSetSelected<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState07GameMatchSelected<ClientId> getClientState07GameMatchSelected(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton) {
        return new RemoteClientState07GameMatchSelected<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState08GameIsPlaying<ClientId> getClientState08GameIsPlaying(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton) {
        return new RemoteClientState08GameIsPlaying<>(clientStateAutomaton);
    }
}
