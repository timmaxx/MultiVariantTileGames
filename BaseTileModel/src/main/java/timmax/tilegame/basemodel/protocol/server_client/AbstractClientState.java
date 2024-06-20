package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.baseview.View;

import java.lang.reflect.Constructor;

public abstract class AbstractClientState<Model, ClientId> {
    private final ClientStateAutomaton<Model, ClientId> clientStateAutomaton;

    public AbstractClientState(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        this.clientStateAutomaton = clientStateAutomaton;
    }

    public ClientStateAutomaton<Model, ClientId> getClientStateAutomaton() {
        return clientStateAutomaton;
    }

    // ToDo: Вынести этот метод в ClientStateAutomaton
    // For local clientState:
    public abstract Constructor<? extends View> getViewConstructor(
            Class<? extends View> classOfView
    );
    // End of local clientState
}
