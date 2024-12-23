package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.credential.Credentials;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

//  Событие клиента с именем и паролем пользователя для идентификации, аутентификации и авторизации.
public class EventOfClient21IdentifyAuthenticateAuthorizeUser extends EventOfClient {
    //  ToDo:   Вместо этих двух полей нужно здесь использовать DTO для USER, в котором будут эти два поля.
    private String userId;
    private String userPassword;

    public EventOfClient21IdentifyAuthenticateAuthorizeUser() {
        super();
    }

    public EventOfClient21IdentifyAuthenticateAuthorizeUser(String userId, String userPassword) {
        this();
        this.userId = userId;
        this.userPassword = userPassword;
    }

    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        if (Credentials.isUserAndPasswordCorrect(userId, userPassword)) {
            userPassword = ""; // Не будем даже хранить пароль.
            remoteClientStateAutomaton.authorizeUser(userId);
        } else {
            userPassword = ""; // Не будем даже хранить неправильный пароль.
            remoteClientStateAutomaton.connect();
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "userId='" + userId + '\'' +
                ", userPassword='*'" +
                '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(userId);
        out.writeObject(userPassword);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        userId = (String) in.readObject();
        userPassword = (String) in.readObject();
    }
}
