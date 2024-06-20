package timmax.tilegame.basemodel.protocol.client.jfx;

import timmax.tilegame.basemodel.protocol.client.LocalClientState07GameMatchSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

// ToDo: Удалить класс.
public class LocalClientState07GameMatchSelectedJfx<Model, ClientId> extends LocalClientState07GameMatchSelected<Model, ClientId> {
    public LocalClientState07GameMatchSelectedJfx(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }
}
