package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

//  Событие сервера с именем пользователя, который был идентифицирован, аутентификацирован и авторизован.
public class EventOfServer21IdentifyAuthenticateAuthorizeUser extends EventOfServer {
    //  ToDo:   Вместо этого поля нужно здесь использовать DTO для USER, в котором будет только это поле.
    private String userId;

    public EventOfServer21IdentifyAuthenticateAuthorizeUser() {
        super();
    }

    public EventOfServer21IdentifyAuthenticateAuthorizeUser(String userId) {
        this();
        if (userId == null || userId.equals("")) {
            throw new NullPointerException("UserName is null. UserName must not be null.");
        }
        this.userId = userId;
    }

    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.authorizeUser(userId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "userId='" + userId + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(userId);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        userId = (String) in.readObject();
    }
}
