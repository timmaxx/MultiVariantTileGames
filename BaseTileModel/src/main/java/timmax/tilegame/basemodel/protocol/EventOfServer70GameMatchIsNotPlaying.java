package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer70GameMatchIsNotPlaying extends EventOfServer {
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        logger.debug("  onGameMatchIsNotPlaying");
        localClientStateAutomaton.forgetGameIsPlaying();
    }

    @Override
    public String toString() {
        return "EventOfServer70GameMatchIsNotPlaying{}";
    }
}
