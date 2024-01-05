package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.basemodel.protocol.TypeOfEvent.LOGOUT;

public class EventOfServer10Logout extends EventOfServer {
    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onLogout");

        // Todo: улучшить качество кода:
        //       Вызов метода у объекта объекта - не хорошая практика!
        //       multiGameWebSocketClient.clientState.setUserName
        //       Ну и далее по аналогии.
        transportOfClient.getLocalClientState().setUserName("");
        transportOfClient.getHashSetOfObserverOnAbstractEvent().updateConnectStatePane(LOGOUT);
    }

    @Override
    public String toString() {
        return "EventOfServer10Logout{}";
    }
}
