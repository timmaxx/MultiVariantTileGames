package timmax.tilegame.basemodel.protocol.client.jfx;

import timmax.tilegame.basemodel.protocol.server_client.*;

// Если из классов LocalClientState0Х убрать метод
// Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView)
// убрать из AbstractClientState2 и перенести в ClientStateAutomaton,
// то не понадобятся классы LocalClientState0Х...Jfx и FabricOfClientStatesJfx
public class FabricOfClientStatesJfx<Model, ClientId> implements IFabricOfClientStates<Model, ClientId> {
    @Override
    public ClientState01NoConect<Model, ClientId> getClientState01NoConect(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        return new LocalClientState01NoConectJfx<>(clientStateAutomaton);
    }

    @Override
    public ClientState02ConnectNonIdent<Model, ClientId> getClientState02ConnectNonIdent(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        return new LocalClientState02ConnectNonIdentJfx<>(clientStateAutomaton);
    }

    @Override
    public ClientState03ConnectAuthorized<Model, ClientId> getClientState03ConnectAuthorized(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        return new LocalClientState03ConnectAuthorizedJfx<>(clientStateAutomaton);
    }

    @Override
    public ClientState04GameTypeSetSelected<Model, ClientId> getClientState04GameTypeSetSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        return new LocalClientState04GameTypeSetSelectedJfx<>(clientStateAutomaton);
    }

    @Override
    public ClientState05GameTypeSelected<Model, ClientId> getClientState05GameTypeSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        return new LocalClientState05GameTypeSelectedJfx<>(clientStateAutomaton);
    }

    @Override
    public ClientState06GameMatchSetSelected<Model, ClientId> getClientState06GameMatchSetSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        return new LocalClientState06GameMatchSetSelectedJfx<>(clientStateAutomaton);
    }

    @Override
    public ClientState07GameMatchSelected<Model, ClientId> getClientState07GameMatchSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        return new LocalClientState07GameMatchSelectedJfx<>(clientStateAutomaton);
    }

    @Override
    public ClientState08GameIsPlaying<Model, ClientId> getClientState08GameIsPlaying(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        return new LocalClientState08GameIsPlayingJfx<>(clientStateAutomaton);
    }
}
