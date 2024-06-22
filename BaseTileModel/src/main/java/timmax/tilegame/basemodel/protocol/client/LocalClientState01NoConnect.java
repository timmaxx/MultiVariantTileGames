package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState01NoConnect;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

// ToDo: Здесь и в других LocalClientState0X...:
//       Возможно вместо Model использовать конкретный интерфейс IModelOfClient
public class LocalClientState01NoConnect<Model> extends ClientState01NoConnect<Model> {
    public LocalClientState01NoConnect(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }
}
