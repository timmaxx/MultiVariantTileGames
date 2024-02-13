package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.transport.TransportOfClient;

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
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onSetGameMatch");

        transportOfClient
                .getLocalClientState()
                .setServerBaseModel(serverBaseModel);
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
