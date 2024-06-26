package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer30ForgetGameTypeSet extends EventOfServer {
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        logger.debug("  onForgetGameTypeSet");
        // Todo: улучшить качество кода:
        //       Вызов метода у объекта объекта - не хорошая практика!
        //       multiGameWebSocketClient.clientState.setUserName
        //       Ну и далее по аналогии.
        localClientStateAutomaton.forgetGameTypeSet();
    }

    @Override
    public String toString() {
        return "EventOfServer30ForgetGameTypeSet{}";
    }
}
