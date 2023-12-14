package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.SELECT_GAME_TYPE;

public class EventOfServer31GameTypeSelect<T> extends EventOfServer<T> {
    private String serverBaseModelClass;

    public EventOfServer31GameTypeSelect() {
        super();
    }

    public EventOfServer31GameTypeSelect(String serverBaseModelClass) {
        this();
        this.serverBaseModelClass = serverBaseModelClass;
    }

    @Override
    public void execute(TransportOfClient<T> transportOfClient) {
        System.out.println("  onSelectGameType");

        transportOfClient.getClientState().setServerBaseModelClass(serverBaseModelClass);
        transportOfClient.getHashSetOfObserverOnAbstractEvent().updateConnectStatePane(SELECT_GAME_TYPE);
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