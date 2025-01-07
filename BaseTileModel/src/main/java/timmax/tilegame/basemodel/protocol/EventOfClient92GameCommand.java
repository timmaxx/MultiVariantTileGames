package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

//  Событие клиента с игровой командой.
//  Этот тип события (единствеенный после предыдущих 11-72) уже не меняет состояние игрового клиента
//  (т.е. матч уже стартовал), но передаёт информацию о ходе игрока в "сыром виде" - т.е. событие мыши или клавиатуры,
//  которое сервером должно быть транслировано:
//  - в ход,
//  - или в часть хода,
//  - или в сигнал, что весь ход передан и его нужно сделать.
public class EventOfClient92GameCommand extends EventOfClient {
    private GameCommand gameCommand;

    public EventOfClient92GameCommand() {
        super();
    }

    public EventOfClient92GameCommand(GameCommand gameCommand) {
        this();
        this.gameCommand = gameCommand;
    }

    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        gameCommand.executeOnServer(remoteClientStateAutomaton.getGameMatchX());
    }

    @Override
    public String toString() {
        return
                EventOfClient92GameCommand.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                        "gameCommand=" + gameCommand +
                        '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameCommand);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameCommand = (GameCommand) in.readObject();
    }
}
