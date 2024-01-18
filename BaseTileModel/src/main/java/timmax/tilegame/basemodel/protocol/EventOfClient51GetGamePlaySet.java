package timmax.tilegame.basemodel.protocol;

import java.util.List;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient51GetGamePlaySet extends EventOfClient {
    @Override
    public <ClienId> void executeOnServer(TransportOfServer<ClienId> transportOfServer, ClienId clientId) {
        System.out.println("  GetGamePlaySet");

        transportOfServer.sendEventOfServer(
                clientId,
                new EventOfServer51GetGamePlaySet(
                        // Из перечня игр, которые сейчас доступны серверу, составить список из тех игр,
                        // которые соответствуют выбранному типу игры
                        // и к которым ещё можно присоединиться. Т.е. удовлетворяющих условиям:
                        // 1. Игра для 2-х и более игроков.
                        // 2. Есть хотя-бы одна не занятая роль.
                        List.of() // Пока будем считать, что нет игр для присоединения.
                )
        );
    }

    @Override
    public String toString() {
        return "EventOfClient51GetGamePlaySet{}";
    }
}
