package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server_client.IGameMatchX;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Set;

//  Событие сервера об установлении соединения и передаче перечня типов игр.
public class EventOfServer11ConnectWithoutUserIdentify extends EventOfServer {
    private Set<GameType<IGameMatchX>> gameTypeSet;

    public EventOfServer11ConnectWithoutUserIdentify() {
    }

    public EventOfServer11ConnectWithoutUserIdentify(Set<GameType<IGameMatchX>> gameTypeSet) {
        this.gameTypeSet = gameTypeSet;
    }

    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.setGameTypeSet(gameTypeSet);
    }

    @Override
    public String toString() {
        return "EventOfServer11ConnectWithoutUserIdentify{" +
                "gameTypeSet=" + gameTypeSet +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameTypeSet);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Object obj = in.readObject();
        if (obj instanceof Set<?> setOfObj) {
            for (Object objOfList : setOfObj) {
                if (!(objOfList instanceof GameType)) {
                    logger.error("Element of collections ({}) is not GameType type.", objOfList);
                    System.exit(1);
                }
            }
            //  Warning:(50, 32) Unchecked cast: 'java.util.Set<capture<?>>' to 'java.util.Set<timmax.tilegame.basemodel.protocol.server.GameType<timmax.tilegame.basemodel.protocol.server_client.IGameMatchX>>'
            this.gameTypeSet = (Set<GameType<IGameMatchX>>) setOfObj;
        } else {
            logger.error("{} is not instance of Set.", obj);
            System.exit(1);
        }
    }
}
