package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

//  Выбран перечень типов игр.
public interface IClientState04GameTypeSetSelected<GameMatchX extends IGameMatchX> extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 4 (GameTypeSetSelected)
    Set<GameType> getGameTypeSet();
    void setGameType(GameType gameType, Set<GameMatchX> gameMatchXSet);
    void resetUser();
}
