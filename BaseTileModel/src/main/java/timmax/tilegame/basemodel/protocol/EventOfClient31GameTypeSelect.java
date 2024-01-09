package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import timmax.tilegame.basemodel.protocol.server.ModelOfServer;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient31GameTypeSelect extends EventOfClient {
    private String gameName;

    public EventOfClient31GameTypeSelect() {
        super();
    }

    public EventOfClient31GameTypeSelect(String gameName) {
        this();
        this.gameName = gameName;
    }

    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onSelectGameType");

        System.out.println("  serverBaseModelClass = " + gameName);
        if (gameName == null) {
            System.err.println("Client sent empty name of model classes.");
            transportOfServer.sendEventOfServerToClient(clientId, new EventOfServer30ForgetGameType());
            return;
        }

        Constructor<?> constructor = transportOfServer.getCollectionOfModelOfServerDescriptor()
                .stream()
                .filter(x -> x.getGameName().equals(gameName))
                .findAny()
                // .map(x -> x.getConstructorOfModelOfServerClass()).orElse(null)
                .map(ModelOfServerDescriptor::getConstructorOfModelOfServerClass).orElse(null)
        ;

        if (constructor == null) {
            System.err.println(gameName + "' was not found in list model classes.");
            transportOfServer.sendEventOfServerToClient(clientId, new EventOfServer30ForgetGameType());
            return;
        }

        Object obj = null;
        try {
            obj = constructor.newInstance(transportOfServer);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            System.err.println("Server cannot make object of model for " + gameName + " with concrete constructor.");
            e.printStackTrace();
            System.exit(1);
        }

        if (obj instanceof ModelOfServer modelOfServer) {
            transportOfServer.setModelOfServer(modelOfServer);
        } else {
            System.err.println("Created object is not ModelOfServer.");
            transportOfServer.sendEventOfServerToClient(clientId, new EventOfServer30ForgetGameType());
            return;
        }

        System.out.println("    modelOfServer = " + transportOfServer.getModelOfServer());
        if (transportOfServer.getModelOfServer() == null) {
            transportOfServer.sendEventOfServerToClient(clientId, new EventOfServer30ForgetGameType());
            return;
        }

        transportOfServer.sendEventOfServerToClient(clientId, new EventOfServer31GameTypeSelect(gameName));
    }

    @Override
    public String toString() {
        return "EventOfClient31GameTypeSelect{" +
                "gameName='" + gameName + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameName);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameName = (String) in.readObject();
    }
}
