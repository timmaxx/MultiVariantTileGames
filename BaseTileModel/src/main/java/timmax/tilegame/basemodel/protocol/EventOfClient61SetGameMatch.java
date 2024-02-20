package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import timmax.tilegame.basemodel.protocol.server.IModelOfServer;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server.RemoteClientState;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient61SetGameMatch extends EventOfClient {
    private InstanceIdOfModel instanceIdOfModel;

    public EventOfClient61SetGameMatch() {
        super();
    }

    public EventOfClient61SetGameMatch(InstanceIdOfModel instanceIdOfModel) {
        this();
        this.instanceIdOfModel = instanceIdOfModel;
    }

    @Override
    public <ClientId> void executeOnServer(TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        System.out.println("  onSetGameMatch");

        System.out.println("  InstanceIdOfModel = " + instanceIdOfModel);

        // ToDo: Исправить Warning:(33, 9) Raw use of parameterized class 'IModelOfServer'
        IModelOfServer iModelOfServer = null;
        RemoteClientState remoteClientState = transportOfServer.getRemoteClientStateByClientId(clientId);
        if (instanceIdOfModel.getId().equals("New game")) {
            // Определяем ранее выбранный тип
            ModelOfServerDescriptor modelOfServerDescriptor = remoteClientState.getGameType();
            Constructor<? extends IModelOfServer> constructor = modelOfServerDescriptor.getConstructorOfModelOfServerClass();

            try {
                // Создаём экземпляр модели, ранее выбранного типа.
                iModelOfServer = constructor.newInstance(remoteClientState);
            } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
                System.err.println("Server cannot create object of model for " + modelOfServerDescriptor + " with constructor with specific parameters.");
                e.printStackTrace();
                System.exit(1);
            }
            remoteClientState
                    .getGameMatchSet()
                    // ToDo: Исправить Warning:(54, 26) Unchecked assignment: 'timmax.tilegame.basemodel.protocol.server.IModelOfServer' to 'timmax.tilegame.basemodel.protocol.server.IModelOfServer<ClientId>'
                    .add(iModelOfServer);
        }
        /*
          else {
            iModelOfServer = remoteClientState
                    .getGameMatchSet()
                    .stream()
                    .filter(x -> x.toString().equals(instanceIdOfModel.getId()))
                    .findAny()
                    .orElse(null)
            ;

            if (iModelOfServer == null) {
                System.err.println("There is not model '" + instanceIdOfModel.getId() + "'");
                remoteClientState.forgetServerBaseModel();
                return;
            }
        }
        */
        // ToDo: Исправить Warning:(72, 87) Unchecked assignment: 'timmax.tilegame.basemodel.protocol.server.IModelOfServer' to 'timmax.tilegame.basemodel.protocol.server.IModelOfServer<ClientId>'
        remoteClientState.setServerBaseModel(iModelOfServer);
    }

    @Override
    public String toString() {
        return "EventOfClient61SetGameMatch{" +
                "instanceIdOfModel=" + instanceIdOfModel +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(instanceIdOfModel);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        instanceIdOfModel = (InstanceIdOfModel) in.readObject();
    }
}
