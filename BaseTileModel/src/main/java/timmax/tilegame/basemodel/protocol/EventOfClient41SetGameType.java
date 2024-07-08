package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient41SetGameType<ClientId> extends EventOfClient<ClientId> {
    // ToDo: См. комментарий к GameType.
    private String gameTypeName;

    public EventOfClient41SetGameType() {
        super();
    }

    public EventOfClient41SetGameType(String gameTypeName) {
        this();
        this.gameTypeName = gameTypeName;
    }

    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        if (gameTypeName == null) {
            logger.error("Client sent empty name of model classes.");
            remoteClientStateAutomaton.forgetGameType();
            return;
        }
        // От клиента поступило символическое имя типа игры (оно должно быть одно из тех, которые ему направлялись множеством).

        GameType gameType = remoteClientStateAutomaton
                .getGameTypeSet()
                .stream()
                // В том перечне ищется gameType с таким-же именем:
                .filter(x -> x.getGameName().equals(gameTypeName))
                .findAny()
                .orElse(null);

        remoteClientStateAutomaton.setGameType(gameType);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "gameTypeName='" + gameTypeName + '\'' +
                '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameTypeName);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameTypeName = (String) in.readObject();
    }
}
