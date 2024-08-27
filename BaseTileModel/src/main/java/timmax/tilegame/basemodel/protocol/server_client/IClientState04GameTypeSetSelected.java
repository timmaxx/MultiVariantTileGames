package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

//  Выбран перечень типов игр.
public interface IClientState04GameTypeSetSelected<GameMatchX extends IGameMatchX> extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 4 (GameTypeSetSelected)
    //  Warning:(12, 9) Raw use of parameterized class 'GameType'
    Set<GameType> getGameTypeSet();


    void selectGameType(GameType<GameMatchX> gameType);
    //  interface TransportOfClient
    //      void selectGameType(GameType gameType);


    void reauthorizeUser();
    //  interface TransportOfClient
    //      void reauthorizeUser();
}
