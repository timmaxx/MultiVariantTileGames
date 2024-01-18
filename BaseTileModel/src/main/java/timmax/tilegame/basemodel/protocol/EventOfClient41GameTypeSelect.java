package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import timmax.tilegame.basemodel.protocol.server.IModelOfServer;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient41GameTypeSelect extends EventOfClient {
    private ModelOfServerDescriptor modelOfServerDescriptor;

    public EventOfClient41GameTypeSelect() {
        super();
    }

    public EventOfClient41GameTypeSelect(ModelOfServerDescriptor modelOfServerDescriptor) {
        this();
        this.modelOfServerDescriptor = modelOfServerDescriptor;
    }

    @Override
    public <ClienId> void executeOnServer(TransportOfServer<ClienId> transportOfServer, ClienId clientId) {
        System.out.println("  onGameTypeSelect");

        System.out.println("  modelOfServerDescriptor = " + modelOfServerDescriptor);
        if (modelOfServerDescriptor == null) {
            System.err.println("Client sent empty name of model classes.");
            transportOfServer.getRemoteClientStateByClientId(clientId).forgetGameType();
            return;
        }

        Constructor<?> constructor = transportOfServer
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

        Object obj = null;
        try {
            obj = constructor.newInstance(transportOfServer);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            System.err.println("Server cannot create object of model for " + modelOfServerDescriptor + " with constructor with specific parameters.");
            e.printStackTrace();
            System.exit(1);
        }

        // ToDo: Избавиться от "Warning:(60, 28) Raw use of parameterized class 'IModelOfServer'":
        if (obj instanceof IModelOfServer) {
            // ??? // ToDo: Избавиться от "Unchecked assignment: 'timmax.tilegame.basemodel.protocol.server.IModelOfServer' to 'timmax.tilegame.basemodel.protocol.server.IModelOfServer<ClienId>'"
            transportOfServer.getRemoteClientStateByClientId(clientId).setGameType(modelOfServerDescriptor);
            // ToDo: obj не был использован в части получения у него полей или вызова методов. Нужен-ли он?
        } else {
            System.err.println("Created object is not ModelOfServer.");
            transportOfServer.getRemoteClientStateByClientId(clientId).forgetGameType();
        }
    }

    @Override
    public String toString() {
        return "EventOfClient41GameTypeSelect{" +
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
