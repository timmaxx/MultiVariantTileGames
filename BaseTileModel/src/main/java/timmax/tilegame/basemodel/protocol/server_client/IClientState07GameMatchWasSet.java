package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.GameMatchStatus;

//  Выбрана партия (создан экземпляр класса модели и он в состоянии настройка).
public interface IClientState07GameMatchWasSet<GameMatchX extends IGameMatchX> extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 7 (MatchSelected)
    GameMatchX getGameMatchX();
    GameMatchStatus getGameMatchStatus();


    void resetGameMatch();
    //  interface TransportOfClient
    //      void resetGameMatch();

    void startGameMatch(GameMatchExtendedDto gameMatchExtendedDto);
}
