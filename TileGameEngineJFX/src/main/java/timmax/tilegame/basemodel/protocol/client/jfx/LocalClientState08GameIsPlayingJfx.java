package timmax.tilegame.basemodel.protocol.client.jfx;

import timmax.tilegame.basemodel.protocol.client.LocalClientState08GameIsPlaying;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

// ToDo: Удалить класс.
public class LocalClientState08GameIsPlayingJfx<Model, ClientId> extends LocalClientState08GameIsPlaying<Model, ClientId> {
    public LocalClientState08GameIsPlayingJfx(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }
}
