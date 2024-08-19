package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

public class EventOfServer61SelectGameMatch extends EventOfServer {
    GameMatchDto gameMatchDto;

    public EventOfServer61SelectGameMatch() {
        super();
    }

    public EventOfServer61SelectGameMatch(GameMatchDto gameMatchDto) {
        this();
        this.gameMatchDto = gameMatchDto;
    }

    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.selectGameMatchX(gameMatchDto);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "gameMatchDto=" + gameMatchDto +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameMatchDto);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameMatchDto = (GameMatchDto) in.readObject();
    }
}
