package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer31GameTypeSelect extends EventOfServer {
    private String serverBaseModelString;

    public EventOfServer31GameTypeSelect() {
        super();
    }

    public EventOfServer31GameTypeSelect(String serverBaseModelString) {
        this();
        this.serverBaseModelString = serverBaseModelString;
    }

    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onSelectGameType");

        transportOfClient.getLocalClientState().setServerBaseModel(serverBaseModelString);
        transportOfClient.getHashSetOfObserverOnAbstractEvent().updateOnSelectGameType();
    }

    @Override
    public String toString() {
        return "EventOfServer31GameTypeSelect{" +
                "serverBaseModelString='" + serverBaseModelString + '\'' +
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
