package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

public class EventOfServer71GameMatchIsPlaying extends EventOfServer {
    @Override
    public void executeOnClient(IModelOfClient iModelOfClient) {
        logger.debug("  onGameMatchIsPlaying");
        iModelOfClient.getLocalClientState().setGameIsPlaying(true);
    }

    @Override
    public String toString() {
        return "EventOfServer71GameMatchIsPlaying{}";
    }
}
