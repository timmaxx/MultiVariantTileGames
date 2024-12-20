package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.credential.Credentials;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient21IdentifyAuthenticateAuthorizeUser extends EventOfClient {
    //  ToDo:   Вместо этих двух полей нужно здесь использовать DTO для USER, в котором будут эти два поля.
    private String userName;
    private String password;

    public EventOfClient21IdentifyAuthenticateAuthorizeUser() {
        super();
    }

    public EventOfClient21IdentifyAuthenticateAuthorizeUser(String userName, String password) {
        this();
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        if (Credentials.isUserAndPasswordCorrect(userName, password)) {
            password = ""; // Не будем даже хранить пароль.
            remoteClientStateAutomaton.authorizeUser(userName);
        } else {
            password = ""; // Не будем даже хранить неправильный пароль.
            remoteClientStateAutomaton.connect();
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "userName='" + userName + '\'' +
                ", password='*'" +
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
