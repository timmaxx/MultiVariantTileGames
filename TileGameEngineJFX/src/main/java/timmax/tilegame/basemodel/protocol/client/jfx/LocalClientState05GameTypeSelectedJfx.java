package timmax.tilegame.basemodel.protocol.client.jfx;

import timmax.tilegame.basemodel.protocol.client.LocalClientState05GameTypeSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

// ToDo: Удалить класс.
public class LocalClientState05GameTypeSelectedJfx<Model, ClientId> extends LocalClientState05GameTypeSelected<Model, ClientId> {
    public LocalClientState05GameTypeSelectedJfx(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }
}
