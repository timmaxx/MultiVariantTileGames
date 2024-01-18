package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer41GameTypeSelect extends EventOfServer {
    private ModelOfServerDescriptor modelOfServerDescriptor;

    public EventOfServer41GameTypeSelect() {
        super();
    }

    public EventOfServer41GameTypeSelect(ModelOfServerDescriptor modelOfServerDescriptor) {
        this();
        this.modelOfServerDescriptor = modelOfServerDescriptor;
    }

    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onSelectGameType");

        transportOfClient.getLocalClientState().setGameType(modelOfServerDescriptor);
        transportOfClient.getHashSetOfObserverOnAbstractEvent().updateOnSelectGameType();
    }

    @Override
    public String toString() {
        return "EventOfServer41GameTypeSelect{" +
                "modelOfServerDescriptor='" + modelOfServerDescriptor + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(modelOfServerDescriptor);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        modelOfServerDescriptor = (ModelOfServerDescriptor) in.readObject();
    }
}
