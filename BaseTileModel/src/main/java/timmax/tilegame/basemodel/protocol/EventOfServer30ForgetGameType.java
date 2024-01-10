package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.basemodel.protocol.TypeOfEvent.FORGET_GAME_TYPE;

public class EventOfServer30ForgetGameType extends EventOfServer {
    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onForgetGameType");

        transportOfClient.getLocalClientState().setServerBaseModelClass(null);
        transportOfClient.updateConnectStatePane(FORGET_GAME_TYPE);
    }

    @Override
    public String toString() {
        return "EventOfServer30ForgetGameType{}";
    }
}
