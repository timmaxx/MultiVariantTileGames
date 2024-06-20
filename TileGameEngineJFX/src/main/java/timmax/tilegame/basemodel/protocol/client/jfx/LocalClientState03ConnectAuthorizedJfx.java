package timmax.tilegame.basemodel.protocol.client.jfx;

import timmax.tilegame.basemodel.protocol.client.LocalClientState03ConnectAuthorized;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

// ToDo: Удалить класс.
public class LocalClientState03ConnectAuthorizedJfx<Model, ClientId> extends LocalClientState03ConnectAuthorized<Model, ClientId> {
    public LocalClientState03ConnectAuthorizedJfx(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }
}
