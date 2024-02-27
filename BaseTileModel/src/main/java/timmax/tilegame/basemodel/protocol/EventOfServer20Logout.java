package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

public class EventOfServer20Logout extends EventOfServer {
    @Override
    public void executeOnClient(IModelOfClient iModelOfClient) {
        logger.debug("  onLogout");
        // Todo: улучшить качество кода:
        //       Вызов метода у объекта объекта - не хорошая практика!
        //       multiGameWebSocketClient.clientState.setUserName
        //       Ну и далее по аналогии.
        iModelOfClient.getLocalClientState().forgetUserName();
    }

    @Override
    public String toString() {
        return "EventOfServer20Logout{}";
    }
}
