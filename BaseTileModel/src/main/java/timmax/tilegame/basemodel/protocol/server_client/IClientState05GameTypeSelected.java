package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

//  Выбран тип игры.
public interface IClientState05GameTypeSelected<Model> extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 5 (GameTypeSelected)
    GameType getGameType();

    void forgetGameType();

    void setGameMatchSet(Set<Model> setOfServerBaseModel);
}
