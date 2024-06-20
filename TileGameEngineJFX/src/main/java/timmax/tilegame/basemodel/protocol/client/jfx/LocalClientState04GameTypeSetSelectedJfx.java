package timmax.tilegame.basemodel.protocol.client.jfx;

import timmax.tilegame.basemodel.protocol.client.LocalClientState04GameTypeSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

// ToDo: Удалить класс.
public class LocalClientState04GameTypeSetSelectedJfx<Model, ClientId> extends LocalClientState04GameTypeSetSelected<Model, ClientId> {
    public LocalClientState04GameTypeSetSelectedJfx(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }
}
