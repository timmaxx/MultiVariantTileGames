package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.credential.Credentials;
import timmax.tilegame.basemodel.dto.UserDtoIdPassword;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;
import timmax.tilegame.basemodel.util.UserUtil;

//  Событие клиента с именем и паролем пользователя для идентификации, аутентификации и авторизации.
public class EventOfClient21IdentifyAuthenticateAuthorizeUser extends EventOfClient {
    private UserDtoIdPassword userDtoIdPassword;

    public EventOfClient21IdentifyAuthenticateAuthorizeUser() {
        super();
    }

    public EventOfClient21IdentifyAuthenticateAuthorizeUser(UserDtoIdPassword userDtoIdPassword) {
        this();
        this.userDtoIdPassword = userDtoIdPassword;
    }

    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        if (Credentials.isUserAndPasswordCorrect(userDtoIdPassword)) {
            userDtoIdPassword.clearPassword(); // Не будем даже хранить пароль.
            remoteClientStateAutomaton.authorizeUser(UserUtil.createUserDtoId(userDtoIdPassword));
        } else {
            userDtoIdPassword.clearPassword(); // Не будем даже хранить пароль.
            remoteClientStateAutomaton.connect();
        }
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(userDtoIdPassword);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        userDtoIdPassword = (UserDtoIdPassword) in.readObject();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "userDtoIdPassword='" + userDtoIdPassword + '\'' +
                '}';
    }
}
