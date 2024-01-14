package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer11Login extends EventOfServer {
    private /*final*/ String userName;

    public EventOfServer11Login() {
        super();
    }

    public EventOfServer11Login(String userName) {
        this();
        if (userName == null || userName.equals("")) {
            throw new NullPointerException("UserName is null. UserName must not be null.");
        }
        this.userName = userName;
    }

    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onLogin");

        transportOfClient.getLocalClientState().setUserName(userName);
        transportOfClient.getHashSetOfObserverOnAbstractEvent().updateOnLogin();
    }

    @Override
    public String toString() {
        return "EventOfServer11Login{" +
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
