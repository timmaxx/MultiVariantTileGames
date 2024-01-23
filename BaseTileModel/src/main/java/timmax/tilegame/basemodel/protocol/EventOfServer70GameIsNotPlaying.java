package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer70GameIsNotPlaying extends EventOfServer {
    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onGameIsNotPlaying");
        transportOfClient.getLocalClientState().forgetGameIsPlaying();
    }

    @Override
    public String toString() {
        return "EventOfServer70GameIsNotPlaying{}";
    }
}
