package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.dto.BaseDto;
import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

//  Событие сервера с именем пользователя, который был идентифицирован, аутентификацирован и авторизован.
public class EventOfServer21IdentifyAuthenticateAuthorizeUser extends EventOfServer {
    private BaseDto userDtoId;

    public EventOfServer21IdentifyAuthenticateAuthorizeUser() {
        super();
    }

    public EventOfServer21IdentifyAuthenticateAuthorizeUser(BaseDto userDtoId) {
        this();
        if (userDtoId == null || userDtoId.isIdNullOrEmpty()) {
            throw new NullPointerException("User name is null. User name must not be null.");
        }
        this.userDtoId = userDtoId;
    }

    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.authorizeUser(userDtoId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "userDtoId='" + userDtoId + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(userDtoId);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        userDtoId = (BaseDto) in.readObject();
    }
}
