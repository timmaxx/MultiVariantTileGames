package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server.IGameMatch;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

public class EventOfClient61SetGameMatch extends EventOfClient {
    private GameMatchId gameMatchId;

    public EventOfClient61SetGameMatch() {
        super();
    }

    public EventOfClient61SetGameMatch(GameMatchId gameMatchId) {
        this();
        this.gameMatchId = gameMatchId;
    }

    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        if (gameMatchId.isNullOrEmpty()) {
            remoteClientStateAutomaton.forgetGameMatchX();
            return;
        }

        IGameMatch iGameMatch;

        iGameMatch = remoteClientStateAutomaton
                .getGameMatchXSet()
                .stream()
                .filter(x -> x.toString().equals(gameMatchId.getId()))
                .findAny()
                .orElse(null)
        ;

        if (iGameMatch == null) {
            logger.error("There is not match '" + gameMatchId.getId() + "'");
            remoteClientStateAutomaton.forgetGameMatchX();
            return;
        }

        // ToDo: Исправить Warning:(72, 87) Unchecked assignment: 'timmax.tilegame.basemodel.protocol.server.IGameMatch' to 'timmax.tilegame.basemodel.protocol.server.IGameMatch<ClientId>'
        remoteClientStateAutomaton.setGameMatchX(iGameMatch);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "gameMatchId=" + gameMatchId +
                '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameMatchId);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameMatchId = (GameMatchId) in.readObject();
    }
}
