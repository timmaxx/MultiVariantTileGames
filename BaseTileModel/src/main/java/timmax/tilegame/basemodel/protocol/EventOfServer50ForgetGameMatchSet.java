package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

public class EventOfServer50ForgetGameMatchSet extends EventOfServer {
    @Override
    public void executeOnClient(IModelOfClient iModelOfClient) {
        logger.debug("  onForgetGameMatchSet");
        iModelOfClient.getLocalClientState().forgetGameMatchSet();
    }

    @Override
    public String toString() {
        return "EventOfServer50ForgetGameMatchSet{}";
    }
}
