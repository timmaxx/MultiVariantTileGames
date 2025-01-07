package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server_client.IGameMatchX;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;
import timmax.tilegame.basemodel.dto.GameMatchDto;

//  ToDo:   Дописать в назначении класса об индексе игрока в матче.
//  Событие клиента с идентификатором матча.
public class EventOfClient61SetGameMatch extends EventOfClient {
    //  ToDo:   Нужно также передавать и индекс игрока в матче.
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
        if (gameMatchDto.isIdNullOrEmpty()) {
            remoteClientStateAutomaton.resetGameType();
            return;
        }

        IGameMatchX iGameMatch = remoteClientStateAutomaton
                .getGameMatchXSet()
                .stream()
                .filter(x -> x.toString().equals(gameMatchDto.getId()))
                .findAny()
                .orElse(null);

        if (iGameMatch == null) {
            logger.error("There is not match '" + gameMatchDto.getId() + "'");
            remoteClientStateAutomaton.resetGameType();
            return;
        }

        remoteClientStateAutomaton.setGameMatchX(iGameMatch);
    }

    // class Object
    @Override
    public String toString() {
        return
                EventOfClient61SetGameMatch.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                        "gameMatchDto=" + gameMatchDto +
                        '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(gameMatchDto);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        gameMatchDto = (GameMatchDto) in.readObject();
    }
}
