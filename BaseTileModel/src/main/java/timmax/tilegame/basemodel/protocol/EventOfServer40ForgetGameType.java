package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer40ForgetGameType extends EventOfServer {
    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onForgetGameType");

        transportOfClient.getLocalClientState().forgetGameType();
        transportOfClient.getHashSetOfObserverOnAbstractEvent().updateOnForgetGameType();
    }

    @Override
    public String toString() {
        return "EventOfServer40ForgetGameType{}";
    }
}
