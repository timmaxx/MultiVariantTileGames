package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient21GetGameTypeSet extends EventOfClient {
    @Override
    public <ClienId> void executeOnServer(TransportOfServer<ClienId> transportOfServer, ClienId clientId) {
        System.out.println("  onGetGameTypeSet");
        // transportOfServer.getRemoteClientStateByClientId(clientId).getGameTypeSet();
        // Можно было-бы:
        // - прочесть ещё раз файл с перечнем классов с типами игр,
        // - сохранить этот перечень с помощью .setGameTypeSet(перечень).
        // - тогда уже вызвать .sendGameTypeSet().
        // Сейчас-же просто отправим клиенту тот перечень типов игр, который изначально был сфомирован.
        transportOfServer.getRemoteClientStateByClientId(clientId).sendGameTypeSet();
    }

    @Override
    public String toString() {
        return "EventOfClient21GetGameTypeSet{}";
    }
}
