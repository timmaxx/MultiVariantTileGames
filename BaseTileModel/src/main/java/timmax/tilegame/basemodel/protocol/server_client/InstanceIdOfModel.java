package timmax.tilegame.basemodel.protocol.server_client;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server.IModelOfServer;

public class InstanceIdOfModel implements Externalizable {
    private String id;

    public static InstanceIdOfModel modelOfServerToInstanceIdOfModel(IModelOfServer iModelOfServer) {
        return new InstanceIdOfModel(iModelOfServer.toString());
    }

    public InstanceIdOfModel() {
    }

    public InstanceIdOfModel(String id) {
        this();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "InstanceIdOfModel{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (String) in.readObject();
    }
}
