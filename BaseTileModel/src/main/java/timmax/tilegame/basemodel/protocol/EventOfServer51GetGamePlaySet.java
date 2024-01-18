package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer51GetGamePlaySet extends EventOfServer {
    private List<InstanceIdOfModel> listOfInstanceIdOfModel;

    public EventOfServer51GetGamePlaySet() {
        super();
    }

    public EventOfServer51GetGamePlaySet(List<InstanceIdOfModel> listOfInstanceIdOfModel) {
        this();
        this.listOfInstanceIdOfModel = listOfInstanceIdOfModel;
    }

    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onGetGameTypeSet");

        transportOfClient
                .getLocalClientState()
                .setGamePlaySet(listOfInstanceIdOfModel);
    }

    @Override
    public String toString() {
        return "EventOfServer51GetGamePlaySet{" +
                "listOfInstanceIdOfModel=" + listOfInstanceIdOfModel +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(listOfInstanceIdOfModel);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // ToDo: избавиться от "Warning:(48, 35) Unchecked cast: 'java.lang.Object' to 'java.util.List<timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel>'"
        //       Например как в readExternal в EventOfServer31GetGameTypeSet
        listOfInstanceIdOfModel = (List<InstanceIdOfModel>) in.readObject();
    }
}
