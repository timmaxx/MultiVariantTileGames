package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

//  Подключение есть, но сервер не предоставил информацию о перечне типов игр.
public interface IClientState02ConnectWithoutServerInfo extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 2 ConnectNonIdent
    //  Warning:(13, 9) Raw use of parameterized class 'GameType'
    Set<GameType<IGameMatchX>> getGameTypeSet();

    void setGameTypeSet(Set<GameType<IGameMatchX>> gameTypeSet);

    void close();
    //  interface TransportOfClient
    //      void close();
}
