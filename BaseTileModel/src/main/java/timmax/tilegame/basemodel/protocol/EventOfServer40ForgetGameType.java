package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

public class EventOfServer40ForgetGameType extends EventOfServer {
    @Override
    public void executeOnClient(IModelOfClient iModelOfClient) {
        logger.debug("  onForgetGameType");
        iModelOfClient.getLocalClientState().forgetGameType();
    }

    @Override
    public String toString() {
        return "EventOfServer40ForgetGameType{}";
    }
}
