package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashSet;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient41SetGameType extends EventOfClient {
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
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        if (gameTypeName == null || gameTypeName.equals("")) {
            logger.error("Client sent empty name of game type.");
            remoteClientStateAutomaton.forgetGameTypeSet();
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

        // ToDo: Формировать список матчей фильтруя из общего списка матчей на сервере (пока такого нет).
        //       Список матчей должен накапливаться при работе сервера (даже без БД, а с БД и подавно).
        //       Но вообще-то, вместо пустого списка (new HashSet<>()), нужно возвращать перечень моделей,
        //       которые соответствуют выбранному типу игр, и к которым ещё можно присоединиться.
        //       Т.е. удовлетворяющих условиям:
        //         1. Игра для 2-х и более игроков.
        //         2. Есть хотя-бы одна не занятая роль.
        remoteClientStateAutomaton.setGameType(gameType, new HashSet<>());
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
