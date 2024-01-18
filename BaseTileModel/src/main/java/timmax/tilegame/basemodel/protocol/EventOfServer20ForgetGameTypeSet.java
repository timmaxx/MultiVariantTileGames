package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer20ForgetGameTypeSet extends EventOfServer {
    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onForgetGameTypeSet");

        // Todo: улучшить качество кода:
        //       Вызов метода у объекта объекта - не хорошая практика!
        //       multiGameWebSocketClient.clientState.setUserName
        //       Ну и далее по аналогии.
        transportOfClient.getLocalClientState().forgetGameTypeSet();
        transportOfClient.getHashSetOfObserverOnAbstractEvent().updateOnForgetGameTypeSet();
    }

    @Override
    public String toString() {
        return "EventOfServer20ForgetGameTypeSet{}";
    }
}
