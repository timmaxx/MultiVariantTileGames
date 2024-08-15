package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server.IGameMatch;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

public class EventOfClient61SetGameMatch extends EventOfClient {
    private GameMatchDto gameMatchDto;

    public EventOfClient61SetGameMatch() {
        super();
    }

    public EventOfClient61SetGameMatch(GameMatchDto gameMatchDto) {
        this();
        this.gameMatchDto = gameMatchDto;
    }

    // class EventOfClient
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        if (gameMatchDto.isNullOrEmpty()) {
            remoteClientStateAutomaton.reselectGameType();
            return;
        }

        IGameMatch iGameMatch;

        iGameMatch = remoteClientStateAutomaton
                .getGameMatchXSet()
                .stream()
                .filter(x -> x.toString().equals(gameMatchDto.getId()))
                .findAny()
                .orElse(null)
        ;

        if (iGameMatch == null) {
            logger.error("There is not match '" + gameMatchDto.getId() + "'");
            remoteClientStateAutomaton.reselectGameType();
            return;
        }

        // ToDo: Исправить Warning:(72, 87) Unchecked assignment: 'timmax.tilegame.basemodel.protocol.server.IGameMatch' to 'timmax.tilegame.basemodel.protocol.server.IGameMatch<ClientId>'
        remoteClientStateAutomaton.setGameMatchX(iGameMatch);
    }

    // class Object
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "gameMatchDto=" + gameMatchDto +
                '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameMatchDto);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameMatchDto = (GameMatchDto) in.readObject();
    }
}
