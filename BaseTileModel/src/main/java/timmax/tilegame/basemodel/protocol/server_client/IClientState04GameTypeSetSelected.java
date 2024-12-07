package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

//  Выбран перечень типов игр.
public interface IClientState04GameTypeSetSelected<GameMatchX extends IGameMatchX> extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 4 (GameTypeSetSelected)
    // ToDo: Метод был добавлен, что-бы добраться до описания типа,
    //       но если нужно только описание, то м.б. другой метод сделать?
    GameType getGameType();


    void setGameType(GameType<GameMatchX> gameType);
    //  interface TransportOfClient
    //      void setGameType(GameType gameType);


    void reauthorizeUser();
    //  interface TransportOfClient
    //      void reauthorizeUser();
}
