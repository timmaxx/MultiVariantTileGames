package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

public class EventOfServer70GameMatchIsNotPlaying extends EventOfServer {
    @Override
    public void executeOnClient(IModelOfClient iModelOfClient) {
        logger.debug("  onGameMatchIsNotPlaying");
        iModelOfClient.getLocalClientState().forgetGameIsPlaying();
    }

    @Override
    public String toString() {
        return "EventOfServer70GameMatchIsNotPlaying{}";
    }
}
