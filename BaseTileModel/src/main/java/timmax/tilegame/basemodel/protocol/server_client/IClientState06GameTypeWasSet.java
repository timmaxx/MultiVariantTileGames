package timmax.tilegame.basemodel.protocol.server_client;

import java.util.Set;

//  Выбран тип игры.
public interface IClientState06GameTypeWasSet<GameMatchX extends IGameMatchX> extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 6 (MatchSetSelected)
    Set<GameMatchX> getGameMatchXSet();

    void setGameMatchX(GameMatchX gameMatchX);
    //  interface TransportOfClient
    //      void setGameMatch(GameMatchDto gameMatchDto);

    void resetGameType();
    //  interface TransportOfClient
    //      void resetGameType();
}
