package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer51GetGameMatchSet extends EventOfServer {
    private List<InstanceIdOfModel> listOfInstanceIdOfModel;

    public EventOfServer51GetGameMatchSet() {
        super();
    }

    public EventOfServer51GetGameMatchSet(List<InstanceIdOfModel> listOfInstanceIdOfModel) {
        this();
        this.listOfInstanceIdOfModel = listOfInstanceIdOfModel;
    }

    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onGetGameMatchSet");

        transportOfClient
                .getLocalClientState()
                .setGameMatchSet(listOfInstanceIdOfModel);
    }

    @Override
    public String toString() {
        return "EventOfServer51GetGameMatchSet{" +
                "listOfInstanceIdOfModel=" + listOfInstanceIdOfModel +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(listOfInstanceIdOfModel);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // ToDo: Исправить Warning:(48, 35) Unchecked cast: 'java.lang.Object' to 'java.util.List<timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel>'
        //       Например как в readExternal в EventOfServer31GetGameTypeSet
        listOfInstanceIdOfModel = (List<InstanceIdOfModel>) in.readObject();
    }
}
