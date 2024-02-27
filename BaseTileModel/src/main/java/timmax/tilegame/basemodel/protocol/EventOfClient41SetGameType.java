package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Constructor;

import timmax.tilegame.basemodel.protocol.server.IModelOfServer;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server.RemoteClientState;

public class EventOfClient41SetGameType extends EventOfClient {
    private ModelOfServerDescriptor modelOfServerDescriptor;

    public EventOfClient41SetGameType() {
        super();
    }

    public EventOfClient41SetGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        this();
        this.modelOfServerDescriptor = modelOfServerDescriptor;
    }

    @Override
    public <ClientId> void executeOnServer(RemoteClientState<ClientId> remoteClientState) {
        logger.debug("  onGameTypeSelect");
        logger.debug("  modelOfServerDescriptor = {}", modelOfServerDescriptor);
        if (modelOfServerDescriptor == null) {
            logger.error("Client sent empty name of model classes.");
            remoteClientState.forgetGameType();
            return;
        }

        Constructor<? extends IModelOfServer> constructor = remoteClientState
                .getGameTypeSet()
                .stream()
                .filter(x -> x.getGameName().equals(modelOfServerDescriptor.getGameName()))
                .findAny()
                .map(ModelOfServerDescriptor::getConstructorOfModelOfServerClass).orElse(null);

        if (constructor == null) {
            logger.error("{} was not found in list model classes.", modelOfServerDescriptor);
            remoteClientState.forgetGameType();
            return;
        }

        modelOfServerDescriptor.setConstructor(constructor);
        remoteClientState.setGameType(modelOfServerDescriptor);
    }

    @Override
    public String toString() {
        return "EventOfClient41SetGameType{" +
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
