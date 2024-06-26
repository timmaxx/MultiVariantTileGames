package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

public class EventOfServer31GetGameTypeSet extends EventOfServer {
    private Set<ModelOfServerDescriptor> collectionOfModelOfServerDescriptor;

    public EventOfServer31GetGameTypeSet() {
        super();
    }

    public EventOfServer31GetGameTypeSet(Set<ModelOfServerDescriptor> collectionOfModelOfServerDescriptor) {
        this();
        this.collectionOfModelOfServerDescriptor = collectionOfModelOfServerDescriptor;
    }

    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        logger.debug("  onGetGameTypeSet");
        localClientStateAutomaton.setGameTypeSet(collectionOfModelOfServerDescriptor);
    }

    @Override
    public String toString() {
        return "EventOfServer31GetGameTypeSet{" +
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
                    logger.error("readExternal(ObjectInput in)\n  element of collections is not ModelOfServerDescriptor.");
                    System.exit(1);
                }
            }
            @SuppressWarnings("unchecked")
            Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor = (Set<ModelOfServerDescriptor>) setOfObj;
            this.collectionOfModelOfServerDescriptor = setOfModelOfServerDescriptor;
        } else {
            logger.error("readExternal(ObjectInput in)\n  in.readObject() is not instance of Set.");
            System.exit(1);
        }
    }
}
