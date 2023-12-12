package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfController;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.FORGET_GAME_TYPE;

public class TransportPackageOfServer30ForgetGameType<T> extends TransportPackageOfServer<T> {

    @Override
    public void execute(TransportOfController<T> transportOfModel) {
        System.out.println("onForgetGameType");

        transportOfModel.getClientState().setServerBaseModelClass(null);
        transportOfModel.getHashSetOfObserverOnAbstractEvent().updateConnectStatePane(FORGET_GAME_TYPE);
    }

    @Override
    public String toString() {
        return "TransportPackageOfServer30ForgetGameType{}";
    }
}