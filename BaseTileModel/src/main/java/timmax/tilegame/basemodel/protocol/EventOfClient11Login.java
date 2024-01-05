package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.credential.Credentials;
import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient11Login extends EventOfClient {
    private String userName;
    private String password;

    public EventOfClient11Login() {
        super();
    }

    public EventOfClient11Login(String userName, String password) {
        this();
        this.userName = userName;
        this.password = password;
    }

    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onLogin");

        System.out.println("    userName = " + userName + " | " + "password = *"); // Пароль не выводим:

        if (Credentials.isUserAndPasswordCorrect(userName, password)) {
            // ToDo: Нужно зафиксировать для этого webSocket имя пользователя (и потом другие параметры авторизации).
            System.out.println("    sendLoginAnswer(TransportOfModel, clientId, userName)");
            sendLoginAnswer(transportOfServer, clientId, userName);
        } else {
            System.out.println("    sendLogoutAnswer(TransportOfModel, clientId)");
            sendLogoutAnswer(transportOfServer, clientId);
        }
    }

    @Override
    public String toString() {
        return "EventOfClient11Login{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

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