package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.credential.Credentials;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient21SetUser<ClientId> extends EventOfClient<ClientId> {
    private String userName;
    private String password;

    public EventOfClient21SetUser() {
        super();
    }

    public EventOfClient21SetUser(String userName, String password) {
        this();
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        if (Credentials.isUserAndPasswordCorrect(userName, password)) {
            password = ""; // Не будем даже хранить пароль.
            remoteClientStateAutomaton.setUser(userName);
        } else {
            remoteClientStateAutomaton.forgetUser();
        }
    }

    @Override
    public String toString() {
        return "EventOfClient21SetUser{" +
                "userName='" + userName + '\'' +
                '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(userName);
        out.writeObject(password);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        userName = (String) in.readObject();
        password = (String) in.readObject();
    }
}
