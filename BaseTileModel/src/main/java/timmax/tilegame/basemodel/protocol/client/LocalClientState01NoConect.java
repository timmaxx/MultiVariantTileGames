package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState01NoConect;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

// ToDo: Здесь и в других LocalClientState0X...:
//       Возможно вместо Model использовать конкретный интерфейс IModelOfClient
public class LocalClientState01NoConect<Model> extends ClientState01NoConect<Model> {
    public LocalClientState01NoConect(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }
}
