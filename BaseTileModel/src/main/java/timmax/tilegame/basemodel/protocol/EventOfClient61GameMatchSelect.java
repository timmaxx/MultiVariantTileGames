package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import timmax.tilegame.basemodel.protocol.server.IModelOfServer;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient61GameMatchSelect extends EventOfClient {
    private InstanceIdOfModel instanceIdOfModel;

    public EventOfClient61GameMatchSelect() {
        super();
    }
    public EventOfClient61GameMatchSelect(InstanceIdOfModel instanceIdOfModel) {
        this();
        this.instanceIdOfModel = instanceIdOfModel;
    }

    @Override
    public <ClientId> void executeOnServer(TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        System.out.println("  onGameMatchSelect");

        System.out.println("  InstanceIdOfModel = " + instanceIdOfModel);

        IModelOfServer iModelOfServer = null;
        if (instanceIdOfModel.getId().equals("New game")) {
            // Определяем ранее выбранный тип
            ModelOfServerDescriptor modelOfServerDescriptor = transportOfServer
                    .getRemoteClientStateByClientId(clientId)
                    .getGameType()
            ;
            Constructor<?> constructor = modelOfServerDescriptor.getConstructorOfModelOfServerClass();

            Object obj = null;
            try {
                // Создаём экземпляр модели, ранее выбранного типа.
                obj = constructor.newInstance(transportOfServer);
            } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
                System.err.println("Server cannot create object of model for " + modelOfServerDescriptor + " with constructor with specific parameters.");
                e.printStackTrace();
                System.exit(1);
            }
            System.out.println("  after constructor");
            if (obj instanceof IModelOfServer iModelOfServerTmp) {
                iModelOfServer = iModelOfServerTmp;
            }

            transportOfServer
                    .getRemoteClientStateByClientId(clientId)
                    .getGamePlaySet()
                    .add(iModelOfServer);
        } else {
            iModelOfServer = transportOfServer
                    .getRemoteClientStateByClientId(clientId)
                    .getGamePlaySet()
                    .stream()
                    .filter(x -> x.toString().equals(instanceIdOfModel.getId()))
                    .findAny()
                    .orElse(null)
            ;

            if (iModelOfServer == null) {
                System.err.println("There is not model '" + instanceIdOfModel.getId() + "'");
                transportOfServer.getRemoteClientStateByClientId(clientId).forgetServerBaseModel();
                return;
            }
        }
        transportOfServer.getRemoteClientStateByClientId(clientId).setServerBaseModel(iModelOfServer);
    }

    @Override
    public String toString() {
        return "EventOfClient61GameMatchSelect{" +
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
