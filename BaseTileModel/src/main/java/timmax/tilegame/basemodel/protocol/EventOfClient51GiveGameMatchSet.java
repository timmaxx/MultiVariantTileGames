package timmax.tilegame.basemodel.protocol;

import java.util.HashSet;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient51GiveGameMatchSet extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        System.out.println("  GetGameMatchSet");

        // Сначала вместо new ArrayList<>() применял List.of() и здесь это работало.
        // Но когда на клиенте "вручную" добавляется строка "New game", а на сервере если пришло "New game" и делается
        // попытка add("New game"), то поскольку List.of() - неизменяемый список, возникнет исключение.

        // Но вообще то, вместо пустого списка, нужно возвращать перечень моделей,
        // которые соответствуют выбранному типу игр и к которым ещё можно присоединиться.
        // Т.е. удовлетворяющих условиям:
        // 1. Игра для 2-х и более игроков.
        // 2. Есть хотя-бы одна не занятая роль.

        transportOfServer.getRemoteClientStateByClientId(clientId).setGameMatchSet(new HashSet<>());
    }

    @Override
    public String toString() {
        return "EventOfClient51GiveGameMatchSet{}";
    }
}
