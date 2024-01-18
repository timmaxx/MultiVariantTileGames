package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer41GetGamePlaySet extends EventOfServer {
    private List<IModelOfClient> listOfIModelOfClient;

    public EventOfServer41GetGamePlaySet() {
        super();
    }

    public EventOfServer41GetGamePlaySet(List<IModelOfClient> listOfIModelOfClient) {
        this();
        this.listOfIModelOfClient = listOfIModelOfClient;
    }

    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onGetGameTypeSet");

        transportOfClient
                .getLocalClientState()
                .setListOfServerBaseModel(listOfIModelOfClient);

        transportOfClient
                .getHashSetOfObserverOnAbstractEvent()
                .updateOnGetGamePlaySet();
    }

    @Override
    public String toString() {
        return "EventOfServer41GetGamePlaySet{" +
                "listOfIModelOfServer=" + listOfIModelOfClient +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(listOfIModelOfClient);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // ToDo: избавиться от "Warning:(52, 32) Unchecked cast: 'java.lang.Object' to 'java.util.List<timmax.tilegame.basemodel.protocol.client.IModelOfClient>'"
        //       Например как в readExternal в EventOfServer21GetGameTypeSet
        listOfIModelOfClient = (List<IModelOfClient>) in.readObject();
    }
}
