package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfController;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.LOGOUT;

public class TransportPackageOfServer010Logout<T> extends TransportPackageOfServer<T> {

    @Override
    public void execute(TransportOfController<T> transportOfModel) {
        System.out.println("onLogout");

        // Todo: улучшить качество кода:
        //       Вызов метода у объекта объекта - не хорошая практика!
        //       multiGameWebSocketClient.clientState.setUserName
        //       Ну и далее по аналогии.
        transportOfModel.getClientState().setUserName("");
        transportOfModel.getHashSetOfObserverOnAbstractEvent().updateConnectStatePane(LOGOUT);
    }

    @Override
    public String toString() {
        return "TransportPackageOfServer010Logout{}";
    }
}