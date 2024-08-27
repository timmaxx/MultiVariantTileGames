package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient41SelectGameType extends EventOfClient {
    // ToDo: См. комментарий к GameType.
    private GameType gameType;

    public EventOfClient41SelectGameType() {
        super();
    }

    public EventOfClient41SelectGameType(GameType gameType) {
        this();
        this.gameType = gameType;
    }

    // class EventOfClient
    @Override
    public <ClientId> void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton) {
        // ToDo: Формировать список матчей фильтруя из общего списка матчей на сервере (пока такого нет).
        //       Список матчей должен накапливаться при работе сервера (даже без БД, а с БД и подавно).
        //       Но вообще-то, вместо пустого списка (new HashSet<>()), нужно возвращать перечень моделей,
        //       которые соответствуют выбранному типу игр, и к которым ещё можно присоединиться.
        //       Т.е. удовлетворяющих условиям:
        //         1. Игра для 2-х и более игроков.
        //         2. Есть хотя-бы одна не занятая роль.
        remoteClientStateAutomaton.selectGameType(gameType);
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameType);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameType = (GameType) in.readObject();
    }

    // class Object
    @Override
    public String toString() {
        return "EventOfClient41SelectGameType{" +
                "gameType=" + gameType +
                '}';
    }
}
