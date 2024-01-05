package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient31GameTypeSelect extends EventOfClient {
    private String serverBaseModelClass;

    public EventOfClient31GameTypeSelect() {
        super();
    }

    public EventOfClient31GameTypeSelect(String serverBaseModelClass) {
        this();
        this.serverBaseModelClass = serverBaseModelClass;
    }

    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onSelectGameType");

        // ToDo: Проверить, что model одна из списка возможных моделей, которые были отправлены ранее этому клиенту.
        //       И если это не так, то отправить клиенту FORGET_GAME_TYPE.

        {   // Создать новую модель
            // ToDo: Вместо вызова конкретного конструктора, тут нужно создавать экземпляр того типа, который был выбран клиентом.
            System.out.println("  serverBaseModelClass = " + serverBaseModelClass);
            if (serverBaseModelClass == null) {
                return;
            }

            try {
                // ToDo: Перенести логику по созданию модели из setModelOfServer() сюда.
                transportOfServer.setModelOfServer(serverBaseModelClass);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
            System.out.println("    modelOfServer = " + transportOfServer.getModelOfServer());
            if (transportOfServer.getModelOfServer() == null) {
                return;
            }
        }

        transportOfServer.send(clientId, new EventOfServer31GameTypeSelect(serverBaseModelClass));
    }

    @Override
    public String toString() {
        return "EventOfClient31GameTypeSelect{" +
                "serverBaseModelClass='" + serverBaseModelClass + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(serverBaseModelClass);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        serverBaseModelClass = (String) in.readObject();
    }
}
