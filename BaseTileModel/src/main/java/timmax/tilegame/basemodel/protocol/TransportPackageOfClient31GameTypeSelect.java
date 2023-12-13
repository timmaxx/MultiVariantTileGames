package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.transport.TransportOfServer;

public class TransportPackageOfClient31GameTypeSelect<T> extends TransportPackageOfClient<T> {
    private String serverBaseModelClass;

    public TransportPackageOfClient31GameTypeSelect() {
        super();
    }

    public TransportPackageOfClient31GameTypeSelect(String serverBaseModelClass) {
        this();
        this.serverBaseModelClass = serverBaseModelClass;
    }

    @Override
    public void execute(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onSelectGameType");

        // ToDo: Проверить, что model одна из списка возможных моделей, которые были отправлены ранее этому клиенту.
        //       И если это не так, то отправить клиенту FORGET_GAME_TYPE.

        {   // Создать новую модель
            // ToDo: Вместо вызова конкретного конструктора, тут нужно создавать экземпляр того типа, который был выбран клиентом.
            // transportOfModel.setModelOfServer(new ModelOfServerOfSokoban<T>(transportOfModel));
            transportOfServer.setModelOfServerTmp();
            System.out.println("    modelOfServer = " + transportOfServer.getModelOfServer());
        }

        transportOfServer.send(clientId, new TransportPackageOfServer31GameTypeSelect<>(serverBaseModelClass));
    }

    @Override
    public String toString() {
        return "TransportPackageOfClient31GameTypeSelect{" +
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