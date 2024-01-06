package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import timmax.tilegame.basemodel.protocol.server.ModelOfServer;
import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient31GameTypeSelect extends EventOfClient {
    private String serverBaseModelString;

    public EventOfClient31GameTypeSelect() {
        super();
    }

    public EventOfClient31GameTypeSelect(String serverBaseModelString) {
        this();
        this.serverBaseModelString = serverBaseModelString;
    }

    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onSelectGameType");

        System.out.println("  serverBaseModelClass = " + serverBaseModelString);
        if (serverBaseModelString == null) {
            System.err.println("Client sent empty name of model classes.");
            transportOfServer.send(clientId, new EventOfServer30ForgetGameType());
            return;
        }

        Constructor<?> constructor = transportOfServer.getCollectionOfModelOfServerDescriptor()
                .stream()
                .filter(x -> x.getModelOfServerClass().toString().equals(serverBaseModelString))
                .findAny()
                // .map(x -> x.getConstructorOfModelOfServerClass()).orElse(null)
                .map(ModelOfServerDescriptor::getConstructorOfModelOfServerClass).orElse(null)
        ;

        if (constructor == null) {
            System.err.println(serverBaseModelString + "' was not found in list model classes.");
            transportOfServer.send(clientId, new EventOfServer30ForgetGameType());
            return;
        }

        Object obj = null;
        try {
            obj = constructor.newInstance(transportOfServer);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            System.err.println("Server cannot make object of model for " + serverBaseModelString + " with concrete constructor.");
            e.printStackTrace();
            System.exit(1);
        }

        if (obj instanceof ModelOfServer modelOfServer) {
            transportOfServer.setModelOfServer(modelOfServer);
        } else {
            System.err.println("Created object is not ModelOfServer.");
            transportOfServer.send(clientId, new EventOfServer30ForgetGameType());
            return;
        }

        System.out.println("    modelOfServer = " + transportOfServer.getModelOfServer());
        if (transportOfServer.getModelOfServer() == null) {
            transportOfServer.send(clientId, new EventOfServer30ForgetGameType());
            return;
        }

        transportOfServer.send(clientId, new EventOfServer31GameTypeSelect(serverBaseModelString));
    }

    @Override
    public String toString() {
        return "EventOfClient31GameTypeSelect{" +
                "serverBaseModelClass='" + serverBaseModelString + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(serverBaseModelString);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        serverBaseModelString = (String) in.readObject();
    }
}
