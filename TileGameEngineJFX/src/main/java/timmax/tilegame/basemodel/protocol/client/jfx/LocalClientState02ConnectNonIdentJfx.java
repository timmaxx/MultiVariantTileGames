package timmax.tilegame.basemodel.protocol.client.jfx;

import timmax.tilegame.basemodel.protocol.client.LocalClientState02ConnectNonIdent;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

// ToDo: Удалить класс.
public class LocalClientState02ConnectNonIdentJfx<Model, ClientId> extends LocalClientState02ConnectNonIdent<Model, ClientId> {
    public LocalClientState02ConnectNonIdentJfx(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }
}
