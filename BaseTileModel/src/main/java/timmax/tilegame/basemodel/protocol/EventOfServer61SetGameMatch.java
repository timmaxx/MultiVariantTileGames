package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

// import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;

public class EventOfServer61SetGameMatch extends EventOfServer {
    InstanceIdOfModel serverBaseModel;

    public EventOfServer61SetGameMatch() {
        super();
    }

    public EventOfServer61SetGameMatch(InstanceIdOfModel serverBaseModel) {
        this();
        this.serverBaseModel = serverBaseModel;
    }

    @Override
    public void executeOnClient(IModelOfClient iModelOfClient) {
        logger.debug("  onSetGameMatch");
/*
        IModelOfClient iModelOfClient3 = null;
        for (IModelOfClient iModelOfClient2 : iModelOfClient.getLocalClientState().getGameMatchSet()) {
            if (iModelOfClient2.toString().equals(serverBaseModel.getId())) {
                iModelOfClient3 = iModelOfClient2;
                break;
            }
        };
*/
        // Ниже сигнатура setServerBaseModel:
        // void setServerBaseModel(Model serverBaseModel)
        // но
        // InstanceIdOfModel serverBaseModel
        // Поэтому InstanceIdOfModel нужно преобразовать в IModelOfClient
        iModelOfClient
                .getLocalClientState()
                .setServerBaseModel(serverBaseModel)
//              .setServerBaseModel(iModelOfClient3)
        ;
    }

    @Override
    public String toString() {
        return "EventOfServer61SetGameMatch{" +
                "serverBaseModel=" + serverBaseModel +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(serverBaseModel);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        serverBaseModel = (InstanceIdOfModel) in.readObject();
    }
}
