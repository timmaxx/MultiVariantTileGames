package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

//  Выбран перечень типов игр.
public interface IClientState04GameTypeSetSelected<GameMatchX extends IGameMatchX> extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 4 (GameTypeSetSelected)
    void selectGameType(GameType<GameMatchX> gameType);
    //  interface TransportOfClient
    //      void selectGameType(GameType gameType);


    void reauthorizeUser();
    //  interface TransportOfClient
    //      void reauthorizeUser();
}
