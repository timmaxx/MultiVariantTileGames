package timmax.tilegame.basemodel.protocol;

import java.util.ArrayList;

import timmax.tilegame.transport.TransportOfController;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.FORGET_GAME_TYPE_SET;

public class TransportPackageOfServer020ForgetGameTypeSet<T> extends TransportPackageOfServer<T> {

    @Override
    public void execute(TransportOfController<T> transportOfModel) {
        System.out.println("  onForgetGameTypeSet");

        // Todo: улучшить качество кода:
        //       Вызов метода у объекта объекта - не хорошая практика!
        //       multiGameWebSocketClient.clientState.setUserName
        //       Ну и далее по аналогии.
        transportOfModel.getClientState().setArrayListOfServerBaseModelClass(new ArrayList<>());
        transportOfModel.getHashSetOfObserverOnAbstractEvent().updateConnectStatePane(FORGET_GAME_TYPE_SET);
    }

    @Override
    public String toString() {
        return "TransportPackageOfServerLogout{}";
    }
}