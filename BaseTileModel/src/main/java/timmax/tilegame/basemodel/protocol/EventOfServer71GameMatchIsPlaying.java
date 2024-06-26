package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer71GameMatchIsPlaying<Model> extends EventOfServer<Model> {
    @Override
    public void executeOnClient(LocalClientStateAutomaton<Model> localClientStateAutomaton) {
        logger.debug("  onGameMatchIsPlaying");
        localClientStateAutomaton.setGameIsPlaying(true);
    }

    @Override
    public String toString() {
        return "EventOfServer71GameMatchIsPlaying{}";
    }
}
