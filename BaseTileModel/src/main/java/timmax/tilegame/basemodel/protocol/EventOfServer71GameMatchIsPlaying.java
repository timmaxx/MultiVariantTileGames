package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer71GameMatchIsPlaying extends EventOfServer {
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        logger.debug("  onGameMatchIsPlaying");
        localClientStateAutomaton.setGameIsPlaying(true);
    }

    @Override
    public String toString() {
        return "EventOfServer71GameMatchIsPlaying{}";
    }
}
