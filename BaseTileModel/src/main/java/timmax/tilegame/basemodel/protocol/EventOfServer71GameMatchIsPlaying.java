package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer71GameMatchIsPlaying extends EventOfServer {
    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onGameMatchIsPlaying");
        transportOfClient.getLocalClientState().setGameIsPlaying(true);
    }

    @Override
    public String toString() {
        return "EventOfServer71GameMatchIsPlaying{}";
    }
}
