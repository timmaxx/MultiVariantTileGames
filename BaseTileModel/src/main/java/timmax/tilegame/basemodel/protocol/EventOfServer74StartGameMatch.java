package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchExtendedDto;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

//  ToDo:   Проверить, почему есть два одинаковых класса EventOfServer71StartGameMatch и EventOfServer74StartGameMatch.
//          Удалить один из них и/или отредактировать их.
public class EventOfServer74StartGameMatch extends EventOfServer {
    private GameMatchExtendedDto gameMatchExtendedDto;

    public EventOfServer74StartGameMatch() {
        super();
    }

    public EventOfServer74StartGameMatch(GameMatchExtendedDto gameMatchExtendedDto) {
        this.gameMatchExtendedDto = gameMatchExtendedDto;
    }

    // class EventOfServer
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.startGameMatch(gameMatchExtendedDto);
    }

    // class Object
    @Override
    public String toString() {
        return "EventOfServer74StartGameMatch{" +
                "gameMatchExtendedDto=" + gameMatchExtendedDto +
                '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(gameMatchExtendedDto);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        gameMatchExtendedDto = (GameMatchExtendedDto) in.readObject();
    }
}
