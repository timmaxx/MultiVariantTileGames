package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

public class EventOfServer41SetGameType extends EventOfServer {
    private ModelOfServerDescriptor modelOfServerDescriptor;

    public EventOfServer41SetGameType() {
        super();
    }

    public EventOfServer41SetGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        this();
        this.modelOfServerDescriptor = modelOfServerDescriptor;
    }

    @Override
    public void executeOnClient(IModelOfClient iModelOfClient) {
        System.out.println("  onGameTypeSelect");
        iModelOfClient.getLocalClientState().setGameType(modelOfServerDescriptor);
    }

    @Override
    public String toString() {
        return "EventOfServer41SetGameType{" +
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
