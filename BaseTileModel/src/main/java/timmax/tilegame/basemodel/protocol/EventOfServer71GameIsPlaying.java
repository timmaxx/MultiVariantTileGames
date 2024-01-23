package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer71GameIsPlaying extends EventOfServer {
    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onGameIsPlaying");
        transportOfClient.getLocalClientState().setGameIsPlaying(true);
    }

    @Override
    public String toString() {
        return "EventOfServer71GameIsPlaying{}";
    }
}
