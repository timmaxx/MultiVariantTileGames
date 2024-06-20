package timmax.tilegame.basemodel.protocol.client.jfx;

import timmax.tilegame.basemodel.protocol.client.LocalClientState06GameMatchSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

// ToDo: Удалить класс.
public class LocalClientState06GameMatchSetSelectedJfx<Model, ClientId> extends LocalClientState06GameMatchSetSelected<Model, ClientId> {
    public LocalClientState06GameMatchSetSelectedJfx(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }
}
