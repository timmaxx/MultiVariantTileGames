package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer30ForgetGameType extends EventOfServer {
    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onForgetGameType");

        transportOfClient.getLocalClientState().forgetServerBaseModel();
        transportOfClient.getHashSetOfObserverOnAbstractEvent().updateOnForgetGameType();
    }

    @Override
    public String toString() {
        return "EventOfServer30ForgetGameType{}";
    }
}
