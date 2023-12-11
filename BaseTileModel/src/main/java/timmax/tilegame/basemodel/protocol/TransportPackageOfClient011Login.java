package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.credential.Credentials;
import timmax.tilegame.transport.TransportOfModel;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class TransportPackageOfClient011Login<T> extends TransportPackageOfClient {
    private String userName;
    private String password;


    public TransportPackageOfClient011Login() {
        super();
    }

    public TransportPackageOfClient011Login(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(userName);
        out.writeObject(password);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        userName = (String) in.readObject();
        password = (String) in.readObject();
    }

    @Override
    public void execute(TransportOfModel TransportOfModel, Object clientId) {
        System.out.println("onLogin");

        System.out.println("userName = " + userName + " | " + "password = *"); // Пароль не выводим:

        if (Credentials.isUserAndPasswordCorrect(userName, password)) {
            // ToDo: Нужно зафиксировать для этого webSocket имя пользователя (и потом другие параметры авторизации).
            System.out.println("sendLoginAnswer(TransportOfModel, clientId, userName)");
            sendLoginAnswer(TransportOfModel, clientId, userName);
        } else {
            System.out.println("sendLogoutAnswer(TransportOfModel, clientId)");
            sendLogoutAnswer(TransportOfModel, clientId);
        }
    }

    @Override
    public String toString() {
        return "TransportPackageOfClientLogin{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}