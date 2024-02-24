package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

public class EventOfServer71GameMatchIsPlaying extends EventOfServer {
    @Override
    public void executeOnClient(IModelOfClient iModelOfClient) {
        System.out.println("  onGameMatchIsPlaying");
        iModelOfClient.getLocalClientState().setGameIsPlaying(true);
    }

    @Override
    public String toString() {
        return "EventOfServer71GameMatchIsPlaying{}";
    }
}
