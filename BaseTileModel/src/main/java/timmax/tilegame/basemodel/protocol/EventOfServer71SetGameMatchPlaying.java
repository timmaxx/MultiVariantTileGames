package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer71SetGameMatchPlaying extends EventOfServer {
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        logger.debug("  onSetGameMatchPlaying");
        localClientStateAutomaton.setGameMatchPlaying(true);
    }

    @Override
    public String toString() {
        return "EventOfServer71SetGameMatchPlaying{}";
    }
}
