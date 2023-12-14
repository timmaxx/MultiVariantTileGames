package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.basemodel.protocol.TypeOfEvent.LOGIN;

public class EventOfServer011Login<T> extends EventOfServer<T> {
    private /*final*/ String userName;

    public EventOfServer011Login() {
        super();
    }

    public EventOfServer011Login(String userName) {
        this();
        this.userName = userName;
    }

    @Override
    public void execute(TransportOfClient<T> transportOfClient) {
        System.out.println("  onLogin");

        transportOfClient.getClientState().setUserName(userName);
        transportOfClient.getHashSetOfObserverOnAbstractEvent().updateConnectStatePane(LOGIN);
    }

    @Override
    public String toString() {
        return "EventOfServer011Login{" +
                "userName='" + userName + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(userName);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        userName = (String) in.readObject();
    }
}