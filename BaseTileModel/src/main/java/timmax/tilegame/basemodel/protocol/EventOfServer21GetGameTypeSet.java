package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashSet;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer21GetGameTypeSet extends EventOfServer {
    private Set<ModelOfServerDescriptor> collectionOfModelOfServerDescriptor = new HashSet<>();

    public EventOfServer21GetGameTypeSet() {
        super();
    }

    public EventOfServer21GetGameTypeSet(Set<ModelOfServerDescriptor> collectionOfModelOfServerDescriptor) {
        this();
        this.collectionOfModelOfServerDescriptor = collectionOfModelOfServerDescriptor;
    }

    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onGetGameTypeSet");

        transportOfClient
                .getLocalClientState()
                .forgetListOfServerBaseModel();

        for (ModelOfServerDescriptor modelOfServerDescriptor : collectionOfModelOfServerDescriptor) {
            transportOfClient
                    .getLocalClientState()
                    .getListOfServerBaseModel()
                    .add(modelOfServerDescriptor.getGameName());
        }
        transportOfClient
                .getHashSetOfObserverOnAbstractEvent()
                .updateOnGetGameTypeSet();
    }

    @Override
    public String toString() {
        return "EventOfServer21GetGameTypeSet{" +
                "collectionOfModelOfServerDescriptor=" + collectionOfModelOfServerDescriptor +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(collectionOfModelOfServerDescriptor);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Object obj = in.readObject();
        if (obj instanceof Set<?> setOfObj) {
            for (Object objOfList : setOfObj) {
                if (!(objOfList instanceof ModelOfServerDescriptor)) {
                    System.err.println("class EventOfServer021GetGameTypeSet\n method void readExternal(ObjectInput in)\n  element of collections is not ModelOfServerDescriptor.");
                    System.exit(1);
                }
            }
            @SuppressWarnings("unchecked")
            Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor = (Set<ModelOfServerDescriptor>) setOfObj;
            this.collectionOfModelOfServerDescriptor = setOfModelOfServerDescriptor;
        } else {
            System.err.println("class EventOfServer021GetGameTypeSet\n method void readExternal(ObjectInput in)\n  in.readObject() is not instance of Set.");
            System.exit(1);
        }
    }
}
