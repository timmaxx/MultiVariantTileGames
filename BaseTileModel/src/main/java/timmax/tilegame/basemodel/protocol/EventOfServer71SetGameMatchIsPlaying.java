package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer71SetGameMatchIsPlaying extends EventOfServer {
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.setGameMatchIsPlaying(true);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
