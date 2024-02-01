package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Constructor;

import timmax.tilegame.basemodel.protocol.server.IModelOfServer;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.transport.TransportOfServer;

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
    public <ClientId> void executeOnServer(TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        System.out.println("  onGameTypeSelect");
        System.out.println("  modelOfServerDescriptor = " + modelOfServerDescriptor);
        if (modelOfServerDescriptor == null) {
            System.err.println("Client sent empty name of model classes.");
            transportOfServer.getRemoteClientStateByClientId(clientId).forgetGameType();
            return;
        }

        Constructor<? extends IModelOfServer<?>> constructor = transportOfServer
                .getRemoteClientStateByClientId(clientId)
                .getGameTypeSet()
                .stream()
                .filter(x -> x.getGameName().equals(modelOfServerDescriptor.getGameName()))
                .findAny()
                .map(ModelOfServerDescriptor::getConstructorOfModelOfServerClass).orElse(null);

        if (constructor == null) {
            System.err.println(modelOfServerDescriptor + "' was not found in list model classes.");
            transportOfServer.getRemoteClientStateByClientId(clientId).forgetGameType();
            return;
        }

        modelOfServerDescriptor.setConstructor(constructor);
        transportOfServer.getRemoteClientStateByClientId(clientId).setGameType(modelOfServerDescriptor);
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
