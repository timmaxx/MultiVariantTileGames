package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.basemodel.protocol.TypeOfEvent.FORGET_GAME_TYPE;

public class EventOfServer30ForgetGameType<T> extends EventOfServer<T> {

    @Override
    public void executeOnClient(TransportOfClient<T> transportOfClient) {
        System.out.println("  onForgetGameType");

        transportOfClient.getClientState().setServerBaseModelClass(null);
        transportOfClient.getHashSetOfObserverOnAbstractEvent().updateConnectStatePane(FORGET_GAME_TYPE);
    }

    @Override
    public String toString() {
        return "EventOfServer30ForgetGameType{}";
    }
}