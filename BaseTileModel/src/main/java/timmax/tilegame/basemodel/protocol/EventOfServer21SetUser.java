package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameType;

// ToDo: Переименовать класс в, например, Идентификация-Аутентификация-Авторизация.
public class EventOfServer21SetUser extends EventOfServer {
    private String userName;
    private Set<GameType> gameTypeSet;

    public EventOfServer21SetUser() {
        super();
    }

    public EventOfServer21SetUser(String userName, Set<GameType> gameTypeSet) {
        this();
        if (userName == null || userName.equals("")) {
            throw new NullPointerException("UserName is null. UserName must not be null.");
        }
        this.userName = userName;
        this.gameTypeSet = gameTypeSet;
    }

    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.setUser(userName, gameTypeSet);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "userName='" + userName + '\'' +
                ", gameTypeSet=" + gameTypeSet +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(userName);
        out.writeObject(gameTypeSet);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        userName = (String) in.readObject();

        Object obj = in.readObject();
        if (obj instanceof Set<?> setOfObj) {
            for (Object objOfList : setOfObj) {
                if (!(objOfList instanceof GameType)) {
                    logger.error("readExternal(ObjectInput in)\n  element of collections is not GameType.");
                    System.exit(1);
                }
            }
            @SuppressWarnings("unchecked")
            Set<GameType> setOfGameType = (Set<GameType>) setOfObj;
            this.gameTypeSet = setOfGameType;
        } else {
            logger.error("readExternal(ObjectInput in)\n  in.readObject() is not instance of Set.");
            System.exit(1);
        }
    }
}
