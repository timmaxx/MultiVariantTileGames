package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer20Logout<Model> extends EventOfServer<Model> {
    @Override
    public void executeOnClient(LocalClientStateAutomaton<Model> localClientStateAutomaton) {
        logger.debug("  onLogout");
        // Todo: улучшить качество кода:
        //       Вызов метода у объекта объекта - не хорошая практика!
        //       multiGameWebSocketClient.clientState.setUserName
        //       Ну и далее по аналогии.
        localClientStateAutomaton.forgetUserName();
    }

    @Override
    public String toString() {
        return "EventOfServer20Logout{}";
    }
}
