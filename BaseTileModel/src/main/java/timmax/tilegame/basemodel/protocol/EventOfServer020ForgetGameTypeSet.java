package timmax.tilegame.basemodel.protocol;

import java.util.ArrayList;

import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.basemodel.protocol.TypeOfEvent.FORGET_GAME_TYPE_SET;

public class EventOfServer020ForgetGameTypeSet extends EventOfServer {
    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onForgetGameTypeSet");

        // Todo: улучшить качество кода:
        //       Вызов метода у объекта объекта - не хорошая практика!
        //       multiGameWebSocketClient.clientState.setUserName
        //       Ну и далее по аналогии.
        transportOfClient.getLocalClientState().setArrayListOfServerBaseModelClass(new ArrayList<>());
        transportOfClient.getHashSetOfObserverOnAbstractEvent().updateConnectStatePane(FORGET_GAME_TYPE_SET);
    }

    @Override
    public String toString() {
        return "EventOfServerLogout{}";
    }
}
