package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.dto.BaseDto;
import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

//  Событие сервера с именем пользователя, который был идентифицирован, аутентификацирован и авторизован.
public class EventOfServer21IdentifyAuthenticateAuthorizeUser extends EventOfServer {
    private BaseDto userDto;

    public EventOfServer21IdentifyAuthenticateAuthorizeUser() {
        super();
    }

    public EventOfServer21IdentifyAuthenticateAuthorizeUser(BaseDto userDto) {
        this();
        if (userDto == null || userDto.isIdNullOrEmpty()) {
            throw new NullPointerException("User name is null. User name must not be null.");
        }
        this.userDto = userDto;
    }

    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.authorizeUser(userDto);
    }

    @Override
    public String toString() {
        return
                EventOfServer21IdentifyAuthenticateAuthorizeUser.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                        "userDto='" + userDto + '\'' +
                        '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(userDto);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        userDto = (BaseDto) in.readObject();
    }
}
