package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.FORGET_GAME_TYPE;

public class TransportPackageOfServer30ForgetGameType<T> extends TransportPackageOfServer<T> {

    @Override
    public void execute(TransportOfClient<T> transportOfClient) {
        System.out.println("  onForgetGameType");

        transportOfClient.getClientState().setServerBaseModelClass(null);
        transportOfClient.getHashSetOfObserverOnAbstractEvent().updateConnectStatePane(FORGET_GAME_TYPE);
    }

    @Override
    public String toString() {
        return "TransportPackageOfServer30ForgetGameType{}";
    }
}