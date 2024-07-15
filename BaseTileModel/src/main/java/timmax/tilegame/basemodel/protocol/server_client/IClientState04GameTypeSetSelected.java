package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

//  Выбран перечень типов игр.
public interface IClientState04GameTypeSetSelected<Model> extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 4 (GameTypeSetSelected)
    Set<GameType> getGameTypeSet();

    void forgetGameTypeSet();

    void setGameType(GameType gameType, Set<Model> gameMatchXSet);
}
