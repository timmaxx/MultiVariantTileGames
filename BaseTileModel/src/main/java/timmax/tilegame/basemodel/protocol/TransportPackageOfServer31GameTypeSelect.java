package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.transport.TransportOfController;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.SELECT_GAME_TYPE;

public class TransportPackageOfServer31GameTypeSelect<T> extends TransportPackageOfServer<T> {
    private String serverBaseModelClass;

    public TransportPackageOfServer31GameTypeSelect() {
        super();
    }

    public TransportPackageOfServer31GameTypeSelect(String serverBaseModelClass) {
        this();
        this.serverBaseModelClass = serverBaseModelClass;
    }

    @Override
    public void execute(TransportOfController<T> transportOfModel) {
        System.out.println("  onSelectGameType");

        // ToDo: Если переделать на сервере отправку класса не строкой, а классом,
        //       то и здесь перевод из строки в класс не понадобится.
        // String serverBaseModelString = (String) (transportPackageOfServer.get("gameType"));
/*
        try {
            // transportOfModel.getClientState().setServerBaseModelClass((Class<? extends ServerBaseModel>) Class.forName(serverBaseModelString));
            // transportOfModel.getClientState().setServerBaseModelClass((Class<? extends ServerBaseModel>) Class.forName(serverBaseModelClass));
        }
*/
        transportOfModel.getClientState().setServerBaseModelClass(serverBaseModelClass);

        transportOfModel.getHashSetOfObserverOnAbstractEvent().updateConnectStatePane(SELECT_GAME_TYPE);
    }

    @Override
    public String toString() {
        return "TransportPackageOfServer31GameTypeSelect{" +
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