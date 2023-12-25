package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.basemodel.protocol.TypeOfEvent.GET_GAME_TYPE_SET;

public class EventOfServer021GetGameTypeSet<T> extends EventOfServer<T> {
    List<String> arrayListOfServerBaseModelClass = new ArrayList<>();


    public EventOfServer021GetGameTypeSet() {
        super();
    }

    public EventOfServer021GetGameTypeSet(List<String> arrayListOfServerBaseModelClass) {
        this();
        this.arrayListOfServerBaseModelClass = arrayListOfServerBaseModelClass;
    }

    @Override
    public void executeOnClient(TransportOfClient<T> transportOfClient) {
        System.out.println("  onGetGameTypeSet");

        transportOfClient.getClientState().setArrayListOfServerBaseModelClass(new ArrayList<>());

        for (String serverBaseModelClass : arrayListOfServerBaseModelClass) {
            transportOfClient.getClientState().addServerBaseModelClass(serverBaseModelClass);
        }
        transportOfClient.getHashSetOfObserverOnAbstractEvent().updateConnectStatePane(GET_GAME_TYPE_SET);
    }

    @Override
    public String toString() {
        return "EventOfServer021GetGameTypeSet{" +
                "arrayListOfServerBaseModelClass=" + arrayListOfServerBaseModelClass +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(arrayListOfServerBaseModelClass);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Object obj = in.readObject();
        if (obj instanceof List<?> listOfObj) {
            for (Object objOfList : listOfObj) {
                if (!(objOfList instanceof String)) {
                    System.err.println("class EventOfServer021GetGameTypeSet\n method void readExternal(ObjectInput in)\n  element of collections is not String");
                    System.exit(1);
                }
            }
            @SuppressWarnings("unchecked")
            List<String> arrayListOfServerBaseModelClassTmp = (List<String>) listOfObj;
            arrayListOfServerBaseModelClass = arrayListOfServerBaseModelClassTmp;
        } else {
            System.err.println("class EventOfServer021GetGameTypeSet\n method void readExternal(ObjectInput in)\n  in.readObject() is not instance of List");
            System.exit(1);
        }
    }
}